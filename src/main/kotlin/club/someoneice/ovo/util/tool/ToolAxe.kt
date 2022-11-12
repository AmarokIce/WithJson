package club.someoneice.ovo.util.tool

import club.someoneice.ovo.data.ItemTool
import net.minecraft.item.Item

class ToolAxe: ToolTypeMain() {
    override fun getToolType(typeGetter: ToolTypeGetter) {
        typeGetter.getType("axe")
    }

    fun toolInit(toolSet: ItemTool, toolMate: Item.ToolMaterial) {
        ItemAxes(toolMate, toolSet)
    }
}