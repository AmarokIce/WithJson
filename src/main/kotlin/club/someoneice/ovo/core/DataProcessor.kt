package club.someoneice.ovo.core

import club.someoneice.ovo.data.*
import club.someoneice.ovo.data.helper.Ingredient
import club.someoneice.ovo.util.RemoveRecipes
import com.google.common.collect.Lists
import java.io.File

class DataProcessor(val data: File, val modid: String) {
    private val dataBiomes        :ArrayList<BiomesData>         = Lists.newArrayList()
    private val dataBlock         :ArrayList<BlockData>          = Lists.newArrayList()
    private val dataItem          :ArrayList<ItemData>           = Lists.newArrayList()
    private val dataItemTool      :ArrayList<ItemToolData>       = Lists.newArrayList()
    private val dataRecipes       :ArrayList<Recipe>             = Lists.newArrayList()
    private val dataDeleteRecipes :ArrayList<Ingredient>         = Lists.newArrayList()

    private val dataGroup         :ArrayList<Group>              = Lists.newArrayList()

    init {
        data.listFiles()!!.forEach(this::scanFile)

        // Start
        this.dataDeleteRecipes.forEach { it.toItemStack()?.let(RemoveRecipes::removeAllRecipe) }
        this.dataItem.forEach(ItemData::registryItem)

    }

    private fun scanFile(file: File) {
        fun <T> handle(list: ArrayList<T>) {
            if (file.isDirectory) for(f in file.listFiles()!!) JsonHandler.dataScan(f, list)
            else JsonHandler.dataScan(file, list)
        }

        val name: String = if (file.isDirectory) file.name else file.nameWithoutExtension
        if (name.endsWith("s")) name.substring(0, name.length - 1)

        when (name.lowercase()) {
            "Group".lowercase()         -> handle(dataGroup)
            "DeleteRecipe".lowercase()  -> handle(dataDeleteRecipes)
            "Item".lowercase()          -> handle(dataItem)
            "ItemTool".lowercase()      -> handle(dataItemTool)
            "Block".lowercase()         -> handle(dataBlock)
            "Recipe".lowercase()        -> handle(dataRecipes)
            "Biome".lowercase()         -> handle(dataBiomes)
        }
    }
}