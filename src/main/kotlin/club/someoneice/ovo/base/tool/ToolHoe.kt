package club.someoneice.ovo.base.tool

import club.someoneice.ovo.data.ItemTool
import net.minecraft.item.Item

class ToolHoe(): ToolTypeMain() {
    override fun getToolType(typeGetter: ToolTypeGetter) {
        typeGetter.getType("hoe")
    }

    fun toolInit(toolSet: ItemTool, toolMate: Item.ToolMaterial) {
        ItemHoes(toolMate, toolSet)
    }
}