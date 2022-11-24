package club.someoneice.ovo.util.tool

import club.someoneice.ovo.data.ItemTool
import net.minecraft.item.Item.Settings
import net.minecraft.item.ToolMaterials

class ToolAxe: ToolTypeMain() {
    override fun getToolType(typeGetter: ToolTypeGetter) {
        typeGetter.getType("axe")
    }

    fun toolInit(settings: Settings, toolSet: ItemTool, toolMate: ToolMaterials) {
        ItemAxes(settings, toolMate, toolSet)
    }
}