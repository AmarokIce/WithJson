package club.someoneice.ovo.json.reader

import club.someoneice.ovo.core.DataList
import club.someoneice.ovo.json.Sandman
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class Recipes {
    fun init(filePath: File) {
        val type = object: TypeToken<List<club.someoneice.ovo.data.Recipes>>() {}.type

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
            val list: List<club.someoneice.ovo.data.Recipes> = gson.fromJson(output, type)
            for (i in list) DataList.dataRecipe.add(i)

        } catch (_: Exception) {
            Sandman.nullSandman()
        }
    }
}