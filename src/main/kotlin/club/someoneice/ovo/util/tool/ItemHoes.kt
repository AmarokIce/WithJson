package club.someoneice.ovo.util.tool

import club.someoneice.ovo.data.ItemTool
import club.someoneice.ovo.util.register
import net.minecraft.item.HoeItem
import net.minecraft.item.ToolMaterials

class ItemHoes(settings: Settings, toolMate: ToolMaterials, toolSet: ItemTool): HoeItem(toolMate, toolSet.attackDamage, -3.0f, settings) {
    init {
        this.register(toolSet.name)
    }
}