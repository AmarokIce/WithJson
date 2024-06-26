package club.someoneice.ovo.core

import cpw.mods.fml.common.Loader
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(modid = OVOMain.MODID, version = OVOMain.VERSION, dependencies = "after:manametalmod")
class OVOMain {
    companion object {
        const val MODID: String = "ovo"
        const val VERSION: String = "0.1.3"
        val ManaMetalModInstall: Boolean = Loader.isModLoaded("manametalmod")

        var Logger: Logger = LogManager.getLogger("WithJson")
    }

    @Mod.EventHandler
    fun initializationModEvent(event: FMLInitializationEvent) {
        Logger.info("OVO is Start loading!")
        CoreHandler.init()
    }
}