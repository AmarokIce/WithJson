package club.someoneice.ovo.core

import club.someoneice.ovo.api.IObjectProcessor
import club.someoneice.ovo.api.Register
import club.someoneice.ovo.handler.BlockHandler
import club.someoneice.ovo.handler.BlockMaterialHandler
import club.someoneice.ovo.handler.CreativeTabHandler
import club.someoneice.ovo.handler.ItemHandler
import com.google.common.collect.Lists
import net.minecraft.block.material.Material

object Registries {
  val REGISTRIES = Lists.newLinkedList<Register<*>>()

  val CREATIVE_TABS = addRegistry(CreativeTabHandler)
  val BLOCK_MATERIAL = addRegistry(BlockMaterialHandler)
  val ITEMS = addRegistry(ItemHandler)
  val BLOCKS = addRegistry(BlockHandler)


  @JvmStatic
  fun <T> addRegistry(nodeHandler: IObjectProcessor<T>): Register<T> = addRegistry(Register(nodeHandler))
  @JvmStatic
  fun <T> addRegistry(registers: Register<T>): Register<T> = registers.apply(REGISTRIES::add)

  fun <T> addRegistryAt(register: Register<T>, target: Register<T>): Register<T> {
    val index = REGISTRIES.indexOf(target)
    if (index == -1) {
      REGISTRIES.add(register)
      return register
    }

    if (index == 0) {
      REGISTRIES.addFirst(register)
    }

    REGISTRIES.add(index, target)
    return register
  }

  fun <T> addRegistryFirst(register: Register<T>): Register<T> {
    REGISTRIES.addFirst(register)
    return register
  }
}
