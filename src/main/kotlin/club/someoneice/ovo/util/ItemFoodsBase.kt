package club.someoneice.ovo.util

import club.someoneice.ovo.core.DataList
import club.someoneice.ovo.core.Info
import club.someoneice.ovo.data.ItemFoods
import club.someoneice.ovo.util.Util.findItemByText
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumAction
import net.minecraft.item.ItemFood
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ItemFoodsBase(hunger: Int, saturation: Float, wolf: Boolean, private val foodSet: ItemFoods): ItemFood(hunger, saturation, wolf) {
    init {
        if (foodSet.always_eat) this.setAlwaysEdible()
        this.unlocalizedName = foodSet.localization_name
        this.setTextureName(Info.modid + ":" + foodSet.texture_name)
        if (DataList.getGroup.containsKey(foodSet.group)) {
            this.creativeTab = DataList.getGroup[foodSet.group]
        }

        GameRegistry.registerItem(this, foodSet.name, Info.modid)
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