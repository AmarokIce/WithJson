package club.someoneice.ovo.util.tool

import club.someoneice.ovo.data.ItemTool
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterials

class ToolPickaxe: ToolTypeMain() {
    override fun getToolType(typeGetter: ToolTypeGetter) {
        typeGetter.getType("pickaxe")
    }

    fun toolInit(settings: Item.Settings, toolSet: ItemTool, toolMate: ToolMaterials) {
        ItemPickaxe(settings, toolMate, toolSet)
    }
}