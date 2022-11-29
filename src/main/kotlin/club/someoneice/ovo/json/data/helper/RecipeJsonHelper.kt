package club.someoneice.ovo.json.data.helper

import club.someoneice.ovo.IDataGem
import club.someoneice.ovo.data.Recipes
import club.someoneice.ovo.json.data.JsonData
import club.someoneice.ovo.util.Util
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Paths

class RecipeJsonHelper: JsonData() {
    var modid: String? = null
    var dataSet: Recipes? = null
    val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    val basePath = "${System.getProperty("user.dir")}\\ovo\\resources\\data\\$modid"

    override fun startIn(modid: String, dataSet: IDataGem) {
        if (!File(basePath).exists()) File(basePath).mkdir()
        if (!File("${basePath}\\recipes").exists()) File("${basePath}\\recipes").mkdir()

        if (dataSet is Recipes) {
            this.modid = modid
            this.dataSet = dataSet

            when (dataSet.type) {
                "crafting_shapeless"    -> craftingShapeless()
                "crafting_shaped"       -> craftingShaped()
                "smelting"              -> smeltingRecipe()
                "smoking"               -> smokingRecipe()
                "blast"                 -> blastRecipe()
                "campfire"              -> campfire()

                // TODO
                "pot"                   -> pot()
                "cut"                   -> cut()
            }
        }
    }

    private fun craftingShapeless() {
        val dataSet = this.dataSet!!
        val path = File("${basePath}\\recipes\\shapeless")
        if (!path.exists()) path.mkdir()

        File("${path}\\${ Util.getItemNameByRegistryName(dataSet.output)}").createNewFile()

        val BaseMap = HashMap<String, Any>()
        val RecipeList = ArrayList<Any>()
        val ResultMap = HashMap<String, Any>()

        for (i in dataSet.input) {
            val ItemMap = HashMap<String, String>()
            ItemMap["item"] = i
            RecipeList.add(ItemMap)
        }

        ResultMap["item"] = dataSet.output
        ResultMap["count"] = dataSet.count

        BaseMap["type"] = "minecraft:crafting_shapeless"
        BaseMap["ingredients"] = RecipeList
        BaseMap["result"] = ResultMap

        val writerModel: BufferedWriter = Files.newBufferedWriter(Paths.get("${path}\\${ Util.getItemNameByRegistryName(dataSet.output)}"))
        writerModel.append(gson.toJson(BaseMap))
        writerModel.close()
    }

    private fun craftingShaped() {
        val dataSet = this.dataSet!!
        var startRecipes = false
        var recipesCounter = 0
        val path = File("${basePath}\\recipes\\shaped")
        if (!path.exists()) path.mkdir()

        File("${path}\\${ Util.getItemNameByRegistryName(dataSet.output)}").createNewFile()

        val BaseMap = HashMap<String, Any>()
        val RecipeList = ArrayList<String>()
        val KeyMap = HashMap<String, Any>()
        val ResultMap = HashMap<String, Any>()

        RecipeList.add(dataSet.input[0])

        for (i in 1 .. dataSet.input.size) {
            if ((i == 1 && dataSet.input[0].length == dataSet.input[i].length && dataSet.input[i + 1].length <= 3) || i < 3 ) {
                RecipeList.add(dataSet.input[i])
            } else if (!startRecipes){
                startRecipes = true
                continue
            }

            if (startRecipes && i != recipesCounter) {
                val ItemMap = HashMap<String, String>()
                ItemMap["item"] = dataSet.input[i + 1]
                KeyMap[dataSet.input[i]] = ItemMap
                recipesCounter = i + 1
            }
        }

        ResultMap["item"] = dataSet.output
        ResultMap["count"] = dataSet.count

        BaseMap["type"] = "minecraft:crafting_shaped"
        BaseMap["ingredients"] = RecipeList
        BaseMap["key"] = KeyMap
        BaseMap["result"] = ResultMap

        val writerModel: BufferedWriter = Files.newBufferedWriter(Paths.get("${path}\\${ Util.getItemNameByRegistryName(dataSet.output)}"))
        writerModel.append(gson.toJson(BaseMap))
        writerModel.close()
    }

