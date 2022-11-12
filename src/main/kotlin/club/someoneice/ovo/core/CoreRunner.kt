package club.someoneice.ovo.core

import club.someoneice.ovo.data.helper.JsonReaderType
import club.someoneice.ovo.json.Sandman
import club.someoneice.ovo.json.helper.JsonProcessor
import club.someoneice.ovo.json.reader.*
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.util.*
import kotlin.collections.List

class CoreRunner {
    private val gson: Gson = Gson()
    private val basePath: String = "${System.getProperty("user.dir")}\\ovo"
    private val packageInfoFilePath: File = File("$basePath\\ovo_package.json")
    private var packagePathList = ArrayList<String>()

    private val group   = Group()
    private val item    = Item()
    private val food    = ItemFoods()
    private val gift    = ItemGifts()
    private val tool    = ItemTools()
    private val swords  = ItemSwords()
    private val block   = ItemBlocks()
    private val recipes = Recipes()

    private val JsonProcessor = JsonProcessor(group, item, block, food, gift, swords, tool, recipes)

    init {
        try {
            if (!packageInfoFilePath.isFile) {
                OVOMain.Logger.warn("ovo_package.json is not find!")
                Sandman.nullSandman()
            } else {
                readerPackage()
                init()
            }
        } catch (_: FileNotFoundException) {
            throw FileNotFoundException("package.json is not find!")
            Sandman.nullSandman()
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

                // Finish read this package, and now clear the DataList.
                DataClearer()
            }
        }
    }

    private fun getFile(baseFile: String) {
        if (File("${baseFile}\\Group.json").isFile) {
            OVOMain.Logger.info("Such File: ${baseFile}\\Group.json")
            // group.getToolType(JsonProcessor, File("${baseFile}\\Group.json"))
            group.init(File("${baseFile}\\Group.json"))
        }


        if (File("${baseFile}\\Item.json").isFile) {
            item.getToolType(JsonProcessor, File("${baseFile}\\Item.json"))
        } else if (File("${baseFile}\\Item").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\Item").listFiles() as Array<File>
            for (i in fileList) {
                item.getToolType(JsonProcessor, i)
            }
        }

        if (File("${baseFile}\\ItemFood.json").isFile) {
            food.getToolType(JsonProcessor, File("${baseFile}\\ItemFood.json"))
        } else if (File("${baseFile}\\ItemFood").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\ItemFood").listFiles() as Array<File>
            for (i in fileList) {
                food.getToolType(JsonProcessor, i)
            }
        }

        if (File("${baseFile}\\ItemGift.json").isFile) {
            gift.getToolType(JsonProcessor, File("${baseFile}\\ItemGift.json"))
        } else if (File("${baseFile}\\ItemGift").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\ItemGift").listFiles() as Array<File>
            for (i in fileList) {
                gift.getToolType(JsonProcessor, i)
            }
        }

        if (File("${baseFile}\\ItemTool.json").isFile) {
            tool.getToolType(JsonProcessor, File("${baseFile}\\ItemTool.json"))
        } else if (File("${baseFile}\\ItemTool").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\ItemTool").listFiles() as Array<File>
            for (i in fileList) {
                tool.getToolType(JsonProcessor, i)
            }
        }

        if (File("${baseFile}\\ItemWeapons.json").isFile) {
            swords.getToolType(JsonProcessor, File("${baseFile}\\ItemWeapons.json"))
        } else if  (File("${baseFile}\\ItemWeapons").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\ItemWeapons").listFiles() as Array<File>
            for (i in fileList) {
                swords.getToolType(JsonProcessor, i)
            }
        }

        if (File("${baseFile}\\Block.json").isFile) {
            block.getToolType(JsonProcessor, File("${baseFile}\\Block.json"))
        } else if (File("${baseFile}\\Block").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\Block").listFiles() as Array<File>
            for (i in fileList) {
                block.getToolType(JsonProcessor, i)
            }
        }

        if (File("${baseFile}\\Recipe.json").isFile) {
            recipes.getToolType(JsonProcessor, File("${baseFile}\\Recipe.json"))
        } else if (File("${baseFile}\\Recipe").isDirectory) {
            val fileList: Array<File> = File("${baseFile}\\Recipe").listFiles() as Array<File>
            for (i in fileList) {
                recipes.getToolType(JsonProcessor, i)
            }
        }
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
            packagePathList = gson.fromJson(output, (object: TypeToken<List<String>> () {} .type) )

        } catch (_: Exception) {
            Sandman.nullSandman()
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
            val infoList: HashMap<String, String> = gson.fromJson(output, (object: TypeToken<HashMap<String, String>> () {} .type) )
            if (infoList.containsKey("modid")) {
                OVOMain.Logger.info("Find modid ${infoList["modid"]}")
                return infoList["modid"]
            }

        } catch (_: Exception) {
            Sandman.nullSandman()
        }

        return Sandman.missingNo() as String?
    }
}