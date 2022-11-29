package club.someoneice.ovo.util

import club.someoneice.ovo.core.DataList
import club.someoneice.ovo.core.Info
import club.someoneice.ovo.data.BlockData
import club.someoneice.ovo.json.Sandman
import club.someoneice.ovo.json.data.CoreDataOutput
import club.someoneice.ovo.json.data.helper.BlockJsonHelper
import club.someoneice.ovo.util.Util.findItemByText
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.loot.context.LootContext
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import java.util.*

class BlockBase(settings: Settings, private val blockSet: BlockData): Block(settings) {
    init {
        Registry.register(Registry.BLOCK, Identifier(Info.modid, blockSet.name), this)
        Registry.register(Registry.ITEM, Identifier(Info.modid, blockSet.name), BlockItem(this, Item.Settings().group(DataList.getGroup[blockSet.group])))

        CoreDataOutput(BlockJsonHelper(), blockSet)
    }

    override fun getDroppedStacks(state: BlockState, builder: LootContext.Builder): MutableList<ItemStack> {
        val itemlist = ArrayList<ItemStack>()
        when(blockSet.drop_item) {
            "null" -> itemlist.add(Sandman.sandman())
            "this" -> itemlist.add(ItemStack(this))
            else -> itemlist.add(ItemStack(findItemByText(blockSet.drop_item)))
        }

        return itemlist
    }
}