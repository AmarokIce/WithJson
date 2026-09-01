package club.someoneice.ovo.handler

import club.someoneice.json.node.JsonNode
import club.someoneice.json.node.MapNode
import club.someoneice.ovo.ModMain
import club.someoneice.ovo.api.IObjectProcessor
import club.someoneice.ovo.api.ISubtypeProcessor
import club.someoneice.ovo.asTypeOf
import club.someoneice.ovo.core.PacketInfo
import club.someoneice.ovo.createId
import club.someoneice.ovo.createIdStr
import club.someoneice.ovo.getCreativeTab
import club.someoneice.ovo.nullOr
import com.google.common.collect.ImmutableList
import com.google.common.collect.Maps
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemAxe
import net.minecraft.item.ItemFood
import net.minecraft.item.ItemHoe
import net.minecraft.item.ItemPickaxe
import net.minecraft.item.ItemSpade
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemSword
import net.minecraft.potion.PotionEffect
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import java.util.Objects

object ItemHandler: IObjectProcessor<Item> {
  private val ITEM_TYPE = Maps.newHashMap<String, ISubtypeProcessor<Item>>()

  @JvmStatic
  fun addItemType(name: String, subtype: ISubtypeProcessor<Item>) {
    this.ITEM_TYPE[name] = subtype
  }

  override val nodeName: String = "items"

  override fun handle(info: PacketInfo, dat: MapNode): Pair<ResourceLocation, Item> {
    val name = dat["name"].asTypeNodeOrThrow(JsonNode.NodeType.String, "The name of the item can't be used or null!").toString()
    val unlocalname = dat["unlocalname"] asTypeOf name
    val texturename = dat["texturename"] asTypeOf (info createIdStr name)
    val creativeTab = dat["creativetab"] asTypeOf "misc"

    val item = (ITEM_TYPE[dat["type"] asTypeOf "item"] ?: run {
      ModMain.LOGGER.error("Item type {} not found!", dat["type"])
      ITEM_TYPE["item"]!!
    })(dat)

    item.unlocalizedName = unlocalname
    item.setTextureName(texturename)

    item.creativeTab = getCreativeTab(creativeTab)

    GameRegistry.registerItem(item, name)

    return (info createId name) to item
  }

  private fun MapNode.findToolMaterial(): Item.ToolMaterial =
    Item.ToolMaterial.entries
      .firstOrNull { it.name.lowercase() == this["material"] asTypeOf "iron" } ?: Item.ToolMaterial.IRON

  private fun MapNode.findArmorMaterial(): ItemArmor.ArmorMaterial =
    ItemArmor.ArmorMaterial.entries
      .firstOrNull { it.name.lowercase() == this["material"] asTypeOf "iron" } ?: ItemArmor.ArmorMaterial.IRON

  init {
    ITEM_TYPE["item"] = ISubtypeProcessor { Item() }
    ITEM_TYPE["axe"] = ISubtypeProcessor { object: ItemAxe(it.findToolMaterial()) {} }
    ITEM_TYPE["pickaxe"] = ISubtypeProcessor { object: ItemPickaxe(it.findToolMaterial()) {} }
    ITEM_TYPE["hoe"] = ISubtypeProcessor { object: ItemHoe(it.findToolMaterial()) {} }
    ITEM_TYPE["shovel"] = ISubtypeProcessor { object: ItemSpade(it.findToolMaterial()) {} }
    ITEM_TYPE["sword"] = ISubtypeProcessor { object: ItemSword(it.findToolMaterial()) {} }

    ITEM_TYPE["armor_head"] = ISubtypeProcessor { object: ItemArmor(it.findArmorMaterial(), 0, 0) {} }
    ITEM_TYPE["armor_chest"] = ISubtypeProcessor { object: ItemArmor(it.findArmorMaterial(), 0, 1) {} }
    ITEM_TYPE["armor_legs"] = ISubtypeProcessor { object: ItemArmor(it.findArmorMaterial(), 0, 2) {} }
    ITEM_TYPE["armor_boots"] = ISubtypeProcessor { object: ItemArmor(it.findArmorMaterial(), 0, 3) {} }

    ITEM_TYPE["food"] = ISubtypeProcessor {
      object : ItemFood(
        it["hunger"].nullOr(0),
        it["saturation"].nullOr(0.0f),
        it["meat"].nullOr(false)
      ) {

        val potions: List<PotionEffect>
        val itemReturn: ItemStack?

        init {
          val builder = ImmutableList.builder<PotionEffect>()
          it["effects"].asArrayNodeOrEmpty().forEach { nod ->
            val effect = nod.asMapNodeOrEmpty()
            if (effect.isEmpty) {
              return@forEach
            }

            val id = effect["id"].obj as? Int ?: run {
              ModMain.LOGGER.error("Effect id not found!")
              return@forEach
            }
            val time = effect["time"].nullOr(0)
            val lv = effect["level"].nullOr(0)
            builder.add(PotionEffect(id, time, lv))
          }
          potions = builder.build()

          itemReturn = (it["item_return"] asTypeOf "").run {
            if (this.isEmpty()) {
              return@run null
            }

            val rl = this.split(":")
            return@run GameRegistry.findItemStack(
              rl[0].run { this.ifEmpty { "minecraft" } },
              rl[1],
              it["item_return_size"].nullOr(1)
            )
          }
        }

        override fun onEaten(item: ItemStack, world: World, player: EntityPlayer): ItemStack {
          potions.map(::PotionEffect).forEach(player::addPotionEffect)

          if (Objects.nonNull(itemReturn)) {
            itemReturn!!
            val copy = ItemStack(itemReturn.item, itemReturn.stackSize)
            if (!player.inventory.addItemStackToInventory(copy)) {
              val entity = EntityItem(world, player.posX, player.posY, player.posZ, copy)
              world.spawnEntityInWorld(entity)
            }
          }

          return super.onEaten(item, world, player)
        }
      }
    }
  }
}
