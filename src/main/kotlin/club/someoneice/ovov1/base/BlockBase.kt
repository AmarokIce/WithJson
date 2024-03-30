package club.someoneice.ovov1.base

import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.data.old.BlockData
import club.someoneice.ovov1.core.`object`.DataList
import club.someoneice.ovov1.util.Util.findItemByText
import club.someoneice.ovov1.util.register
import com.google.common.collect.Lists
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class BlockBase(material: Material, private val blockSet: BlockData): Block(material) {
    init {
        this.setHardness(blockSet.hard.toFloat())
        if (blockSet.is_glow) this.setLightLevel(15.0F)
        if (blockSet.break_tool.isNotEmpty()) this.setHarvestLevel(blockSet.break_tool, blockSet.hard_level)

        if (DataList.getGroup.containsKey(blockSet.group)) this.setCreativeTab(DataList.getGroup[blockSet.group])
        else OVOMain.Logger.error("${blockSet.name}'s group is in error !")

        this.register(blockSet.name)
    }

    override fun getDrops(world: World?, x: Int, y: Int, z: Int, metadata: Int, fortune: Int): ArrayList<ItemStack> {
        return Lists.newArrayList<ItemStack>().also {
            when(blockSet.drop_item) {
                "null" -> Unit
                "this" -> it.add(ItemStack(this))
                else -> it.add(ItemStack(findItemByText(blockSet.drop_item)))
            }
        }
    }
}