package club.someoneice.ovo.util

import club.someoneice.ovo.core.Info
import club.someoneice.ovo.json.Sandman
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import project.studio.manametalmod.core.ItemStackOre
import javax.annotation.Nullable

object Util {

    @Nullable
    fun findItemByText(Item: String): Item? {
        if (Item == "null") return null
        var item: Item? = net.minecraft.item.Item.itemRegistry.getObject(Item) as Item?

        if (item == null) {
            try {
                item = net.minecraft.item.Item.getItemById(Integer.valueOf(Item))
            } catch (_: NumberFormatException) {
                Sandman.nullSandman()
            }
        }

        return item
    }
}

fun Block.register(name: String) {
    GameRegistry.registerBlock(this, name)
}

fun Item.register(name: String) {
    GameRegistry.registerItem(this, name, Info.modid)
}

fun Item.itemStack(): ItemStack {
    return ItemStack(this);
}

fun Block.itemStack(): ItemStack {
    return ItemStack(this)
}

fun ItemStack.ore(): ItemStackOre {
    return ItemStackOre(this)
}

fun Item.ore(): ItemStackOre {
    return ItemStackOre(this)
}