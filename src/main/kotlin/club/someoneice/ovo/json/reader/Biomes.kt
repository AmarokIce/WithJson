package club.someoneice.ovo.json.reader

import club.someoneice.ovo.core.obj.DataList
import club.someoneice.ovo.json.Sandman
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.nio.file.Files

class Biomes {
    fun init(filePath: File) {
        val type = object: TypeToken<List<String>>() {}.type

        val gson = Gson()
        val reader = Files.newInputStream(filePath.toPath())

        try {
            val byte = ByteArray(filePath.length().toInt())
            reader.read(byte)
            reader.close()

            val output = String(byte)
            DataList.dataBiomes = gson.fromJson(output, type)

        } catch (_: Exception) {
            Sandman.nullSandman()
        }
    }
}