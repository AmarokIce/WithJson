package club.someoneice.ovo.util

import club.someoneice.json.JSON
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import java.io.File

fun String.toItem(): Item? {
    val name = ResourceLocation(this)
    return GameRegistry.findItem(name.resourceDomain, name.resourcePath)
}

fun String.toBlock(): Block? {
    val name = ResourceLocation(this)
    return GameRegistry.findBlock(name.resourceDomain, name.resourcePath)
}

fun String.toItemStack(meta: Int = 0): ItemStack? {
    return this.toItem()?.let { ItemStack(it, 1, meta) }
}

fun String.path(): String {
    return this.replace("/", File.pathSeparator)
}

fun Block.register(name: String) {
    GameRegistry.registerBlock(this, name)
}

fun Item.register(name: String, modid: String) {
    GameRegistry.registerItem(this, name, modid)
}

fun Item.defaultState(): ItemStack {
    return ItemStack(this)
}

fun Block.defaultState(): ItemStack {
    return ItemStack(this)
}

fun ItemStack.setSize(size: Int): ItemStack {
    this.stackSize = size
    return this
}

fun EntityPlayer.giveOrThrowOut(item: ItemStack) {
    if (this.inventory.addItemStackToInventory(item)) return
    if (this.worldObj.isRemote) return
    this.worldObj.spawnEntityInWorld(EntityItem(this.worldObj, this.posX, this.posY, this.posZ, item))
}

val json: JSON = JSON.json5