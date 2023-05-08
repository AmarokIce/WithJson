package club.someoneice.ovo.util.tool

import club.someoneice.ovo.data.ItemTool
import club.someoneice.ovo.util.register
import net.minecraft.item.PickaxeItem
import net.minecraft.item.ToolMaterials

class ItemPickaxes(settings: Settings, toolMate: ToolMaterials, toolSet: ItemTool): PickaxeItem(toolMate, toolSet.attackDamage,-2.8f,settings) {
    init {
        this.register(toolSet.name)
    }
}