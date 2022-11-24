package club.someoneice.ovo.json

import net.minecraft.block.Blocks
import net.minecraft.item.ItemStack

object Sandman {
    fun sandman(): ItemStack {
        return ItemStack(Blocks.AIR)
    }

    fun nullSandman() {
        // Do nothing.
    }

    // Return a null and it will as any things.
    fun missingNo(): Any? {
        return null
    }
}