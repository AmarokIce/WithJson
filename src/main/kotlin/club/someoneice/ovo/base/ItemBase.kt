package club.someoneice.ovo.base

import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.core.`object`.DataList
import club.someoneice.ovo.core.`object`.Info
import club.someoneice.ovo.data.ItemData
import club.someoneice.ovo.util.register
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

        this.register(itemSet.name)
        OVOMain.Logger.info("Item ${itemSet.name} is register success!")
    }
}