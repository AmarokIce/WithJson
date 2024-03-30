package club.someoneice.ovov1.base

import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.generator.old.ItemWeapon
import club.someoneice.ovov1.core.`object`.DataList
import club.someoneice.ovov1.core.`object`.Info
import club.someoneice.ovov1.util.register
import net.minecraft.item.ItemSword

class ItemWeapons(toolSet: ItemWeapon, toolMate: ToolMaterial?): ItemSword(toolMate) {
    init {
        this.unlocalizedName = toolSet.localization_name
        this.setTextureName("${Info.modid}:${toolSet.texture_name}")
        this.maxStackSize = 1
        if (DataList.getGroup.containsKey(toolSet.group)) this.creativeTab = DataList.getGroup[toolSet.group]
        else OVOMain.Logger.error("${toolSet.name}'s group is in error !")

        this.register(toolSet.name)
    }
}