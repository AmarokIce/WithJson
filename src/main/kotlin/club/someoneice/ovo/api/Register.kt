package club.someoneice.ovo.api

import club.someoneice.json.node.ArrayNode
import club.someoneice.json.node.JsonNode
import club.someoneice.json.node.MapNode
import club.someoneice.ovo.asRl
import club.someoneice.ovo.core.PacketInfo
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.google.common.collect.ImmutableBiMap
import net.minecraft.util.ResourceLocation
import javax.annotation.CheckForNull

data class Register<T>(
  private val nodeHandler: IObjectProcessor<T>,
  private val registry: BiMap<ResourceLocation, T> = HashBiMap.create()
) {
  fun getRootName(): String = this.nodeHandler.nodeName

  fun handleAndRegister(msg: PacketInfo, nodes: ArrayNode) {
    nodes.forEach { node ->
      if (node.type != JsonNode.NodeType.Map) {
        return@forEach
      }

      val (rl, obj) = this.nodeHandler.handle(msg, node as MapNode)
      registry[rl] = obj
    }
  }

  fun copyMap(): ImmutableBiMap<ResourceLocation, T> {
    return ImmutableBiMap.copyOf(registry)
  }

  @CheckForNull
  fun find(name: String): T? {
    return find(name.asRl())
  }

  @CheckForNull
  fun find(rl: ResourceLocation): T? {
    return registry[rl]
  }

  @CheckForNull
  fun findName(dat: T): ResourceLocation? {
    return registry.inverse()[dat]
  }
}
