package club.someoneice.ovo.json.helper

import java.io.File

abstract class JsonTypeGetter {
    abstract fun getType(typeGetter: String, filePath: File)
}