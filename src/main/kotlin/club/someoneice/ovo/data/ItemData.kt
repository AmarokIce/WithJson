package club.someoneice.ovo.data

import club.someoneice.ovo.core.CoreHandler
import club.someoneice.ovo.data.helper.FoodData
import club.someoneice.ovo.util.giveOrThrowOut
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumAction
import net.minecraft.item.Item
import net.minecraft.item.ItemFood
import net.minecraft.item.ItemStack
import net.minecraft.util.IIcon
import net.minecraft.world.World

data class ItemData (
    val name:               String,
    val localizationName:   String          = name,
    val textureName:        String          = name,
    val maxSize:            Int             = 64,
    val group:              String          = "minecraft:misc",
    val meta:               Int             = 0,
    val info:               ArrayList<String>    = ArrayList(),

    val foodData:           FoodData?       = null
) {
    private val icons by lazy<Array<IIcon?>> { arrayOfNulls(this.meta + 1) }

    fun registryItem(): Item {
        val flag = this.meta > 0
        val item = foodData?.let { object: ItemFood(it.hunger, it.saturation, it.wolf) {
            init {
                this.setUnlocalizedName(this@ItemData.localizationName)
                this.setTextureName(this@ItemData.textureName)

                this.setHasSubtypes(flag)
            }
            override fun getItemUseAction(item: ItemStack) = if (it.isDrink) EnumAction.drink else EnumAction.eat
            override fun getMaxItemUseDuration(item: ItemStack) = it.speed
            override fun onEaten(item: ItemStack, world: World, player: EntityPlayer): ItemStack {
                if (it.returnItem.isEmpty()) return super.onEaten(item, world, player)
                it.returnItem.forEach { itemReturn -> itemReturn.toItemStack()?.let(player::giveOrThrowOut) }
                return super.onEaten(item, world, player)
            }

            override fun registerIcons(register: IIconRegister) {
                if (!flag) super.registerIcons(register)
                else dataRegisterIcons(register)
            }

            override fun getSubItems(item: Item, tabs: CreativeTabs, list: MutableList<Any?>) {
                if (!flag) super.getSubItems(item, tabs, list)
                else dataGetSubItems(item, list)
            }

            override fun getIcon(stack: ItemStack, pass: Int): IIcon
                = if (!flag) super.getIcon(stack, pass) else icons[stack.itemDamage]!!

            override fun getUnlocalizedName(stack: ItemStack): String
                = if (!flag) super.getUnlocalizedName(stack) else "${super.getUnlocalizedName(stack)}_${stack.itemDamage}"

            override fun addInformation(item: ItemStack?, player: EntityPlayer, infoList: MutableList<Any?>, shouldShow: Boolean) {
                item ?: return; for (message in info) infoList.add(I18n.format(message))
            }
        }.apply { if (it.alwaysEat) this.setAlwaysEdible() } } ?: object: Item() {
            init {
                this.setUnlocalizedName(this@ItemData.localizationName)
                this.setTextureName(this@ItemData.textureName)

                this.setHasSubtypes(flag)
            }

            override fun registerIcons(register: IIconRegister) {
                if (!flag) super.registerIcons(register)
                else dataRegisterIcons(register)
            }

            override fun getSubItems(item: Item, tabs: CreativeTabs, list: MutableList<Any?>) {
                if (!flag) super.getSubItems(item, tabs, list)
                else dataGetSubItems(item, list)
            }

            override fun getIcon(stack: ItemStack, pass: Int): IIcon =
                if (!flag) super.getIcon(stack, pass) else icons[stack.itemDamage]!!

            override fun getUnlocalizedName(stack: ItemStack): String =
                if (!flag) super.getUnlocalizedName(stack) else "${super.getUnlocalizedName(stack)}_${stack.itemDamage}"

            override fun addInformation(item: ItemStack?, player: EntityPlayer, infoList: MutableList<Any?>, shouldShow: Boolean) {
                for (message in info) infoList.add(I18n.format(message))
            }
        }

        CoreHandler.ITEM_GROUP[this.group]?.let(item::setCreativeTab)
            ?: CoreHandler.ITEM_GROUP_CACHE.getOrPut(this.group, ::ArrayList).add(item)

        return item
    }

    private fun dataRegisterIcons(register: IIconRegister) {
        for (i in 0 until this.meta) icons[i] = register.registerIcon("${textureName}_${i}")
    }

    private fun dataGetSubItems(item: Item, list: MutableList<Any?>) {
        for (i in 0 until this.meta) list.add(ItemStack(item, 1, i))
    }
}
