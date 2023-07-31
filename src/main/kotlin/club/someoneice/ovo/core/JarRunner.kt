package club.someoneice.ovo.core

import club.someoneice.ovo.core.`object`.DataList
import club.someoneice.ovo.util.gson
import club.someoneice.togocup.recipebook.JarUtil
import com.google.common.collect.Lists
import com.google.common.collect.Maps
import com.google.common.collect.Sets
import com.google.gson.reflect.TypeToken

class JarRunner {
    companion object {
        val set: HashSet<String> = Sets.newHashSet(
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
            return this;
        }

        for (buffered in JarUtil.getInstance().dataSet) {
            val urlName = buffered.fileUrl;
            if (!urlName.contains("ovo/")) continue
            val name = urlName.replace("ovo/", "").substring(0, urlName.replace("ovo/", "").indexOf("/"))
            mappings[name] = mappings.getOrDefault(name, Lists.newArrayList()).addElement(buffered)
            if (urlName.contains("info.json")) infoMappings[name] = buffered
        }

        for (key in mappings.keys) {
            val mapping: ArrayList<JarUtil.UrlBuffered> = mappings[key]!!
            if (mapping.isNotEmpty() && infoMappings.containsKey(key)) {
                val type = object: TypeToken<HashMap<String, String>>() {}.type
                val modid: String? =
                    (gson.fromJson(JarUtil.getInstance().readFileFromUrl(infoMappings[key]), type) as HashMap<String, String>).let { it["modid"] }
                for (url in mappings[key]!!) {
                    if (url == infoMappings[key]) continue
                    val fileName =
                        url.fileUrl.substring(url.fileUrl.lastIndexOf("/") + 1, url.fileUrl.lastIndexOf("."))
                    (
                            if (set.contains(fileName.lowercase())) fileName
                            else {
                                val path = url.fileUrl.replace("ovo/${key}/", "")
                                val filePath = path.substring(0, path.indexOf("/"))
                                if (set.contains(filePath.lowercase())) filePath
                                else null
                            }
                            )?.let {
                            val txt = JarUtil.getInstance().readFileFromUrl(url) ?: return@let
                            when (it.lowercase()) {
                                "Item".lowercase()          -> CoreRunner.item.init            (txt, DataList.dataItem)
                                "ItemFood".lowercase()      -> CoreRunner.food.init            (txt, DataList.dataItemFood)
                                "ItemGift".lowercase()      -> CoreRunner.gift.init            (txt, DataList.dataItemGift)
                                "ItemTool".lowercase()      -> CoreRunner.tool.init            (txt, DataList.dataItemTool)
                                "ItemWeapons".lowercase()   -> CoreRunner.swords.init          (txt, DataList.dataItemWeapons)
                                "Block".lowercase()         -> CoreRunner.block.init           (txt, DataList.dataBlock)
                                "Recipe".lowercase()        -> CoreRunner.recipes.init         (txt, DataList.dataRecipes)
                                "DeleteRecipe".lowercase()  -> CoreRunner.delete_recipes.init  (txt, DataList.dataDeleteRecipes)
                                "Biomes".lowercase()        -> CoreRunner.biomes.init          (txt, DataList.dataBiomes)
                                "Group".lowercase()         -> CoreRunner.group.init           (txt, DataList.dataGroup)
                            }
                        }
                }

                DataProcessor(modid).init()
                DataList.init()
            }
        }
    }
}