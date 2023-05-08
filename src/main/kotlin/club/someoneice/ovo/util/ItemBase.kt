package club.someoneice.ovo.util

import club.someoneice.ovo.data.ItemData
import net.minecraft.item.Item

class ItemBase(settings: Settings, itemSet: ItemData): Item(settings) {
    init {
        this.register(itemSet.name)
    }
}