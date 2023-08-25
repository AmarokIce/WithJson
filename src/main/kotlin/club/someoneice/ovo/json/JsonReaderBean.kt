package club.someoneice.ovo.json

import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.util.gson
import com.google.gson.reflect.TypeToken
import java.io.File

class JsonReaderBean<T> {
    /**
     * @param file Input the original json file, if input other file, it will not work.
     *
     * @return Input the originalList, the object for return will put into the originalList.
     * */
    fun init(file: File, originalList: ArrayList<T>) {
        this.init(file.reader().readText(), originalList)
    }

    fun init(file: String, originalList: ArrayList<T>) {
        if (OVOMain.PineapplePsychicInstall) Json5ReaderBean.json5Reader(file, originalList)
        else jsonReader(file, originalList)
    }

    private fun jsonReader(text: String, originalList: ArrayList<T>) {
        val type = object: TypeToken<List<T>>() {}.type
        originalList.addAll(gson.fromJson(text, type) as List<T>)
    }
}