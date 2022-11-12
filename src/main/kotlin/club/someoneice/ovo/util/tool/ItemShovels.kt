package club.someoneice.ovo.util.tool

import club.someoneice.ovo.core.DataList
import club.someoneice.ovo.core.Info
import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.data.ItemTool
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.ItemSpade

class ItemShovels(toolMate: ToolMaterial, toolSet: ItemTool): ItemSpade(toolMate) {
    init {
        this.unlocalizedName = toolSet.localization_name
        this.setTextureName("${Info.modid}:${toolSet.texture_name}")
        this.maxStackSize = 1
        if (DataList.getGroup.containsKey(toolSet.group)) this.creativeTab = DataList.getGroup[toolSet.group]
        else OVOMain.Logger.error("${toolSet.name}'s group is in error !")

        GameRegistry.registerItem(this, toolSet.name, Info.modid)
    }
}