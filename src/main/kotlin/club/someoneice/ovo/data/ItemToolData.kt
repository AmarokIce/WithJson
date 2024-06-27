package club.someoneice.ovo.data

import club.someoneice.ovo.core.CoreHandler
import net.minecraft.item.*
import java.util.*

data class ItemToolData(
    val name:               String,
    val localizationName:   String      = name,
    val textureName:        String      = name,
    val toolkit:            String      = "pickaxe",
    val toolMaterial:       String      = "wood",
    val group:              String      = "minecraft:misc"
) {
    fun registerTool(): Item {
        fun setData(item: Item) {
            item.unlocalizedName = this.localizationName
            item.setTextureName(this.textureName)
            item.setMaxStackSize(1)
        }

        val material = Item.ToolMaterial.valueOf(this@ItemToolData.toolMaterial)
        val item = when (this.toolkit.lowercase(Locale.getDefault())) {
            "axe"       -> object: ItemAxe(material) {}.apply(::setData)
            "hoe"       -> object: ItemHoe(material) {}.apply(::setData)
            "shovel"    -> object: ItemSpade(material) {}.apply(::setData)
            "pickaxe"   -> object: ItemPickaxe(material) {}.apply(::setData)
            "sword"     -> object: ItemSword(material) {}.apply(::setData)
            else        -> object: ItemPickaxe(material) {}.apply(::setData)
        }

        CoreHandler.ITEM_GROUP[this.group]?.let(item::setCreativeTab)
            ?: CoreHandler.ITEM_GROUP_CACHE.getOrPut(this.group, ::ArrayList).add(item)

        return item
    }
}
