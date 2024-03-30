package club.someoneice.ovov1.generator

import club.someoneice.ovov1.json.JsonReaderBean
import club.someoneice.ovov1.util.gson
import java.io.File
object GeneratorMain {
    lateinit var baseDir: File
    @JvmStatic
    fun main(vararg args: String) {
        println("OVO Generator is running ...")
        while (true) {
            print("请输入OVO文件夹的所在位置: ")
            val ovoDir = File(readlnOrNull() ?: continue)
            if (!ovoDir.exists() || !ovoDir.isDirectory) continue

            baseDir = ovoDir
            break
        }

        clearConsole()

    }

    fun checkInfo() {
        println("正在检查文件夹目录...")
        val packagePathList = ArrayList<String>()
        val packageInfoFilePath = File(baseDir, "ovo_package.json")
        if (packageInfoFilePath.exists() && packageInfoFilePath.isFile)
            JsonReaderBean<String>().init(packageInfoFilePath, packagePathList)
        else packageInfoFilePath.createNewFile()
        packagePathList.forEach(::println)

        println("输入对应的名称以打开文件夹（如不存在则会创建）")
        val name: String
        while (true) {
            print("请输入名称: ")
            name = readlnOrNull() ?: continue
            break
        }

        if (!packagePathList.contains(name)) {
            packagePathList.add(name)
            packageInfoFilePath.writeText(gson.toJson(packagePathList))
        }

        val fileDir = File(packageInfoFilePath, name)
        if (!fileDir.exists() || !fileDir.isDirectory) fileDir.mkdir()

        clearConsole()

        join(fileDir)
    }

    fun join(dir: File) {
        println("输入名称进入对应的资料夹：")
        println("Group  - 创造模式物品栏")
        println("Item   - 物品")
        println("Food   - 食物")
        println("Gift   - 礼包")
        println("Tool   - 工具")
        println("Sword  - 武器")
        println("Block  - 方块")
        println("Recipe - 食谱")


    }

    private fun clearConsole() {
        val operatingSystem = System.getProperty("os.name")
        if (operatingSystem.contains("Windows")) {
            val process = ProcessBuilder("cmd", "/c", "cls");
            val startProcess = process.inheritIO().start();
            startProcess.waitFor();
        } else {
            val process = ProcessBuilder("clear");
            val startProcess = process.inheritIO().start();
            startProcess.waitFor();
        }
    }
}