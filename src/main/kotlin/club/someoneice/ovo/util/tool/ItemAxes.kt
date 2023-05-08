package club.someoneice.ovo.util.tool

import club.someoneice.ovo.data.ItemTool
import club.someoneice.ovo.util.register
import net.minecraft.item.AxeItem
import net.minecraft.item.ToolMaterials

class ItemAxes(settings: Settings, toolMate: ToolMaterials, toolSet: ItemTool): AxeItem(toolMate, toolSet.attackDamage.toFloat(), -3.2f, settings) {
    init {
        this.register(toolSet.name)
    }
}