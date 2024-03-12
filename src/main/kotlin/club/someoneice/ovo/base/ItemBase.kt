package club.someoneice.ovo.base

import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.core.`object`.DataList
import club.someoneice.ovo.core.`object`.Info
import club.someoneice.ovo.data.ItemData
import club.someoneice.ovo.util.register
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ChatComponentTranslation
import net.minecraft.util.IIcon

class ItemBase(val itemSet: ItemData): Item() {
    lateinit var icons: Array<IIcon?>

    init {
        this.unlocalizedName = itemSet.localization_name
        this.setTextureName(Info.modid + ":" + itemSet.texture_name)
        this.maxStackSize = itemSet.max_size
        if (DataList.getGroup.containsKey(itemSet.group)) {
            this.creativeTab = DataList.getGroup[itemSet.group]
        } else {
            OVOMain.Logger.error("${itemSet.name}'s group is in error !")
        }

        this.register(itemSet.name)

        if (itemSet.meta > 0) {
            this.hasSubtypes = true
            icons = arrayOfNulls(itemSet.meta)
        }
        OVOMain.Logger.info("Item ${itemSet.name} is register success!")
    }

    override fun registerIcons(register: IIconRegister) {
        for (i in 0 until itemSet.meta) icons[i] = register.registerIcon("${Info.modid}:${itemSet.texture_name}_${i}")
    }

    override fun getSubItems(item: Item, tab: CreativeTabs, list: MutableList<Any?>) {
        for (i in 0 until itemSet.meta) list.add(ItemStack(item, 1, i))
    }

    override fun addInformation(item: ItemStack?, player: EntityPlayer, infoList: MutableList<Any?>, flag: Boolean) {
        item ?: return; for (message in itemSet.info) infoList.add(I18n.format(message))
    }

    override fun getIcon(stack: ItemStack, pass: Int): IIcon = icons[stack.itemDamage]!!
    override fun getUnlocalizedName(stack: ItemStack): String = "${super.getUnlocalizedName(stack)}_${stack.itemDamage}"

}