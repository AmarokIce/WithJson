package club.someoneice.ovo.util.tool

import club.someoneice.ovo.core.DataList
import club.someoneice.ovo.data.ItemTool
import club.someoneice.ovo.json.Sandman
import net.minecraft.item.Item
import net.minecraft.item.Item.Settings
import net.minecraft.item.ToolMaterials

class Helper(private val axe: ToolAxe, private val hoe: ToolHoe, private val pickaxe: ToolPickaxe, private val shovel: ToolShovel, private val toolSet: ItemTool): ToolTypeGetter() {
    override fun getType(typeChooser: String) {
        val settings: Item.Settings = Item.Settings()
        settings.group(DataList.getGroup[toolSet.group])
        settings.maxCount(1)

        val mate: ToolMaterials = when (toolSet.tool_meta) {
            "wood"      -> ToolMaterials.WOOD
            "stone"     -> ToolMaterials.STONE
            "iron"      -> ToolMaterials.WOOD
            "gold"      -> ToolMaterials.GOLD
            "diamond"   -> ToolMaterials.DIAMOND

            else        -> ToolMaterials.WOOD
        }

        when (typeChooser) {
            "axe"       -> makeAxe      (settings, toolSet, mate)
            "pickaxe"   -> makePickaxe  (settings, toolSet, mate)
            "hoe"       -> makeHoe      (settings, toolSet, mate)
            "shovel"    -> makeShovel   (settings, toolSet, mate)

            else        -> Sandman.nullSandman()
        }
    }

    private fun makeAxe(settings: Settings, toolSet: ItemTool, toolMate: ToolMaterials) {
        axe.toolInit(settings, toolSet, toolMate)
    }

    private fun makePickaxe(settings: Settings, toolSet: ItemTool, toolMate: ToolMaterials) {
        pickaxe.toolInit(settings, toolSet, toolMate)
    }

    private fun makeHoe(settings: Settings, toolSet: ItemTool, toolMate: ToolMaterials) {
        hoe.toolInit(settings, toolSet, toolMate)
    }

    private fun makeShovel(settings: Settings, toolSet: ItemTool, toolMate: ToolMaterials) {
        shovel.toolInit(settings, toolSet, toolMate)
    }
}