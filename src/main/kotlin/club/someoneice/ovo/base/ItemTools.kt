package club.someoneice.ovo.base

import club.someoneice.ovo.base.tool.*
import club.someoneice.ovo.data.ItemTool
import club.someoneice.ovo.json.Sandman

class ItemTools(toolSet: ItemTool) {
    init {
        val axe = ToolAxe()
        val pickaxe = ToolPickaxe()
        val hoe = ToolHoe()
        val shovel = ToolShovel()
        val helper: ToolTypeGetter = Helper(axe, hoe, pickaxe, shovel, toolSet)
        when(toolSet.toolkit) {
            "axe"       -> axe.getToolType(helper)
            "hoe"       -> hoe.getToolType(helper)
            "pickaxe"   -> pickaxe.getToolType(helper)
            "shovel"    -> shovel.getToolType(helper)

            else        -> Sandman.nullSandman()
        }
    }
}