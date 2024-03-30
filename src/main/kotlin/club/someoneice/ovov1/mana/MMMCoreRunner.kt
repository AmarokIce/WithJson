package club.someoneice.ovov1.mana

import club.someoneice.ovov1.json.JsonReaderBean
import club.someoneice.ovov1.mana.data.MMMCraftTable
import club.someoneice.ovov1.mana.data.MMMMapData
import club.someoneice.ovov1.mana.data.MMMRemoveRecipes
import java.io.File

class MMMCoreRunner(baseFile: String) {
    private val basePath: File = File("${baseFile}\\manametal")

    private val deleteRecipe    = JsonReaderBean<MMMRemoveRecipes>()
    private val recipe          = JsonReaderBean<MMMCraftTable>()
    private val data            = JsonReaderBean<MMMMapData>()
    private val dungeon         = JsonReaderBean<String>()

    init {
        scanning()
        MMMProcessor()
        MMMDataList.clean()
    }

    private fun scanning() {
        if (!basePath.exists() || !basePath.isDirectory) return
        for (file in basePath.listFiles()!!) {
            val name: String = if (file.isDirectory) file.name else file.nameWithoutExtension
            when (name.lowercase()) {
                "DeleteRecipe"  -> handle(file, deleteRecipe,   MMMDataList.dataRemove)
                "GoldItem"      -> handle(file, data,           MMMDataList.dataGoldItem)
                "FrozenItem"    -> handle(file, data,           MMMDataList.dataFrozenItem)
                "ItemValue"     -> handle(file, data,           MMMDataList.dataItemValue)
                "DungeonItem"   -> handle(file, dungeon,        MMMDataList.dataDungeon)
                "Recipes"       -> handle(file, recipe,         MMMDataList.dataRecipes)
            }
        }
    }

    private fun <T> handle(file: File, json: JsonReaderBean<T>, list: ArrayList<T>) {
        if (file.isDirectory) for(f in file.listFiles()!!) json.init(f, list)
        else json.init(file, list)
    }
}