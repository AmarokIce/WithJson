package club.someoneice.ovo.base.tool

import club.someoneice.ovo.data.ItemTool
import net.minecraft.item.Item

class ToolShovel: ToolTypeMain() {
    override fun getToolType(typeGetter: ToolTypeGetter) {
        typeGetter.getType("shovel")
    }

    fun toolInit(toolSet: ItemTool, toolMate: Item.ToolMaterial) {
        ItemShovels(toolMate, toolSet)
    }
}