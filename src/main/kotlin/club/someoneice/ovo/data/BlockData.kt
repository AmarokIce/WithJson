package club.someoneice.ovo.data

import club.someoneice.ovo.core.CoreHandler
import club.someoneice.ovo.data.helper.Ingredient
import club.someoneice.ovo.util.toBlock
import com.google.common.collect.Lists
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

data class BlockData (
    val name:               String,
    val localizationName:   String                  = name,
    val textureName:        ArrayList<String>       = Lists.newArrayList(name),
    val hard:               Float                   = 0.25f,
    val breakTool:          String                  = "pickaxe",
    val material:           String                  = "minecraft:stone",
    val lightLevel:         Float                   = 0.0f,
    val dropItem:           ArrayList<Ingredient>   = Lists.newArrayList(Ingredient("this", 1, 0)),
    val group:              String                  = "",

    // 0: Simple, 1: All-sides, 2: Log
    val rotationType:       Int                 = 0
) {
    fun registerBlock(): Block {
        val materialBlock = material.toBlock() ?: Blocks.stone
        val block = object: Block(materialBlock.material) {
            init {
                this.setBlockName(localizationName)
                this.setBlockTextureName(this@BlockData.textureName[0])
                this.setStepSound(materialBlock.stepSound)
                this.setHardness(this@BlockData.hard)
                this.setLightLevel(this@BlockData.lightLevel)
            }

            override fun isWood(world: IBlockAccess?, x: Int, y: Int, z: Int): Boolean = this@BlockData.rotationType == 2
            override fun isToolEffective(type: String, metadata: Int): Boolean = type == this@BlockData.breakTool
            override fun getDrops(world: World?, x: Int, y: Int, z: Int, metadata: Int, fortune: Int) =
                Lists.newArrayList(this@BlockData.dropItem.map {
                    if (it.name == "this") ItemStack(this, it.size, it.meta) else it.toItemStack()
                })
        }

        CoreHandler.ITEM_GROUP[this.group]?.let(block::setCreativeTab)
            ?: CoreHandler.BLOCK_GROUP_CACHE.getOrPut(this.group, ::ArrayList).add(block)

        return block
    }
}