package club.someoneice.ovov1.core

import club.someoneice.ovov1.core.`object`.DataList
import club.someoneice.ovov1.util.gson
import club.someoneice.togocup.recipebook.JarUtil
import com.google.common.collect.ImmutableList
import com.google.common.collect.Lists
import com.google.common.collect.Maps
import com.google.gson.reflect.TypeToken

class JarRunner {
    companion object {
        val list: ImmutableList<String> = ImmutableList.of(
            "Item".lowercase(),
            "ItemFood".lowercase(),
            "ItemGift".lowercase(),
            "ItemTool".lowercase(),
            "ItemWeapons".lowercase(),
            "Block".lowercase(),
            "Recipe".lowercase(),
            "DeleteRecipe".lowercase(),
            "Biomes".lowercase(),
            "Group".lowercase()
        )
    }

    init {
        val mappings: HashMap<String, ArrayList<JarUtil.UrlBuffered>> = Maps.newHashMap()
        val infoMappings: HashMap<String, JarUtil.UrlBuffered> = Maps.newHashMap()
        fun <E>ArrayList<E>.addElement(e: E): ArrayList<E> {
            this.add(e)
            return this
        }

        for (buffered in JarUtil.getInstance().dataSet) {
            val urlName = buffered.fileUrl
            if (!urlName.contains("ovo/")) continue
            val name = urlName.replace("ovo/", "").substring(0, urlName.replace("ovo/", "").indexOf("/"))
            mappings[name] = mappings.getOrDefault(name, Lists.newArrayList()).addElement(buffered)
            if (urlName.contains("info.json")) infoMappings[name] = buffered
        }

        for (key in mappings.keys) processor(mappings, infoMappings, key)
    }

    private fun processor(mappings: HashMap<String, ArrayList<JarUtil.UrlBuffered>>, infoMappings: HashMap<String, JarUtil.UrlBuffered>, key: String) {
        val mapping: ArrayList<JarUtil.UrlBuffered> = mappings[key]!!
        if (mapping.isNotEmpty() && infoMappings.containsKey(key)) {
            val type = object: TypeToken<HashMap<String, String>>() {}.type
            val modid: String? =
                (gson.fromJson(JarUtil.getInstance().readFileFromUrl(infoMappings[key]), type) as HashMap<String, String>)
                    .let { it["modid"] }

            for (url in mappings[key]!!) processorUrl(url, infoMappings, key)

            DataProcessor(modid).init()
            DataList.init()
        }
    }

    private fun processorUrl(url: JarUtil.UrlBuffered, infoMappings: HashMap<String, JarUtil.UrlBuffered>, key: String) {
        if (url == infoMappings[key]) return
        val fileName = url.fileUrl.substring(url.fileUrl.lastIndexOf("/") + 1, url.fileUrl.lastIndexOf("."))
        run {
            if (list.contains(fileName.lowercase())) return@run fileName

            val path = url.fileUrl.replace("ovo/${key}/", "")
            val filePath = path.substring(0, path.indexOf("/"))

            return@run if (list.contains(filePath.lowercase())) filePath else null
        }?.let {
            val txt = JarUtil.getInstance().readFileFromUrl(url) ?: return@let
            val name = if (it.endsWith("s")) it.substring(0, it.length - 1) else it
            when (name.lowercase()) {
                "Item".lowercase()          -> CoreRunner.item.init            (txt, DataList.dataItem)
                "ItemFood".lowercase()      -> CoreRunner.food.init            (txt, DataList.dataItemFood)
                "ItemGift".lowercase()      -> CoreRunner.gift.init            (txt, DataList.dataItemGift)
                "ItemTool".lowercase()      -> CoreRunner.tool.init            (txt, DataList.dataItemTool)
                "ItemWeapon".lowercase()    -> CoreRunner.sword.init           (txt, DataList.dataItemWeapons)
                "Block".lowercase()         -> CoreRunner.block.init           (txt, DataList.dataBlock)
                "Recipe".lowercase()        -> CoreRunner.recipe.init          (txt, DataList.dataRecipes)
                "DeleteRecipe".lowercase()  -> CoreRunner.delete_recipes.init  (txt, DataList.dataDeleteRecipes)
                "Biome".lowercase()         -> CoreRunner.biomes.init          (txt, DataList.dataBiomes)
                "Group".lowercase()         -> CoreRunner.group.init           (txt, DataList.dataGroup)
            }
        }
    }
}