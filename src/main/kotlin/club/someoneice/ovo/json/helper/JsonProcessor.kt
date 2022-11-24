package club.someoneice.ovo.json.helper

import club.someoneice.ovo.OVOMain
import club.someoneice.ovo.json.reader.*
import club.someoneice.ovo.json.Sandman
import java.io.File

class JsonProcessor(
    private val group   : Group,
    private val item    : Item,
    private val block   : ItemBlocks,
    private val foods   : ItemFoods,
    private val gift    : ItemGifts,
    private val swords  : ItemSwords,
    private val tools   : ItemTools,
): JsonTypeGetter() {
    override fun getType(typeGetter: String, filePath: File) {
        OVOMain.Logger.info("Get type: $typeGetter")
        when(typeGetter) {
            "group"         -> group.init(filePath)
            "item"          -> item.init(filePath)
            "item_food"     -> foods.init(filePath)
            "item_gift"     -> gift.init(filePath)
            "item_tools"     -> tools.init(filePath)
            "item_swords"   -> swords.init(filePath)
            "block"         -> block.init(filePath)

            else -> Sandman.missingNo()
        }
    }
}