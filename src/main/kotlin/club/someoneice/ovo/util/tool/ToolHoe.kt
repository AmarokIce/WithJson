package club.someoneice.ovo.util.tool

import club.someoneice.ovo.data.ItemTool
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterials

class ToolHoe(): ToolTypeMain() {
    override fun getToolType(typeGetter: ToolTypeGetter) {
        typeGetter.getType("hoe")
    }

    fun toolInit(settings: Item.Settings, toolSet: ItemTool, toolMate: ToolMaterials) {
        ItemHoes(settings, toolMate, toolSet)
    }
}