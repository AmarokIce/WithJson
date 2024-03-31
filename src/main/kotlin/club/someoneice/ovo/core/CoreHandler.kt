package club.someoneice.ovo.core

import club.someoneice.ovo.util.gson
import com.google.gson.reflect.TypeToken
import cpw.mods.fml.common.Loader
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import java.io.File

class CoreHandler {
    companion object {
        val baseFile: File = File(Loader.instance().configDir.parentFile, "ovo")

        val ITEM_GROUP = HashMap<String, CreativeTabs>()
        val ITEM_GROUP_CACHE = HashMap<String, ArrayList<Item>>()
        val BLOCK_GROUP_CACHE = HashMap<String, ArrayList<Block>>()
    }

    init {
        CreativeTabs.creativeTabArray.forEach {
            ITEM_GROUP[ResourceLocation(it.tabLabel).toString()] = it
        }

        run {
            val file = File(baseFile, "ovo_package.json")
            if (!baseFile.exists() || !baseFile.isDirectory) {
                baseFile.mkdirs()
                file.createNewFile()
                return@run
            }

            if (!file.exists() || !file.isFile) {
                file.createNewFile()
                return@run
            }

            val packagePathList = ArrayList<String>()
            JsonHandler.dataScan(baseFile, packagePathList)

            if (packagePathList.isEmpty()) return@run
            fun readWithOriginal(file: File): HashMap<String, String>? {
                val type = object: TypeToken<HashMap<String, String>>() {}.type
                return gson.fromJson(file.readText(), type) as HashMap<String, String>?
            }

            for (i in packagePathList) {
                val packagePath = File(baseFile, i)
                val packageInfo = File(packagePath, "info.json")
                if (!packagePath.isDirectory || !packageInfo.isFile) {
                    OVOMain.Logger.error("OVO-Package $i is not find!")
                    continue
                }

                OVOMain.Logger.info("Such OVO-Package: $i")
                val modid = readWithOriginal(packageInfo)?.let { it["modid"] } ?: "ovo"
                DataProcessor(packagePath, modid)

                // if (OVOMain.ManaMetalModInstall) MMMCoreRunner("${basePath}\\${i}")
                // DataList.init()
            }
        }
    }

}