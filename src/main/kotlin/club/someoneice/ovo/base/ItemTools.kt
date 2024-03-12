package club.someoneice.ovo.base

import club.someoneice.ovo.base.tool.ItemAxes
import club.someoneice.ovo.base.tool.ItemHoes
import club.someoneice.ovo.base.tool.ItemPickaxe
import club.someoneice.ovo.base.tool.ItemShovels
import club.someoneice.ovo.data.ItemTool
import club.someoneice.ovo.json.Sandman
import net.minecraft.item.Item

class ItemTools(toolSet: ItemTool) {
    init {
        val mate: Item.ToolMaterial = when (toolSet.tool_meta) {
            "wood" -> Item.ToolMaterial.WOOD
            "stone" -> Item.ToolMaterial.STONE
            "iron" -> Item.ToolMaterial.WOOD
            "gold" -> Item.ToolMaterial.GOLD
            "diamond" -> Item.ToolMaterial.EMERALD

            else -> Item.ToolMaterial.WOOD
        }

        when (toolSet.toolkit) {
            "axe" ->        ItemAxes(mate, toolSet)
            "pickaxe" ->    ItemPickaxe(mate, toolSet)
            "hoe" ->        ItemHoes(mate, toolSet)
            "shovel" ->     ItemShovels(mate, toolSet)

            else ->         Sandman.nullSandman()
        }
    }
}