package club.someoneice.ovo.util

import club.someoneice.ovo.core.`object`.DataList
import club.someoneice.ovo.data.ItemTool
import club.someoneice.ovo.json.Sandman
import club.someoneice.ovo.util.tool.ItemAxes
import club.someoneice.ovo.util.tool.ItemHoes
import club.someoneice.ovo.util.tool.ItemPickaxes
import club.someoneice.ovo.util.tool.ItemShovels
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterials

class ItemTools(toolSet: ItemTool) {
    init {
        val settings: Item.Settings = Item.Settings()
        settings.group(DataList.getGroup[toolSet.group])
        settings.maxCount(1)

        val mate: ToolMaterials = when (toolSet.tool_meta) {
            "wood" -> ToolMaterials.WOOD
            "stone" -> ToolMaterials.STONE
            "iron" -> ToolMaterials.WOOD
            "gold" -> ToolMaterials.GOLD
            "diamond" -> ToolMaterials.DIAMOND

            else -> ToolMaterials.WOOD
        }

        when (toolSet.toolkit) {
            "axe" -> ItemAxes(settings, mate, toolSet)
            "pickaxe" -> ItemPickaxes(settings, mate, toolSet)
            "hoe" -> ItemHoes(settings, mate, toolSet)
            "shovel" -> ItemShovels(settings, mate, toolSet)

            else -> Sandman.nullSandman()
        }

    }
}