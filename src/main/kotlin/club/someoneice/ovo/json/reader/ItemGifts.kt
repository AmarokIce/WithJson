package club.someoneice.ovo.json.reader

import club.someoneice.ovo.core.DataList
import club.someoneice.ovo.data.ItemGift
import club.someoneice.ovo.json.Sandman
import club.someoneice.ovo.json.helper.JsonTypeGetter
import club.someoneice.ovo.json.helper.JsonTypeHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ItemGifts: JsonTypeHelper() {
    override fun getToolType(typeGetter: JsonTypeGetter, filePath: File) {
        typeGetter.getType("item_gift", filePath)
    }

    override fun init(filePath: File) {
        val type = object: TypeToken<List<ItemGift>>() {}.type

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
            val list: List<ItemGift> = gson.fromJson(output, type)
            for (i in list) DataList.dataItemGift.add(i)

        } catch (_: Exception) {
            Sandman.nullSandman()
        }
    }
}