package club.someoneice.ovo.util.tool

import club.someoneice.ovo.data.ItemTool
import club.someoneice.ovo.util.register
import net.minecraft.item.ShovelItem
import net.minecraft.item.ToolMaterials

class ItemShovels(settings: Settings, toolMate: ToolMaterials, toolSet: ItemTool): ShovelItem(toolMate, toolSet.attackDamage.toFloat(), -3.0f, settings) {
    init {
        this.register(toolSet.name)
    }
}