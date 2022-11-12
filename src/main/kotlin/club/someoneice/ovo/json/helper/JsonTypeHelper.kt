package club.someoneice.ovo.json.helper

import java.io.File

abstract class JsonTypeHelper {
    abstract fun getToolType(typeGetter: JsonTypeGetter, filePath: File)
}