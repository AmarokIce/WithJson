package club.someoneice.ovo.util

import club.someoneice.ovo.core.DataList
import club.someoneice.ovo.core.Info
import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.data.ItemGift
import club.someoneice.ovo.util.Util.findItemByText
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ItemGifts(private val itemSet: ItemGift): Item() {
    init {
        this.unlocalizedName = itemSet.localization_name
        this.setTextureName(Info.modid + ":" + itemSet.texture_name)
        this.maxStackSize = itemSet.max_size
        if (DataList.getGroup.containsKey(itemSet.group)) {
            this.creativeTab = DataList.getGroup[itemSet.group]
        } else {
            OVOMain.Logger.error("${itemSet.name}'s group is in error !")
        }

        GameRegistry.registerItem(this, itemSet.name, Info.modid)
    }

    override fun onItemUse(item: ItemStack, player: EntityPlayer, world: World, p_77648_4_: Int, p_77648_5_: Int, p_77648_6_: Int, p_77648_7_: Int, x: Float, y: Float, z: Float): Boolean {
        item.stackSize--
        if (itemSet.items.size > 0) {
            for (itm in itemSet.items) {
                val getItem: Item? = findItemByText(itm.item.toString())
                player.inventory.addItemStackToInventory(ItemStack(getItem, itm.item_number, itm.item_meta))
            }
        }

        return true
    }
}