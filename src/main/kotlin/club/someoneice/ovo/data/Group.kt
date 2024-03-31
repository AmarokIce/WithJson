package club.someoneice.ovo.data

import club.someoneice.ovo.core.CoreHandler
import club.someoneice.ovo.data.helper.Ingredient
import club.someoneice.ovo.util.toItem
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

data class Group (
    val name: String,
    val icon: Ingredient = Ingredient("minecraft:apple")
) {
    fun registerAndScanItemCache(modid: String) {
        val tab = object: CreativeTabs(this@Group.name) {
            override fun getTabIconItem() = this@Group.icon.name.toItem()
            override fun func_151243_f() = this@Group.icon.meta
        }

        fun setTab(item: Item) {
            item.setCreativeTab(tab)
        }

        fun setTab(block: Block) {
            block.setCreativeTab(tab)
        }

        val groupName = "${modid}:${this.name}"
        CoreHandler.ITEM_GROUP[groupName] = tab
        CoreHandler.ITEM_GROUP_CACHE[groupName]?.forEach(::setTab)
        CoreHandler.BLOCK_GROUP_CACHE[groupName]?.forEach(::setTab)
    }
}
