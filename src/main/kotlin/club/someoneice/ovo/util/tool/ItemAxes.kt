package club.someoneice.ovo.util.tool

import club.someoneice.ovo.core.Info
import club.someoneice.ovo.data.ItemTool
import net.minecraft.item.AxeItem
import net.minecraft.item.ToolMaterials
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class ItemAxes(settings: Settings, toolMate: ToolMaterials, toolSet: ItemTool): AxeItem(toolMate, toolSet.attackDamage.toFloat(), -3.2f, settings) {
    init {
        Registry.register(Registry.ITEM, Identifier(Info.modid, toolSet.name), this)
    }
}