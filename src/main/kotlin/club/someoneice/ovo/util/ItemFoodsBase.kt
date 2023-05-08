package club.someoneice.ovo.util

import club.someoneice.ovo.core.`object`.Info
import club.someoneice.ovo.data.ItemFood
import club.someoneice.ovo.json.data.CoreDataOutput
import club.someoneice.ovo.json.data.helper.ItemJsonHelper
import club.someoneice.ovo.util.Util.findItemByText
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.UseAction
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

class ItemFoodsBase(settings: Settings, private val foodSet: ItemFood): Item(settings) {
    init {
        Registry.register(Registry.ITEM, Identifier(Info.modid, foodSet.name), this)
        CoreDataOutput(ItemJsonHelper(), foodSet)
    }

    override fun getUseAction(stack: ItemStack?): UseAction {
        return if (foodSet.isDrink) UseAction.DRINK else UseAction.EAT
    }

    override fun finishUsing(stack: ItemStack?, world: World?, user: LivingEntity?): ItemStack {
        val item = findItemByText(foodSet.return_item) ?: return ItemStack.EMPTY
        return item.defaultStack
    }
}