package club.someoneice.ovo.generator

import java.io.File

object GeneratorMain {
    @JvmStatic
    fun main(args: Array<String>) {
        val path = if (args.size > 1) args[0] else askForPath()
        val file = File(path)
        if (!file.exists() || !file.isDirectory) throw IllegalArgumentException("Cannot scan a non-directory!")

        file.listFiles()?.forEach(GeneratorMain::scanFile)
    }

    private fun scanFile(file: File) {
        if (!file.endsWith(".json") && !file.endsWith(".json5")) return
        val fileName = file.nameWithoutExtension

    }

    private fun askForPath(): String {
        println("请输入路径：")
        while (true) {
            readln().apply { if (isEmpty()) return@apply else return@askForPath this }
        }
    }
}