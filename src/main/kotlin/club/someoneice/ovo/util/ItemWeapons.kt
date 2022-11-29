package club.someoneice.ovo.util

import club.someoneice.ovo.core.Info
import club.someoneice.ovo.data.ItemKnife
import club.someoneice.ovo.json.data.CoreDataOutput
import club.someoneice.ovo.json.data.helper.ItemJsonHelper
import net.minecraft.item.*
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class ItemWeapons(settings: Settings, toolSet: ItemKnife, toolMate: ToolMaterials?): SwordItem(toolMate, toolSet.attackDamage, -2.4F, settings) {
    init {
        Registry.register(Registry.ITEM, Identifier(Info.modid, toolSet.name), this)
        CoreDataOutput(ItemJsonHelper(), toolSet)
    }
}