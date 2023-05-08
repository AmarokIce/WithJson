package club.someoneice.ovo.util

import club.someoneice.ovo.data.ItemWeapon
import club.someoneice.ovo.json.data.CoreDataOutput
import club.someoneice.ovo.json.data.helper.ItemJsonHelper
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterials

class ItemWeapons(settings: Settings, toolSet: ItemWeapon, toolMate: ToolMaterials?): SwordItem(toolMate, toolSet.attackDamage, -2.4F, settings) {
    init {
        this.register(toolSet.name)
        CoreDataOutput(ItemJsonHelper(), toolSet)
    }
}