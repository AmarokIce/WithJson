package club.someoneice.ovo.core

import club.someoneice.ovo.util.gson
import club.someoneice.ovo.util.json
import com.google.gson.reflect.TypeToken
import cpw.mods.fml.common.Loader
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import java.io.File
import java.io.IOException

object CoreHandler {
    private val baseFile: File = File(Loader.instance().configDir.parentFile.path, "ovo")

    val ITEM_GROUP = HashMap<String, CreativeTabs>()
    val ITEM_GROUP_CACHE = HashMap<String, ArrayList<Item>>()
    val BLOCK_GROUP_CACHE = HashMap<String, ArrayList<Block>>()


    internal fun init() {
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
            this.dataScan(file, packagePathList)

            if (packagePathList.isEmpty()) return@run
            fun readWithOriginal(file: File): HashMap<String, String> {
                val map = HashMap<String, String>()
                val raw = json.tryPullObjectOrEmpty(file.readText())
                if (raw.isEmpty) return map
                return map.apply { raw.obj.entries.forEach { (k, v) -> this[k] = v.toString() } }
            }

            for (i in packagePathList) {
                val packagePath = File(baseFile, i)
                val packageInfo = File(packagePath, "info.json")
                if (!packagePath.isDirectory || !packageInfo.isFile) {
                    OVOMain.Logger.error("OVO-Package $i is not find!")
                    continue
                }

                OVOMain.Logger.info("Such OVO-Package: $i")
                val modid = readWithOriginal(packageInfo)["modid"] ?: "ovo"
                DataProcessor(packagePath, modid)

                // if (OVOMain.ManaMetalModInstall) MMMCoreRunner("${basePath}\\${i}")
                // DataList.init()
            }
        }
    }

    private inline fun <reified T> dataScan(file: File, list: java.util.ArrayList<T>) {
        list.addAll(pullAsData<T>(read(file).toString()))
    }

    private inline fun <reified T> pullAsData(nodeBase: String) =
        gson.fromJson<java.util.ArrayList<T>>(nodeBase, object: TypeToken<java.util.ArrayList<T>>() {}.type)

    @Throws(IOException::class)
    private fun read(file: File) = StringBuilder().apply {
        file.readLines().forEach { text ->
            if (text.contains("//")) this.append(text, 0, text.indexOf("//"))
            else this.append(text)
        }
    }
}