package club.someoneice.ovo.util

import club.someoneice.ovo.json.Sandman
import net.minecraft.item.Item
import javax.annotation.Nullable

object Util {

    @Nullable
    fun findItemByText(Item: String): Item? {
        if (Item == "null") return null
        var item: Item? = net.minecraft.item.Item.itemRegistry.getObject(Item) as Item?

        if (item == null) {
            try {
                item = net.minecraft.item.Item.getItemById(Integer.valueOf(Item))
            } catch (_: NumberFormatException) {
                Sandman.nullSandman()
            }
        }

        return item
    }
}