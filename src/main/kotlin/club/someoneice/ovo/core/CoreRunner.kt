package club.someoneice.ovo.core

import club.someoneice.ovo.core.`object`.DataList
import club.someoneice.ovo.data.*
import club.someoneice.ovo.json.JsonReaderBean
import club.someoneice.ovo.mana.MMMCoreRunner
import club.someoneice.ovo.util.gson
import com.google.gson.reflect.TypeToken
import cpw.mods.fml.common.Loader
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files

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
        internal val tool            = JsonReaderBean<ItemTool>()
        internal val swords          = JsonReaderBean<ItemWeapon>()
        internal val block           = JsonReaderBean<BlockData>()
        internal val recipes         = JsonReaderBean<Recipe>()
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
            val reader = Files.newInputStream(file.toPath())

            return try {
                val byte = ByteArray(file.length().toInt())
                reader.read(byte)
                reader.close()

                gson.fromJson(String(byte), type) as HashMap<String, String>
            } catch (_: Exception) {
                null
            }
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

        /**
         * Read the file from Jar.
         */
        if (Loader.isModLoaded("pineapple_recipe_book")) {
            JarRunner()
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