package club.someoneice.ovo.base.tool

import club.someoneice.ovo.data.ItemTool
import club.someoneice.ovo.json.Sandman
import net.minecraft.item.Item

class Helper(private val axe: ToolAxe, private val hoe: ToolHoe, private val pickaxe: ToolPickaxe, private val shovel: ToolShovel, private val toolSet: ItemTool): ToolTypeGetter() {
    override fun getType(typeChooser: String) {
        val mate: Item.ToolMaterial = when (toolSet.tool_meta) {
            "wood"      -> Item.ToolMaterial.WOOD
            "stone"     -> Item.ToolMaterial.STONE
            "iron"      -> Item.ToolMaterial.WOOD
            "gold"      -> Item.ToolMaterial.GOLD
            "diamond"   -> Item.ToolMaterial.EMERALD

            else        -> Item.ToolMaterial.WOOD
        }

        when (typeChooser) {
            "axe"       -> makeAxe      (toolSet, mate)
            "pickaxe"   -> makePickaxe  (toolSet, mate)
            "hoe"       -> makeHoe      (toolSet, mate)
            "shovel"    -> makeShovel   (toolSet, mate)

            else        -> Sandman.nullSandman()
        }
    }

    private fun makeAxe(toolSet: ItemTool, toolMate: Item.ToolMaterial) {
        axe.toolInit(toolSet, toolMate)
    }

    private fun makePickaxe(toolSet: ItemTool, toolMate: Item.ToolMaterial) {
        pickaxe.toolInit(toolSet, toolMate)
    }

    private fun makeHoe(toolSet: ItemTool, toolMate: Item.ToolMaterial) {
        hoe.toolInit(toolSet, toolMate)
    }

    private fun makeShovel(toolSet: ItemTool, toolMate: Item.ToolMaterial) {
        shovel.toolInit(toolSet, toolMate)
    }
}