package club.someoneice.ovo.util

import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.reflect.ParameterizedType

object JsonHandler {
    fun <T>dataScan(file: File, list: ArrayList<T>) {
        if (file.name.endsWith(".json5")) json5Reader(file.readText(), list)
        else jsonReader(file.readText(), list)
    }

    private fun <T>json5Reader(text: String, list: ArrayList<T>) {
        val dataClazz: Class<out T> = (list.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<out T>
        list.addAll(json.tryPullAsClassList(dataClazz, text))
    }

    private fun <T>jsonReader(text: String, list: ArrayList<T>) {
        val type = object: TypeToken<List<T>>() {}.type
        list.addAll(gson.fromJson(text, type) as List<T>)
    }
}