package club.someoneice.ovo.util.tool

import club.someoneice.ovo.data.ItemTool
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterials

class ToolShovel: ToolTypeMain() {
    override fun getToolType(typeGetter: ToolTypeGetter) {
        typeGetter.getType("shovel")
    }

    fun toolInit(settings: Item.Settings, toolSet: ItemTool, toolMate: ToolMaterials) {
        ItemShovels(settings, toolMate, toolSet)
    }
}