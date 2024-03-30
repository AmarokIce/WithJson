package club.someoneice.ovo.data.helper

import club.someoneice.ovo.util.setSize
import club.someoneice.ovo.util.toItemStack
import net.minecraft.item.ItemStack

data class Ingredient(
    val name: String,
    val size: Int       = 1,
    val meta: Int       = 0
) {
    fun toItemStack(): ItemStack? = name.toItemStack(meta)?.setSize(size)
}
