package club.someoneice.ovo

import club.someoneice.json.node.JsonNode
import club.someoneice.ovo.core.PacketInfo
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import scala.tools.nsc.typechecker.DestructureTypes.`DestructureType$class`.node

fun getCreativeTab(name: String): CreativeTabs =
  CreativeTabs.creativeTabArray.firstOrNull { name == it.tabLabel } ?: CreativeTabs.tabMisc

infix fun PacketInfo.createId(path: String): ResourceLocation = ResourceLocation(this.id, path)
infix fun PacketInfo.createIdStr(path: String): String = this.id + ":" + path

infix fun ResourceLocation.createStack(size: Int = 1): ItemStack = GameRegistry.findItemStack(this.resourceDomain, this.resourcePath, size)
fun ResourceLocation.createItem(): Item = GameRegistry.findItem(this.resourceDomain, this.resourcePath)

fun String.asRl(): ResourceLocation = ResourceLocation(this)

infix fun <T> JsonNode<*>.asTypeOf(elseOf: T): T {
  return (this nullOr elseOf)
}

@Suppress("UNCHECKED_CAST")
infix fun <T> JsonNode<*>.nullOr(elseOf: T): T {
  return if (this.isNull) {
    elseOf
  } else {
    this.obj as? T
      ?: elseOf
  }
}
