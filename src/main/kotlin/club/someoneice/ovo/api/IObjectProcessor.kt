package club.someoneice.ovo.api

import club.someoneice.json.node.JsonNode
import club.someoneice.json.node.MapNode
import club.someoneice.ovo.core.PacketInfo
import net.minecraft.util.ResourceLocation

interface IObjectProcessor<T> {
  val nodeName: String

  fun handle(info: PacketInfo, dat: MapNode): Pair<ResourceLocation, T>
}
