package club.someoneice.ovo.util

import club.someoneice.ovo.OVOMain
import club.someoneice.ovo.core.Info
import club.someoneice.ovo.data.ItemData
import club.someoneice.ovo.json.data.CoreDataOutput
import club.someoneice.ovo.json.data.helper.BlockJsonHelper
import club.someoneice.ovo.json.data.helper.ItemJsonHelper
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class ItemBase(settings: Settings, itemSet: ItemData): Item(settings) {
    init {
        Registry.register(Registry.ITEM, Identifier(Info.modid, itemSet.name), this)

        CoreDataOutput(ItemJsonHelper(), itemSet)
        OVOMain.Logger.debug("Item ${itemSet.name} is register success!")
    }
}