package club.someoneice.ovo.json.reader

import club.someoneice.ovo.core.DataList
import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.data.Group
import club.someoneice.ovo.json.helper.JsonTypeGetter
import club.someoneice.ovo.json.helper.JsonTypeHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

class Group: JsonTypeHelper() {
    override fun getToolType(typeGetter: JsonTypeGetter, filePath: File) {
        typeGetter.getType("group", filePath)
    }

    override fun init(filePath: File) {
        val type = object: TypeToken<List<Group>>() {}.type

        val gson = Gson()
        val text = StringBuffer()
        val buffreader = BufferedReader(FileReader(filePath))

        try {
            while (true) {
                val str = buffreader.readLine()
                if (str == null) break else text.append(str.toString())
            }
            buffreader.close()
            val output: String = text.toString()

            OVOMain.Logger.info(output)
            OVOMain.Logger.info("START READ GROUP !")


            val list: List<Group> = gson.fromJson(output, type)
            for (i in list) DataList.dataGroup.add(i)


        } catch (_: Exception) {
            OVOMain.Logger.error("Cannot read a Group data!")
        }
    }
}