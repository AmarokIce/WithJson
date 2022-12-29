package club.someoneice.ovo.json.helper

import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.json.Sandman
import com.google.gson.Gson
import java.io.File

class JsonProcessor(
    private val group           : club.someoneice.ovo.json.reader.Group,
    private val item            : club.someoneice.ovo.json.reader.Item,
    private val block           : club.someoneice.ovo.json.reader.ItemBlocks,
    private val foods           : club.someoneice.ovo.json.reader.ItemFoods,
    private val gift            : club.someoneice.ovo.json.reader.ItemGifts,
    private val swords          : club.someoneice.ovo.json.reader.ItemSwords,
    private val tools           : club.someoneice.ovo.json.reader.ItemTools,
    private val recipes         : club.someoneice.ovo.json.reader.Recipes,
    private val delete_recipes  : club.someoneice.ovo.json.reader.DeleteRecipes,
    private val biomes          : club.someoneice.ovo.json.reader.Biomes
): JsonTypeGetter() {
    override fun getType(typeGetter: String, filePath: File) {
        OVOMain.Logger.info("Get type: $typeGetter")
        when(typeGetter) {
            "group"             -> group.init(filePath)
            "item"              -> item.init(filePath)
            "item_food"         -> foods.init(filePath)
            "item_gift"         -> gift.init(filePath)
            "item_tools"        -> tools.init(filePath)
            "item_swords"       -> swords.init(filePath)
            "block"             -> block.init(filePath)
            "recipes"           -> recipes.init(filePath)
            "delete_recipes"    -> delete_recipes.init(filePath)
            "biomes"            -> biomes.init(filePath)

            else -> Sandman.missingNo()
        }
    }
}