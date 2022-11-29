package club.someoneice.ovo.util

import net.minecraft.item.Item
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