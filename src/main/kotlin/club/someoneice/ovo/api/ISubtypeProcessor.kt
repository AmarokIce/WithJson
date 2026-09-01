package club.someoneice.ovo.api

import club.someoneice.json.node.MapNode

@FunctionalInterface
fun interface ISubtypeProcessor<T> {
  operator fun invoke(node: MapNode): T
}