    private fun smeltingRecipe() {
        val dataSet = this.dataSet!!
        val path = File("${basePath}\\recipes\\smelting")
        if (!path.exists()) path.mkdir()

        File("${path}\\${ Util.getItemNameByRegistryName(dataSet.output)}").createNewFile()

        val BaseMap = HashMap<String, Any>()
        val KeyMap = HashMap<String, Any>()

        KeyMap["item"] = dataSet.input[0]

        BaseMap["type"] = "minecraft:smelting"
        BaseMap["ingredient"] = KeyMap
        BaseMap["result"] = dataSet.output
        BaseMap["experience"] = 0.5
        BaseMap["cookingtime"] = 200

        val writerModel: BufferedWriter = Files.newBufferedWriter(Paths.get("${path}\\${ Util.getItemNameByRegistryName(dataSet.output)}"))
        writerModel.append(gson.toJson(BaseMap))
        writerModel.close()
    }

    private fun smokingRecipe() {
        val dataSet = this.dataSet!!
        val path = File("${basePath}\\recipes\\smoking")
        if (!path.exists()) path.mkdir()

        File("${path}\\${ Util.getItemNameByRegistryName(dataSet.output)}").createNewFile()

        val BaseMap = HashMap<String, Any>()
        val KeyMap = HashMap<String, Any>()

        KeyMap["item"] = dataSet.input[0]

        BaseMap["type"] = "minecraft:smoking"
        BaseMap["ingredient"] = KeyMap
        BaseMap["result"] = dataSet.output
        BaseMap["experience"] = 0.5
        BaseMap["cookingtime"] = 100

        val writerModel: BufferedWriter = Files.newBufferedWriter(Paths.get("${path}\\${ Util.getItemNameByRegistryName(dataSet.output)}"))
        writerModel.append(gson.toJson(BaseMap))
        writerModel.close()
    }

    private fun blastRecipe() {
        val dataSet = this.dataSet!!
        val path = File("${basePath}\\recipes\\blasting")
        if (!path.exists()) path.mkdir()

        File("${path}\\${ Util.getItemNameByRegistryName(dataSet.output)}").createNewFile()

        val BaseMap = HashMap<String, Any>()
        val KeyMap = HashMap<String, Any>()

        KeyMap["item"] = dataSet.input[0]

        BaseMap["type"] = "minecraft:blasting"
        BaseMap["ingredient"] = KeyMap
        BaseMap["result"] = dataSet.output
        BaseMap["experience"] = 0.5
        BaseMap["cookingtime"] = 100

        val writerModel: BufferedWriter = Files.newBufferedWriter(Paths.get("${path}\\${ Util.getItemNameByRegistryName(dataSet.output)}"))
        writerModel.append(gson.toJson(BaseMap))
        writerModel.close()
    }

    private fun campfire() {
        val dataSet = this.dataSet!!
        val path = File("${basePath}\\recipes\\campfire")
        if (!path.exists()) path.mkdir()

        File("${path}\\${ Util.getItemNameByRegistryName(dataSet.output)}").createNewFile()

        val BaseMap = HashMap<String, Any>()
        val KeyMap = HashMap<String, Any>()

        KeyMap["item"] = dataSet.input[0]

        BaseMap["type"] = "minecraft:campfire_cooking"
        BaseMap["ingredient"] = KeyMap
        BaseMap["result"] = dataSet.output
        BaseMap["experience"] = 0.5
        BaseMap["cookingtime"] = 400

        val writerModel: BufferedWriter = Files.newBufferedWriter(Paths.get("${path}\\${ Util.getItemNameByRegistryName(dataSet.output)}"))
        writerModel.append(gson.toJson(BaseMap))
        writerModel.close()
    }

    private fun pot() {

    }

    private fun cut() {

    }
}