package club.someoneice.ovo

import club.someoneice.ovo.core.CoreRunner
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

object OVOMain {
    const val modid: String = "ovo"
    val Logger: Logger = LogManager.getLogger(modid)
}

fun init() {
    CoreRunner()
}

