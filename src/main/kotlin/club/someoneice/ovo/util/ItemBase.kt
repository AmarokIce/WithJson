package club.someoneice.ovo.util

import club.someoneice.ovo.OVOMain
import club.someoneice.ovo.core.Info
import club.someoneice.ovo.data.ItemData
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class ItemBase(settings: Settings, itemSet: ItemData): Item(settings) {
    init {
        Registry.register(Registry.ITEM, Identifier(Info.modid, itemSet.name), this)
        OVOMain.Logger.info("Item ${itemSet.name} is register success!")
    }
}