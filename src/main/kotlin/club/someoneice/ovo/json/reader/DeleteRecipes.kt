package club.someoneice.ovo.json.reader

import club.someoneice.ovo.core.DataList
import club.someoneice.ovo.json.Sandman
import club.someoneice.ovo.json.helper.JsonTypeGetter
import club.someoneice.ovo.json.helper.JsonTypeHelper
import club.someoneice.ovo.util.Util.findItemByText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.nio.file.Files

class DeleteRecipes: JsonTypeHelper() {
    override fun getToolType(typeGetter: JsonTypeGetter, filePath: File) {
        typeGetter.getType("delete_recipes", filePath)
    }

    override fun init(filePath: File) {
        val type = object: TypeToken<List<String>>() {}.type

        val gson = Gson()
        val reader = Files.newInputStream(filePath.toPath())

        try {
            val byte = ByteArray(filePath.length().toInt())
            reader.read(byte)
            reader.close()

            for (i in gson.fromJson(String(byte), type) as List<String>) {
                if (findItemByText(i) != null) DataList.dataDeleteRecipes.add(findItemByText(i)!!)
            }

        } catch (_: Exception) {
            Sandman.nullSandman()
        }
    }
}