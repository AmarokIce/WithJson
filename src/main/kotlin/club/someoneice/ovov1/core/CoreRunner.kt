package club.someoneice.ovov1.core

import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.generator.old.*
import club.someoneice.ovov1.core.`object`.DataList
import club.someoneice.ovov1.json.JsonReaderBean
import club.someoneice.ovov1.mana.MMMCoreRunner
import club.someoneice.ovov1.util.gson
import com.google.gson.reflect.TypeToken
import cpw.mods.fml.common.Loader
import java.io.File
import java.io.FileNotFoundException

class CoreRunner {
    private val basePath: String = "${System.getProperty("user.dir")}\\ovo"
    private var packagePathList = ArrayList<String>()

    // Although it may seem cumbersome, it is easier to discern than internal inference, and there will be no surprises.
    // Internal inference is good but this is too much type... It's easy to confuse.
    companion object {
        internal val group           = JsonReaderBean<Group>()
        internal val item            = JsonReaderBean<ItemData>()
        internal val food            = JsonReaderBean<ItemFood>()
        internal val gift            = JsonReaderBean<ItemGift>()
        internal val tool            = JsonReaderBean<ItemToolData>()
        internal val sword           = JsonReaderBean<ItemWeapon>()
        internal val block           = JsonReaderBean<BlockData>()
        internal val recipe          = JsonReaderBean<Recipe>()
        internal val delete_recipes  = JsonReaderBean<String>()
        internal val biomes          = JsonReaderBean<BiomesData>()
    }

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
        fun readWithOriginal(file: File): HashMap<String, String>? {
            val type = object: TypeToken<HashMap<String, String>>() {}.type
            return gson.fromJson(file.readText(), type) as HashMap<String, String>?
        }

        for (i in packagePathList) {
            if (File("${basePath}\\${i}").isDirectory || !File("${basePath}\\${i}\\info.json").isFile) {
                OVOMain.Logger.info("Such pockage: ${basePath}\\${i}")

                val modid: String? = readWithOriginal(File("${basePath}\\${i}\\info.json"))?.let { it["modid"] }

                // Scanning the OVOPackage! And add all we want!
                scanning("${basePath}\\${i}")

                DataProcessor(modid).init()
                if (OVOMain.ManaMetalModInstall) MMMCoreRunner("${basePath}\\${i}")
                DataList.init()
            }
        }

        if (Loader.isModLoaded("pineapple_recipe_book")) JarRunner()
    }

    private fun scanning(baseFile: String) {
        val initPath = File(baseFile)
        if (!initPath.exists() || !initPath.isDirectory) return
        for (file in initPath.listFiles()!!) {
            val name: String = if (file.isDirectory) file.name else file.nameWithoutExtension
            if (name.endsWith("s")) name.substring(0, name.length - 1)
            when (name.lowercase()) {
                "Item".lowercase()          -> handle(file, item,           DataList.dataItem)
                "ItemFood".lowercase()      -> handle(file, food,           DataList.dataItemFood)
                "ItemGift".lowercase()      -> handle(file, gift,           DataList.dataItemGift)
                "ItemTool".lowercase()      -> handle(file, tool,           DataList.dataItemTool)
                "ItemWeapon".lowercase()    -> handle(file, sword,          DataList.dataItemWeapons)
                "Block".lowercase()         -> handle(file, block,          DataList.dataBlock)
                "Recipe".lowercase()        -> handle(file, recipe,         DataList.dataRecipes)
                "DeleteRecipe".lowercase()  -> handle(file, delete_recipes, DataList.dataDeleteRecipes)
                "Biome".lowercase()         -> handle(file, biomes,         DataList.dataBiomes)
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