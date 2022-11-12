package club.someoneice.ovo.util

import club.someoneice.ovo.json.Sandman
import net.minecraft.item.Item

object Util {
    fun findItemByText(Item: String): Item? {
        if (Item == "null") return null
        var item: Item? = net.minecraft.item.Item.itemRegistry.getObject(Item) as Item

        if (item == null) {
            try {
                val item1 = net.minecraft.item.Item.getItemById(Integer.valueOf(Item))
                item = item1
            } catch (_: NumberFormatException) {
                Sandman.nullSandman()
            }
        }

        return item
    }
}