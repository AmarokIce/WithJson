package club.someoneice.ovo.handler

import club.someoneice.json.Nodes
import club.someoneice.json.node.IntegerNode
import club.someoneice.json.node.JsonNode
import club.someoneice.json.node.MapNode
import club.someoneice.json.node.StringNode
import club.someoneice.ovo.api.IObjectProcessor
import club.someoneice.ovo.asRl
import club.someoneice.ovo.asTypeOf
import club.someoneice.ovo.core.PacketInfo
import club.someoneice.ovo.core.Registries
import club.someoneice.ovo.createId
import club.someoneice.ovo.getCreativeTab
import club.someoneice.ovo.nullOr
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.block.Block
import net.minecraft.block.BlockDirectional
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.IIcon
import net.minecraft.util.MathHelper
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import scala.tools.reflect.StdRuntimeTags.u


object BlockHandler: IObjectProcessor<Block> {
  override val nodeName: String = "block"
  override fun handle(
    info: PacketInfo,
    dat: MapNode
  ): Pair<ResourceLocation, Block> {
    val name = dat["name"].asTypeNodeOrThrow(JsonNode.NodeType.String, "The name of the item can't be used or null!").toString()
    val unlocalname = dat["unlocalname"] asTypeOf name
    val creativeTab = dat["creativetab"] asTypeOf "misc"
    val materialBlock = (dat["material"] asTypeOf "minecraft:dirt").asRl()
    val material: Material = Registries.BLOCK_MATERIAL.find(materialBlock)
      ?: GameRegistry.findBlock(materialBlock.resourceDomain, materialBlock.resourcePath).material
    val rotation = dat["rotation"] asTypeOf 0
    val texturename = getTextureName(dat)
    val textureface = dat["textureface"].asArrayNodeOrEmpty().let {
      return@let if (it.isNotEmpty()) {
        IntArray(6) { index -> it[index] asTypeOf 0 }
      } else {
        IntArray(6) { 0 }
      }
    }


    return (info createId name) to createBlock(
      rotation, material, texturename, textureface).apply {
      this.setCreativeTab(getCreativeTab(creativeTab))
      this.setBlockName(unlocalname)
    }
  }

  @Throws(IllegalArgumentException::class)
  private fun getTextureName(dat: MapNode): Array<String> {
    val node = dat["texturename"].nullOr(StringNode("minecraft:missingno")).asTypeNode()

    return when(node.type) {
      JsonNode.NodeType.String -> node.obj.let {
        arrayOf(it)
      }
      JsonNode.NodeType.Array -> node.asArrayNodeOrEmpty()
        .filterIsInstance<StringNode>()
        .map(StringNode::getObj)
        .toTypedArray()
      else -> arrayOf("")
    }
  }

  private fun createBlock(rotationType: Int, blockMaterial: Material, iconInput: Array<String>, faceIcon: IntArray): Block {
    return when(rotationType) {
      1 -> object: BlockDirectional(blockMaterial) {
        @SideOnly(Side.CLIENT)
        val icons: Array<IIcon?> = arrayOfNulls(iconInput.size)

        override fun registerBlockIcons(register: IIconRegister) {
          for ((index, icon) in iconInput.withIndex()) {
            icons[index] = register.registerIcon(icon)
          }
        }

        override fun onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, entity: EntityLivingBase, stack: ItemStack) {
          val direction: Int = MathHelper.floor_double(
            (entity.rotationYaw * 4.0f / 360.0f).toDouble() + 0.5) and 3
          world.setBlockMetadataWithNotify(x, y, z, direction, 2)
        }

        override fun getIcon(side: Int, meta: Int): IIcon = icons[faceIcon[FACE_ROTATION[meta][side]]]!!
      }
      2 -> object: BlockDirectional(blockMaterial) {
          @SideOnly(Side.CLIENT)
          val icons: Array<IIcon?> = arrayOfNulls(iconInput.size)

          override fun registerBlockIcons(register: IIconRegister) {
            for ((index, icon) in iconInput.withIndex()) {
              icons[index] = register.registerIcon(icon)
            }
          }

          override fun onBlockPlaced(world: World, x: Int, y: Int, z: Int, side: Int,
                                   hitX: Float, hitY: Float, hitZ: Float, meta: Int): Int {
          return side
        }

        override fun getIcon(side: Int, meta: Int): IIcon = icons[faceIcon[ALL_ROTATION[meta][side]]]!!
      }
      else -> object: Block(blockMaterial) {
        init {
          this.setBlockTextureName(iconInput[0])
        }
      }
    }
  }

  private val ALL_ROTATION
    = arrayOf(
    intArrayOf(2, 3, 1, 0, 5, 4),
    intArrayOf(3, 2, 0, 1, 4, 5),
    intArrayOf(0, 1, 2, 3, 4, 5),
    intArrayOf(0, 1, 3, 2, 5, 4),
    intArrayOf(0, 1, 5, 4, 2, 3),
    intArrayOf(0, 1, 4, 5, 3, 2)
  )

  private val FACE_ROTATION
    = arrayOf(
    intArrayOf( 0, 1, 2, 3, 4, 5 ),
    intArrayOf( 0, 1, 3, 2, 5, 4 ),
    intArrayOf( 0, 1, 5, 4, 2, 3 ),
    intArrayOf( 0, 1, 4, 5, 3, 2 )
  )
}
