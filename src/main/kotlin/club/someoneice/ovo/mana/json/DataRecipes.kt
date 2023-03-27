package club.someoneice.ovo.mana.json

import club.someoneice.ovo.json.Sandman
import club.someoneice.ovo.mana.MMMDataList
import club.someoneice.ovo.mana.data.MMMCraftTable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.nio.file.Files

class DataRecipes(filePath: File) {
    init {
        val type = object: TypeToken<List<MMMCraftTable>>() {}.type

        val gson = Gson()
        val reader = Files.newInputStream(filePath.toPath())

        try {
            val byte = ByteArray(filePath.length().toInt())
            reader.read(byte)
            reader.close()

            val output = String(byte)
            val list: List<MMMCraftTable> = gson.fromJson(output, type)
            for (i in list) MMMDataList.dataRecipes.add(i)

        } catch (_: Exception) {
            Sandman.nullSandman()
        }
    }
}