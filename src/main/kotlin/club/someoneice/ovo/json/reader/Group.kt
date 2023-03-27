package club.someoneice.ovo.json.reader

import club.someoneice.ovo.core.DataList
import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.data.Group
import club.someoneice.ovo.json.helper.JsonTypeGetter
import club.someoneice.ovo.json.helper.JsonTypeHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.nio.file.Files

class Group: JsonTypeHelper() {
    override fun getToolType(typeGetter: JsonTypeGetter, filePath: File) {
        typeGetter.getType("group", filePath)
    }

    override fun init(filePath: File) {
        val type = object: TypeToken<List<Group>>() {}.type

        val gson = Gson()
        val reader = Files.newInputStream(filePath.toPath())

        try {
            val byte = ByteArray(filePath.length().toInt())
            reader.read(byte)
            reader.close()

            val output = String(byte)

            OVOMain.Logger.info(output)
            OVOMain.Logger.info("START READ GROUP !")


            val list: List<Group> = gson.fromJson(output, type)
            for (i in list) DataList.dataGroup.add(i)


        } catch (_: Exception) {
            OVOMain.Logger.error("Cannot read a Group data!")
        }
    }
}