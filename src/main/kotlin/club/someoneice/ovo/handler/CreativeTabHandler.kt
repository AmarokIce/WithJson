package club.someoneice.ovo.handler

import club.someoneice.json.node.JsonNode
import club.someoneice.json.node.MapNode
import club.someoneice.ovo.api.IObjectProcessor
import club.someoneice.ovo.asTypeOf
import club.someoneice.ovo.core.PacketInfo
import club.someoneice.ovo.createId
import club.someoneice.ovo.createItem
import club.someoneice.ovo.nullOr
import com.google.common.base.Suppliers
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

object CreativeTabHandler: IObjectProcessor<CreativeTabs> {
  override val nodeName: String = "creative_tab"

  override fun handle(info: PacketInfo, dat: MapNode): Pair<ResourceLocation, CreativeTabs> {
    val name = dat["name"].asTypeNodeOrThrow(JsonNode.NodeType.String, "The name of the creative tab can't be used or null!").toString()
    val item = ResourceLocation(dat["icon"] asTypeOf "minecraft:apple")
    val tab = object: CreativeTabs(name) {
      val iconItem = Suppliers.memoize(item::createItem)
      override fun getTabIconItem(): Item = iconItem.get()
      override fun hasSearchBar(): Boolean = dat["searchbar"].nullOr(false)
    }

    return (info createId name) to tab
  }
}
