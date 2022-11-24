package club.someoneice.ovo.util

import club.someoneice.ovo.json.Sandman
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object Util {
    fun findItemByText(Item: String): Item? {
        if (Item == "null") return null

        return try {
            val modid: String = Item.substring(1, Item.indexOf(":") - 1)
            val name: String = Item.substring(Item.indexOf(":") + 1)

            Registry.ITEM.get(Identifier(modid, name))
        } catch (_: Exception) {
            null
        }
    }

}