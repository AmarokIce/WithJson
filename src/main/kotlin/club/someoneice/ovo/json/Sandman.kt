package club.someoneice.ovo.json

import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

object Sandman {
    fun sandman(): ItemStack {
        return ItemStack(Blocks.air)
    }

    fun nullSandman() {
        // Do nothing.
    }

    // Return a null and it will as any things.
    fun missingNo(): Any? {
        return null
    }
}