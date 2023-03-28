package club.someoneice.ovo.core

import club.someoneice.ovo.json.Sandman
import club.someoneice.ovo.json.reader.*

import club.someoneice.ovo.mana.MMMCoreRunner
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader

class CoreRunner {
    private val gson: Gson = Gson()
    private val basePath: String = "${System.getProperty("user.dir")}\\ovo"
    private val packageInfoFilePath: File = File("$basePath\\ovo_package.json")
    private var packagePathList = ArrayList<String>()

    private val group           = Group()
    private val item            = Item()
    private val food            = ItemFoods()
    private val gift            = ItemGifts()
    private val tool            = ItemTools()
    private val swords          = ItemSwords()
    private val block           = ItemBlocks()
    private val recipes         = Recipes()
    private val delete_recipes  = DeleteRecipes()
    private val biomes          = Biomes()

    init {
        try {
            if (!packageInfoFilePath.isFile)
                OVOMain.Logger.warn("ovo_package.json is not find!")
            else {
                readerPackage()
                init()
            }
        } catch (_: FileNotFoundException) {
            throw FileNotFoundException("package.json is not find!")
        }
    }

    private fun init() {
        if (packagePathList.size < 1) return
        for (i in packagePathList){
            if (!File("${basePath}\\${i}").isDirectory || !File("${basePath}\\${i}\\info.json").isFile) {
                continue
            } else {
                OVOMain.Logger.info("Such pockage: ${basePath}\\${i}")
                val modid: String? = readInfo(File("${basePath}\\${i}\\info.json"))
                getFile("${basePath}\\${i}")

                DataProcessor(modid).init()

                // Now we will read the manametalmod 's recipe in this package.
                if (OVOMain.ManaMetalModInstall) MMMCoreRunner("${basePath}\\${i}")

                // Finish read this package, and now clear the DataList.
                DataClearer()
            }
        }
    }

    private fun getFile(baseFile: String) {
        if (File("${baseFile}\\Group.json").isFile) {
            OVOMain.Logger.info("Such File: ${baseFile}\\Group.json")
            // group.init(File("${baseFile}\\Group.json"))
            group.init(File("${baseFile}\\Group.json"))
        }


        if (File("${baseFile}\\Item.json").isFile) {
            item.init(File("${baseFile}\\Item.json"))
        } else if (File("${baseFile}\\Item").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\Item").listFiles() as Array<File>
            for (i in fileList) {
                item.init(i)
            }
        }

        if (File("${baseFile}\\ItemFood.json").isFile) {
            food.init(File("${baseFile}\\ItemFood.json"))
        } else if (File("${baseFile}\\ItemFood").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\ItemFood").listFiles() as Array<File>
            for (i in fileList) {
                food.init(i)
            }
        }

        if (File("${baseFile}\\ItemGift.json").isFile) {
            gift.init(File("${baseFile}\\ItemGift.json"))
        } else if (File("${baseFile}\\ItemGift").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\ItemGift").listFiles() as Array<File>
            for (i in fileList) {
                gift.init(i)
            }
        }

        if (File("${baseFile}\\ItemTool.json").isFile) {
            tool.init(File("${baseFile}\\ItemTool.json"))
        } else if (File("${baseFile}\\ItemTool").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\ItemTool").listFiles() as Array<File>
            for (i in fileList) {
                tool.init(i)
            }
        }

        if (File("${baseFile}\\ItemWeapons.json").isFile) {
            swords.init(File("${baseFile}\\ItemWeapons.json"))
        } else if (File("${baseFile}\\ItemWeapons").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\ItemWeapons").listFiles() as Array<File>
            for (i in fileList) {
                swords.init(i)
            }
        }

        if (File("${baseFile}\\Block.json").isFile) {
            block.init(File("${baseFile}\\Block.json"))
        } else if (File("${baseFile}\\Block").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\Block").listFiles() as Array<File>
            for (i in fileList) {
                block.init(i)
            }
        }

        if (File("${baseFile}\\Recipe.json").isFile) {
            recipes.init(File("${baseFile}\\Recipe.json"))
        } else if (File("${baseFile}\\Recipe").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\Recipe").listFiles() as Array<File>
            for (i in fileList) {
                recipes.init(i)
            }
        }

        if (File("${baseFile}\\DeleteRecipe.json").isFile) {
            delete_recipes.init(File("${baseFile}\\DeleteRecipe.json"))
        } else if (File("${baseFile}\\DeleteRecipe").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\DeleteRecipe").listFiles() as Array<File>
            for (i in fileList) {
                delete_recipes.init(i)
            }
        }

        if (File("${baseFile}\\Biomes.json").isFile) {
            biomes.init(File("${baseFile}\\Biomes.json"))
        } else if (File("${baseFile}\\Biomes").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\Biomes").listFiles() as Array<File>
            for (i in fileList) {
                biomes.init(i)
            }
        }
    }

    private fun readInfo(infoPath: File): String? {
        val text = StringBuffer()
        val buffreader = BufferedReader(FileReader(infoPath))

        try {
            while (true) {
                val str = buffreader.readLine()
                if (str == null) break else text.append(str.toString())
            }

            buffreader.close()
            val output: String = text.toString()
            val infoList: HashMap<String, String> =
                gson.fromJson(output, (object : TypeToken<HashMap<String, String>>() {}.type))
            if (infoList.containsKey("modid")) {
                OVOMain.Logger.info("Find modid ${infoList["modid"]}")
                return infoList["modid"]
            }

        } catch (_: Exception) {
            Sandman.nullSandman()
        }

        return Sandman.missingNo() as String?
    }

    private fun readerPackage() {
        val text = StringBuffer()
        val buffreader = BufferedReader(FileReader(packageInfoFilePath))

        try {
            while (true) {
                val str = buffreader.readLine()
                if (str == null) break else text.append(str.toString())
            }

            buffreader.close()
            val output: String = text.toString()
            packagePathList = gson.fromJson(output, (object : TypeToken<List<String>>() {}.type))

        } catch (_: Exception) {
            Sandman.nullSandman()
        }
    }
}