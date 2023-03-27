package club.someoneice.ovo.mana

import club.someoneice.ovo.mana.json.*
import java.io.File

class MMMCoreRunner(baseFile: String) {
    private val basePath: String = "${baseFile}\\manametal"

    init {
        dataReader()
        MMMProcessor()
        MMMDataList.clean()
    }

    private fun dataReader() {
        if (File("${basePath}\\DeleteRecipe.json").isFile)
            RecipeRemove(File("${basePath}\\DeleteRecipe.json"))
        else if (File("${basePath}\\DeleteRecipe").isDirectory) {
            val fileList: Array<File> = File("${basePath}\\DeleteRecipe").listFiles() as Array<File>
            for (i in fileList) RecipeRemove(i)
        }

        if (File("${basePath}\\GoldItem.json").isFile)
            GoldItem(File("${basePath}\\GoldItem.json"))
        else if (File("${basePath}\\GoldItem").isDirectory) {
            val fileList: Array<File> = File("${basePath}\\GoldItem").listFiles() as Array<File>
            for (i in fileList) GoldItem(i)
        }

        if (File("${basePath}\\FrozenItem.json").isFile)
            FrozenItem(File("${basePath}\\FrozenItem.json"))
        else if (File("${basePath}\\FrozenItem").isDirectory) {
            val fileList: Array<File> = File("${basePath}\\FrozenItem").listFiles() as Array<File>
            for (i in fileList) FrozenItem(i)
        }

        if (File("${basePath}\\ItemValue.json").isFile)
            SellItem(File("${basePath}\\ItemValue.json"))
        else if (File("${basePath}\\ItemValue").isDirectory) {
            val fileList: Array<File> = File("${basePath}\\ItemValue").listFiles() as Array<File>
            for (i in fileList) SellItem(i)
        }

        if (File("${basePath}\\DungeonItem.json").isFile)
            DungeonItem(File("${basePath}\\DungeonItem.json"))
        else if (File("${basePath}\\DungeonItem").isDirectory) {
            val fileList: Array<File> = File("${basePath}\\DungeonItem").listFiles() as Array<File>
            for (i in fileList) DungeonItem(i)
        }

        if (File("${basePath}\\Recipes.json").isFile)
            DataRecipes(File("${basePath}\\Recipes.json"))
        else if (File("${basePath}\\Recipes").isDirectory) {
            val fileList: Array<File> = File("${basePath}\\Recipes").listFiles() as Array<File>
            for (i in fileList) DataRecipes(i)
        }
    }
}