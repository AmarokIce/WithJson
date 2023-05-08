package club.someoneice.ovo.util

import club.someoneice.ovo.core.`object`.DataList
import club.someoneice.ovo.core.`object`.Info
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object Util {
    fun findItemByText(Item: String): Item? {
        if (Item == "null") return null

        return try {
            val modid: String = Item.substring(0, Item.indexOf(":"))
            val name: String = Item.substring(Item.indexOf(":") + 1)

            Registry.ITEM.get(Identifier(modid, name))
        } catch (_: Exception) {
            null
        }
    }

    fun getItemNameByRegistryName(ItemName: String): String {
        return ItemName.substring(ItemName.indexOf(":") + 1)
    }
}

fun Block.register(name: String, group: String) {
    Registry.register(Registry.BLOCK, Identifier(Info.modid, name), this)
    Registry.register(Registry.ITEM, Identifier(Info.modid, name), BlockItem(this, Item.Settings().group(
        DataList.getGroup[group]))
    )
}

fun Item.register(name: String) {
    Registry.register(Registry.ITEM, Identifier(Info.modid, name), this)
}

fun ItemStack.setSize(size: Int): ItemStack {
    this.count = size
    return this
}