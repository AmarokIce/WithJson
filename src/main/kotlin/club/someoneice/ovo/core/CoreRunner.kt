package club.someoneice.ovo.core

import club.someoneice.ovo.core.`object`.DataList
import club.someoneice.ovo.data.*
import club.someoneice.ovo.json.JsonReaderBean
import club.someoneice.ovo.mana.MMMCoreRunner
import java.io.File
import java.io.FileNotFoundException

class CoreRunner {
    private val basePath: String = "${System.getProperty("user.dir")}\\ovo"
    private var packagePathList = ArrayList<String>()

    // Although it may seem cumbersome, it is easier to discern than internal inference, and there will be no surprises.
    // Internal inference is good but this is too much type... It's easy to confuse.
    private val group           = JsonReaderBean<Group>()
    private val item            = JsonReaderBean<ItemData>()
    private val food            = JsonReaderBean<ItemFood>()
    private val gift            = JsonReaderBean<ItemGift>()
    private val tool            = JsonReaderBean<ItemTool>()
    private val swords          = JsonReaderBean<ItemWeapon>()
    private val block           = JsonReaderBean<BlockData>()
    private val recipes         = JsonReaderBean<Recipe>()
    private val delete_recipes  = JsonReaderBean<String>()
    private val biomes          = JsonReaderBean<BiomesData>()

    init {
        try {
            val packageInfoFilePath = File("$basePath\\ovo_package.json")
            if (packageInfoFilePath.isFile) {
                JsonReaderBean<String>().init(packageInfoFilePath, packagePathList)
                init()
            } else OVOMain.Logger.warn("ovo_package.json is not find!")
        } catch (_: FileNotFoundException) {
            throw FileNotFoundException("package.json is not find!")
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

                DataProcessor(modid).init()
                if (OVOMain.ManaMetalModInstall) MMMCoreRunner("${basePath}\\${i}")
                DataList.init()
            }
        }
    }

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
                "Recipe".lowercase()        -> handle(file, recipes,        DataList.dataRecipes)
                "DeleteRecipe".lowercase()  -> handle(file, delete_recipes, DataList.dataDeleteRecipes)
                "Biomes".lowercase()        -> handle(file, biomes,         DataList.dataBiomes)
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