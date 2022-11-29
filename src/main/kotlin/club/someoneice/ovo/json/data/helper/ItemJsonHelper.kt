package club.someoneice.ovo.json.data.helper

import club.someoneice.ovo.IDataGem
import club.someoneice.ovo.data.*
import club.someoneice.ovo.json.data.JsonData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Paths

class ItemJsonHelper: JsonData() {
    override fun startIn(modid: String, dataSet: IDataGem) {
        if (dataSet is ItemData) {
            dataProcessor(modid, dataSet.name)
        }

        if (dataSet is ItemKnife) {
            dataProcessor(modid, dataSet.name)
        }

        if (dataSet is ItemFoods) {
            dataProcessor(modid, dataSet.name)
        }

        if (dataSet is ItemTool) {
            dataProcessor(modid, dataSet.name)
        }

        if (dataSet is ItemGift) {
            dataProcessor(modid, dataSet.name)
        }
    }

    private fun dataProcessor(modid: String, name: String) {
        val basePath = "${System.getProperty("user.dir")}\\ovo\\resources\\assets\\$modid"

        if (!File(basePath).exists()) File(basePath).mkdir()
        if (!File("${basePath}\\models\\item").exists()) File("${basePath}\\models\\item").mkdir()
        if (!File("${basePath}\\textures\\item").exists()) File("${basePath}\\textures\\item").mkdirs()

        File("${basePath}\\models\\item\\${name}.json").createNewFile()

        val BaseMap = HashMap<String, Any>()
        val TexturesMap = HashMap<String, String>()
        BaseMap["parent"] = "item/generated"
        TexturesMap["layer0"] = "${modid}:item/${name}"
        BaseMap["textures"] = TexturesMap

        val gson = GsonBuilder().setPrettyPrinting().create()
        val writer: BufferedWriter = Files.newBufferedWriter(Paths.get("${basePath}\\models\\item\\${name}.json"))
        writer.append(gson.toJson(BaseMap))
        writer.close()
    }
}