package club.someoneice.ovo.util

import club.someoneice.ovo.core.`object`.Info
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import project.studio.manametalmod.core.ItemStackOre

object Util {
    fun findItemByText(itemName: String): Item? {
        return Item.itemRegistry.getObject(itemName) as Item? ?: Item.getItemFromBlock(Block.getBlockFromName(itemName))
    }

    fun findBlockByText(blockName: String): Item? {
        return Item.getItemFromBlock(Block.getBlockFromName(blockName))
    }
}

fun Block.register(name: String) {
    GameRegistry.registerBlock(this, name)
}

fun Item.register(name: String) {
    GameRegistry.registerItem(this, name, Info.modid)
}

fun Item.itemStack(): ItemStack {
    return ItemStack(this)
}

fun Block.itemStack(): ItemStack {
    return ItemStack(this)
}

fun ItemStack.ore(): ItemStackOre {
    return ItemStackOre(this)
}

fun ItemStack.setSize(size: Int): ItemStack {
    this.stackSize = size
    return this
}

fun Item.ore(): ItemStackOre {
    return ItemStackOre(this)
}

val gson: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()