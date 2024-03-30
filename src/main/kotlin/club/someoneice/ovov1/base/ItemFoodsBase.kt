package club.someoneice.ovov1.base

import club.someoneice.ovov1.core.`object`.DataList
import club.someoneice.ovov1.core.`object`.Info
import club.someoneice.ovov1.util.Util.findItemByText
import club.someoneice.ovov1.util.register
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumAction
import net.minecraft.item.ItemFood
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ItemFoodsBase(hunger: Int, saturation: Float, wolf: Boolean, private val foodSet: club.someoneice.ovo.data.old.ItemFood): ItemFood(hunger, saturation, wolf) {
    init {
        if (foodSet.always_eat) this.setAlwaysEdible()
        this.unlocalizedName = foodSet.localization_name
        this.setTextureName(Info.modid + ":" + foodSet.texture_name)
        if (DataList.getGroup.containsKey(foodSet.group)) {
            this.creativeTab = DataList.getGroup[foodSet.group]
        }

        this.register(foodSet.name)
    }

    override fun getMaxItemUseDuration(items: ItemStack?): Int {
        return if (this.foodSet.fast_food) 16 else super.getMaxItemUseDuration(items)
    }

    override fun getItemUseAction(items: ItemStack?): EnumAction {
        return if (foodSet.isDrink) EnumAction.drink else EnumAction.eat
    }

    override fun onEaten(items: ItemStack, world: World, player: EntityPlayer): ItemStack {
        super.onEaten(items, world, player)
        return ItemStack(findItemByText(foodSet.return_item))
    }
}