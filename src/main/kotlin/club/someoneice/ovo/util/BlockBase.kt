package club.someoneice.ovo.util

import club.someoneice.ovo.core.DataList
import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.data.BlockData
import club.someoneice.ovo.json.Sandman
import club.someoneice.ovo.util.Util.findItemByText
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.item.ItemStack
import net.minecraft.util.IIcon
import net.minecraft.world.World
import java.util.*

class BlockBase(material: Material, private val blockSet: BlockData): Block(material) {
    init {
        this.setHardness(blockSet.hard.toFloat())
        this.setHarvestLevel(blockSet.break_tool, blockSet.hard_level)

        if (blockSet.is_glow) this.setLightLevel(15.0F)

        if (DataList.getGroup.containsKey(blockSet.group)) {
            this.setCreativeTab(DataList.getGroup[blockSet.group])
        } else {
            OVOMain.Logger.error("${blockSet.name}'s group is in error !")
        }
        GameRegistry.registerBlock(this, blockSet.name)
    }

    override fun getDrops(world: World?, x: Int, y: Int, z: Int, metadata: Int, fortune: Int): ArrayList<ItemStack> {
        val itemlist = ArrayList<ItemStack>()
        when(blockSet.drop_item) {
            "null" -> itemlist.add(Sandman.sandman())
            "this" -> itemlist.add(ItemStack(this))
            else -> itemlist.add(ItemStack(findItemByText(blockSet.drop_item)))
        }

        return itemlist
    }
}