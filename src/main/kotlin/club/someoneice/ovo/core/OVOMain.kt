package club.someoneice.ovo.core

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import org.apache.logging.log4j.LogManager


@Mod(modid = OVOMain.modid, version = OVOMain.VERSION, dependencies = "required-after:legacymckotlin;required-after:manametalmod")
class OVOMain {
    companion object {
        const val modid: String = "ovo"
        const val VERSION: String = "0.0.3"
        val ManaMetalModInstall: Boolean = getManaMetalMod();

        var Logger = LogManager.getLogger("ovo")

        private fun getManaMetalMod(): Boolean {
            return try {
                Class.forName("project.studio.manametalmod.ManaMetalMod")
                true
            } catch (e: ClassNotFoundException) {
                false
            }
        }
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        Logger.info("OVO is Start loading!")

        CoreRunner()
    }


}