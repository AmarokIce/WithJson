package club.someoneice.ovo.base.tool

import club.someoneice.ovo.data.ItemTool
import net.minecraft.item.Item

class ToolPickaxe: ToolTypeMain() {
    override fun getToolType(typeGetter: ToolTypeGetter) {
        typeGetter.getType("pickaxe")
    }

    fun toolInit(toolSet: ItemTool, toolMate: Item.ToolMaterial) {
        ItemPickaxe(toolMate, toolSet)
    }
}