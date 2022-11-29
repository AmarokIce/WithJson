package club.someoneice.ovo.util

import club.someoneice.ovo.core.Info
import club.someoneice.ovo.data.ItemGift
import club.someoneice.ovo.json.data.CoreDataOutput
import club.someoneice.ovo.json.data.helper.ItemJsonHelper
import club.someoneice.ovo.util.Util.findItemByText
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

class ItemGifts(settings: Settings, private val itemSet: ItemGift): Item(settings) {
    init {
        Registry.register(Registry.ITEM, Identifier(Info.modid, itemSet.name), this)
        CoreDataOutput(ItemJsonHelper(), itemSet)
    }

    override fun finishUsing(stack: ItemStack, world: World, user: LivingEntity): ItemStack {
        stack.count--
        if (itemSet.items.size > 0) {
            for (itm in itemSet.items) {
                val getItem: Item? = findItemByText(itm.item.toString())
                (user as PlayerEntity).inventory.addPickBlock(ItemStack(getItem, itm.item_number))
            }
        }

        return super.finishUsing(stack, world, user)
    }
}