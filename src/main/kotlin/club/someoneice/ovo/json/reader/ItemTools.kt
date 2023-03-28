package club.someoneice.ovo.json.reader

import club.someoneice.ovo.core.obj.DataList
import club.someoneice.ovo.data.ItemTool
import club.someoneice.ovo.json.Sandman
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.nio.file.Files

class ItemTools {
    fun init(filePath: File) {
        val type = object: TypeToken<List<ItemTool>>() {}.type

        val gson = Gson()
        val reader = Files.newInputStream(filePath.toPath())

        try {
            val byte = ByteArray(filePath.length().toInt())
            reader.read(byte)
            reader.close()

            val output = String(byte)
            val list: List<ItemTool> = gson.fromJson(output, type)
            for (i in list) DataList.dataItemTool.add(i)

        } catch (_: Exception) {
            Sandman.nullSandman()
        }
    }
}