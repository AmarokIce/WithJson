package club.someoneice.ovo.core

import alexsocol.patcher.KotlinAdapter
import cpw.mods.fml.common.Loader
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import org.apache.logging.log4j.LogManager

@Mod(modid = OVOMain.modid, version = OVOMain.VERSION, modLanguageAdapter = KotlinAdapter.className, dependencies = "after:manametalmod")
class OVOMain {
    companion object {
        const val modid: String = "ovo"
        const val VERSION: String = "0.1.2"
        val ManaMetalModInstall: Boolean = Loader.isModLoaded("manametalmod")
        val PineapplePsychicInstall: Boolean = Loader.isModLoaded("pineapple_psychic")

        var Logger = LogManager.getLogger("WithJson")
    }

    @Mod.EventHandler
    fun initializationModEvent(event: FMLInitializationEvent) {
        Logger.info("OVO is Start loading!")
        CoreRunner()
    }
}