package club.someoneice.ovo.json

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.nio.file.Files

class JsonReaderBean<T> {
    val gson = Gson()

    /**
     * @param file Input the original json file, if input other file, it will not work.
     *
     * @return Input the originalList, the object for return will put into the originalList.
     * */
    fun init(file: File, originalList: ArrayList<T>) {
        val type = object: TypeToken<List<T>>() {}.type
        val reader = Files.newInputStream(file.toPath())

        try {
            val byte = ByteArray(file.length().toInt())
            reader.read(byte)
            reader.close()

            originalList.addAll(gson.fromJson(String(byte), type) as List<T>)
        } catch (_: Exception) {
            Sandman.nullSandman()
        }
    }

    fun readWithOriginal(file: File): T? {
        val type = object: TypeToken<T>() {}.type
        val reader = Files.newInputStream(file.toPath())

        return try {
            val byte = ByteArray(file.length().toInt())
            reader.read(byte)
            reader.close()
            gson.fromJson(String(byte), type) as T

        } catch (_: Exception) {
            null
        }
    }
}