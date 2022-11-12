package club.someoneice.ovo.util

import club.someoneice.ovo.core.DataList
import club.someoneice.ovo.core.Info
import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.data.ItemData
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.Item

class ItemBase(itemSet: ItemData): Item() {
    init {
        this.unlocalizedName = itemSet.localization_name
        this.setTextureName(Info.modid + ":" + itemSet.texture_name)
        this.maxStackSize = itemSet.max_size
        if (DataList.getGroup.containsKey(itemSet.group)) {
            this.creativeTab = DataList.getGroup[itemSet.group]
        } else {
            OVOMain.Logger.error("${itemSet.name}'s group is in error !")
        }

        GameRegistry.registerItem(this, itemSet.name, Info.modid)
        OVOMain.Logger.info("Item ${itemSet.name} is register success!")
    }
}