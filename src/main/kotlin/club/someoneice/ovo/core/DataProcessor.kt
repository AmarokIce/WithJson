package club.someoneice.ovo.core

import club.someoneice.ovo.data.*
import club.someoneice.ovo.data.helper.Ingredient
import club.someoneice.ovo.util.RemoveRecipes
import club.someoneice.ovo.util.gson
import club.someoneice.ovo.util.register
import com.google.common.collect.Lists
import com.google.gson.reflect.TypeToken
import net.minecraft.block.Block
import net.minecraft.item.Item
import java.io.File
import java.io.IOException
import java.util.*

class DataProcessor(data: File, private val modid: String) {
    private val dataBiomes        : ArrayList<BiomesData> = Lists.newArrayList()
    private val dataBlock         : ArrayList<BlockData>          = Lists.newArrayList()
    private val dataItem          : ArrayList<ItemData>           = Lists.newArrayList()
    private val dataItemTool      : ArrayList<ItemToolData>       = Lists.newArrayList()
    private val dataRecipes       : ArrayList<Recipe>             = Lists.newArrayList()
    private val dataDeleteRecipes : ArrayList<Ingredient>         = Lists.newArrayList()

    private val dataGroup         : ArrayList<Group>              = Lists.newArrayList()

    init {
        data.listFiles()!!.forEach(this::scanFile)

        this.dataDeleteRecipes.forEach { it.toItemStack()?.let(RemoveRecipes::removeAllRecipe) }

        val itemListCache = HashMap<String, Item>()
        val blockListCache = HashMap<String, Block>()

        this.dataItem.forEach { itemListCache[it.name] = it.registryItem(modid) }
        this.dataItemTool.forEach { itemListCache[it.name] = it.registerTool() }

        this.dataBlock.forEach { blockListCache[it.name] = it.registerBlock() }

        this.dataBiomes.forEach(BiomesData::register)
        this.dataGroup.forEach { it.registerAndScanItemCache(modid) }
        this.dataRecipes.forEach(Recipe::addRecipe)

        itemListCache.entries.forEach { (k, v) -> v.register(k, modid) }
        blockListCache.entries.forEach { (k, v) -> v.register(k) }
    }

    private fun scanFile(file: File) {
        val name: String = if (file.isDirectory) file.name else file.nameWithoutExtension
        if (name.endsWith("s")) name.substring(0, name.length - 1)

        when (name.lowercase()) {
            "Group".lowercase()         -> handle(file, dataGroup)
            "DeleteRecipe".lowercase()  -> handle(file, dataDeleteRecipes)
            "Item".lowercase()          -> handle(file, dataItem)
            "ItemTool".lowercase()      -> handle(file, dataItemTool)
            "Block".lowercase()         -> handle(file, dataBlock)
            "Recipe".lowercase()        -> handle(file, dataRecipes)
            "Biome".lowercase()         -> handle(file, dataBiomes)
        }
    }

    private inline fun <reified T> handle(file: File, list: ArrayList<T>) {
        if (file.isDirectory) for(f in file.listFiles()!!) this.dataScan(f, list)
        else this.dataScan(file, list)
    }

    private inline fun <reified T> dataScan(file: File, list: ArrayList<T>) {
        list.addAll(pullAsData<T>(read(file).toString()))
    }

    private inline fun <reified T> pullAsData(nodeBase: String) =
        gson.fromJson<ArrayList<T>>(nodeBase, object: TypeToken<ArrayList<T>>() {}.type)

    @Throws(IOException::class)
    private fun read(file: File) = StringBuilder().apply {
        file.readLines().forEach { text ->
            if (text.contains("//")) this.append(text, 0, text.indexOf("//"))
            else this.append(text)
        }
    }
}