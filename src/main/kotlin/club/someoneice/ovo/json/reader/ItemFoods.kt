package club.someoneice.ovo.json.reader

import club.someoneice.ovo.core.DataList
import club.someoneice.ovo.data.ItemFoods
import club.someoneice.ovo.json.Sandman
import club.someoneice.ovo.json.helper.JsonTypeGetter
import club.someoneice.ovo.json.helper.JsonTypeHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.nio.file.Files

class ItemFoods: JsonTypeHelper() {
    override fun getToolType(typeGetter: JsonTypeGetter, filePath: File) {
        typeGetter.getType("item_food", filePath)
    }

    override fun init(filePath: File) {
        val type = object: TypeToken<List<ItemFoods>>() {}.type

        val gson = Gson()
        val reader = Files.newInputStream(filePath.toPath())

        try {
            val byte = ByteArray(filePath.length().toInt())
            reader.read(byte)

            reader.close()
            val output = String(byte)
            val list: List<ItemFoods> = gson.fromJson(output, type)
            for (i in list) DataList.dataItemFood.add(i)


        } catch (_: Exception) {
            Sandman.nullSandman()
        }
    }
}