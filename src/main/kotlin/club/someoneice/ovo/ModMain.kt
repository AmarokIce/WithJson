package club.someoneice.ovo

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.eventhandler.EventPriority
import org.apache.logging.log4j.LogManager

@Mod(modid = ModMain.MODID, useMetadata = true)
class ModMain {
  companion object {
    const val MODID = "ovo"
    val LOGGER = LogManager.getLogger(MODID)
  }

  @Mod.EventHandler
  fun preInit(event: FMLPreInitializationEvent) {

  }

  @Mod.EventHandler
  fun init(event: FMLInitializationEvent) {}
}
