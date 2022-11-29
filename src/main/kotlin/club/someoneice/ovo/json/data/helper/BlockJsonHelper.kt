package club.someoneice.ovo.json.data.helper

import club.someoneice.ovo.IDataGem
import club.someoneice.ovo.data.BlockData
import club.someoneice.ovo.json.data.JsonData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.Writer
import java.nio.file.Files
import java.nio.file.Paths

class BlockJsonHelper: JsonData() {

    override fun startIn(modid: String, dataSet: IDataGem) {
        val basePath = "${System.getProperty("user.dir")}\\ovo\\resources\\assets\\$modid"
        if (!File(basePath).exists()) File(basePath).mkdir()
        if (!File("${basePath}\\blockstates").exists()) File("${basePath}\\blockstates").mkdir()
        if (!File("${basePath}\\models\\block").exists()) File("${basePath}\\models\\block").mkdir()
        if (!File("${basePath}\\textures\\block").exists()) File("${basePath}\\textures\\block").mkdir()

        if (dataSet is BlockData) {
            File("${basePath}\\blockstates\\${dataSet.name}.json").createNewFile()
            File("${basePath}\\models\\block\\${dataSet.name}.json").createNewFile()

            val gson = GsonBuilder().setPrettyPrinting().create()

            // State
            val StateMap = HashMap<String, Any>()
            val StateModelMap = HashMap<String, Any>()
            val ModelMap = HashMap<String, Any>()
            ModelMap["model"] = "${modid}:block/${dataSet.name}"
            StateModelMap[""] = ModelMap
            StateMap["variants"] = StateModelMap

            val writerState: BufferedWriter = Files.newBufferedWriter(Paths.get("${basePath}\\blockstates\\${dataSet.name}.json"))
            writerState.append(gson.toJson(StateMap))
            writerState.close()

            // Model
            val BaseMap = HashMap<String, Any>()
            val TexturesMap = HashMap<String, String>()
            BaseMap["parent"] = "minecraft:block/cube_all"
            TexturesMap["all"] = "${modid}:block/${dataSet.name}"
            BaseMap["textures"] = TexturesMap

            val writerModel: BufferedWriter = Files.newBufferedWriter(Paths.get("${basePath}\\models\\block\\${dataSet.name}.json"))
            writerModel.append(gson.toJson(BaseMap))
            writerModel.close()
        }
    }
}