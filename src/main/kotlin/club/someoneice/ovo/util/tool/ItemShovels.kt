package club.someoneice.ovo.util.tool

import club.someoneice.ovo.core.Info
import club.someoneice.ovo.data.ItemTool
import club.someoneice.ovo.json.data.CoreDataOutput
import club.someoneice.ovo.json.data.helper.ItemJsonHelper
import net.minecraft.item.ShovelItem
import net.minecraft.item.ToolMaterials
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class ItemShovels(settings: Settings, toolMate: ToolMaterials, toolSet: ItemTool): ShovelItem(toolMate, toolSet.attackDamage.toFloat(), -3.0f, settings) {
    init {
        Registry.register(Registry.ITEM, Identifier(Info.modid, toolSet.name), this)
        CoreDataOutput(ItemJsonHelper(), toolSet)
    }
}