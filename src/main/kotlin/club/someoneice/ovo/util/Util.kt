package club.someoneice.ovo.util

import club.someoneice.ovo.core.Info
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import project.studio.manametalmod.core.ItemStackOre
import javax.annotation.Nullable

object Util {

    @Nullable
    fun findItemByText(itemName: String): Item? {
        if (itemName == "null") return null
        return Item.itemRegistry.getObject(itemName) as Item? ?: Item.getItemById(Integer.valueOf(itemName)) ?:
               Item.getItemFromBlock(Block.getBlockFromName(itemName)) ?: Item.getItemFromBlock(Block.getBlockById(Integer.valueOf(itemName)))
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