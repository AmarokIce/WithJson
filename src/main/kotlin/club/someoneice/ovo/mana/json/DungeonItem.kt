package club.someoneice.ovo.mana.json

import club.someoneice.ovo.json.Sandman
import club.someoneice.ovo.mana.MMMDataList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.nio.file.Files

class DungeonItem(filePath: File) {
    init {
        val type = object: TypeToken<List<String>>() {}.type

        val gson = Gson()
        val reader = Files.newInputStream(filePath.toPath())

        try {
            val byte = ByteArray(filePath.length().toInt())
            reader.read(byte)
            reader.close()

            val output = String(byte)
            val list: List<String> = gson.fromJson(output, type)
            for (i in list) MMMDataList.dataDungeon.add(i)

        } catch (_: Exception) {
            Sandman.nullSandman()
        }
    }
}