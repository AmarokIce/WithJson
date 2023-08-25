package club.someoneice.ovo.json

import club.someoneice.json.JSON
import java.lang.reflect.ParameterizedType

object Json5ReaderBean {
    private val json: JSON = JSON.json5
    fun <T>json5Reader(file: String, originalList: ArrayList<T>) {
        val dataClazz: Class<out T> = (originalList.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<out T>
        originalList.addAll(json.tryPullAsClassList(dataClazz, file))
    }
}