package club.someoneice.ovo.core

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import org.apache.logging.log4j.LogManager


@Mod(modid = OVOMain.modid, version = OVOMain.VERSION, dependencies = "required-after:legacymckotlin;")
class OVOMain {
    companion object {
        const val modid: String = "ovo"
        const val VERSION: String = "0.1.0"

        var Logger = LogManager.getLogger("ovo")
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        OVOMain.Logger.info("OVO is Start loading!")
        CoreRunner()
    }
}