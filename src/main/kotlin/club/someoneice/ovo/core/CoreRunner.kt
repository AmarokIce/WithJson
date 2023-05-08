package club.someoneice.ovo.core

import club.someoneice.ovo.OVOMain
import club.someoneice.ovo.core.`object`.DataList
import club.someoneice.ovo.data.*
import club.someoneice.ovo.json.JsonReaderBean
import club.someoneice.ovo.json.Sandman
import com.google.gson.Gson
import java.io.File
import java.io.FileNotFoundException
import java.util.*

class CoreRunner {
    private val basePath: String = "${System.getProperty("user.dir")}\\ovo"
    private val packageInfoFilePath: File = File("$basePath\\ovo_package.json")
    private var packagePathList = ArrayList<String>()

    private val group   = JsonReaderBean<Group>()
    private val item    = JsonReaderBean<ItemData>()
    private val food    = JsonReaderBean<ItemFood>()
    private val gift    = JsonReaderBean<ItemGift>()
    private val tool    = JsonReaderBean<ItemTool>()
    private val swords  = JsonReaderBean<ItemWeapon>()
    private val block   = JsonReaderBean<BlockData>()

    init {
        try {
            if (packageInfoFilePath.isFile) {
                JsonReaderBean<String>().init(packageInfoFilePath, packagePathList)
                init()
            } else OVOMain.Logger.info("ovo_package.json is not find!")
        } catch (_: FileNotFoundException) {
            Sandman.nullSandman()
        }
    }

    private fun init() {
        for (i in packagePathList) {
            if (File("${basePath}\\${i}").isDirectory || !File("${basePath}\\${i}\\info.json").isFile) {
                OVOMain.Logger.info("Such pockage: ${basePath}\\${i}")
                val modid: String? =
                    (JsonReaderBean<HashMap<String, String>>().readWithOriginal(File("${basePath}\\${i}\\info.json")) as HashMap<String, String>)["modid"]

                // Scanning the OVOPackage! And add all we want!
                scanning("${basePath}\\${i}")

                DataProcessor(modid)
                DataList.init()
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun scanning(baseFile: String) {
        val initPath = File(baseFile)
        if (!initPath.exists() || !initPath.isDirectory) return
        for (file in initPath.listFiles()!!) {
            val name: String = if (file.isDirectory) file.name else file.nameWithoutExtension
            when (name.lowercase()) {
                "Item".lowercase()          -> handle(file, item,           DataList.dataItem)
                "ItemFood".lowercase()      -> handle(file, food,           DataList.dataItemFood)
                "ItemGift".lowercase()      -> handle(file, gift,           DataList.dataItemGift)
                "ItemTool".lowercase()      -> handle(file, tool,           DataList.dataItemTool)
                "ItemWeapons".lowercase()   -> handle(file, swords,         DataList.dataItemWeapons)
                "Block".lowercase()         -> handle(file, block,          DataList.dataBlock)
                "Group".lowercase()         -> {
                    if (file.isDirectory) continue
                    group.init(file, DataList.dataGroup)
                }
            }
        }
    }

    private fun <T> handle(file: File, json: JsonReaderBean<T>, list: ArrayList<T>) {
        if (file.isDirectory) for(f in file.listFiles()!!) json.init(f, list)
        else json.init(file, list)
    }
}