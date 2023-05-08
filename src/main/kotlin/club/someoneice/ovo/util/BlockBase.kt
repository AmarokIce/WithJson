package club.someoneice.ovo.util

import club.someoneice.ovo.data.BlockData
import club.someoneice.ovo.util.Util.findItemByText
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.ItemStack
import net.minecraft.loot.context.LootContext

class BlockBase(settings: Settings, private val blockSet: BlockData): Block(settings) {
    init {
        this.register(blockSet.name, blockSet.group)
    }

    override fun getDroppedStacks(state: BlockState, builder: LootContext.Builder): MutableList<ItemStack> {
        val itemlist = ArrayList<ItemStack>()
        when(blockSet.drop_item) {
            "null" -> return itemlist
            "this" -> itemlist.add(ItemStack(this))
            else -> itemlist.add(ItemStack(findItemByText(blockSet.drop_item)))
        }

        return itemlist
    }
}