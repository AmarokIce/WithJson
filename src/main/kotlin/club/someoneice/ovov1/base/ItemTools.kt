package club.someoneice.ovov1.base

import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.data.old.ItemToolData
import club.someoneice.ovov1.core.`object`.DataList
import club.someoneice.ovov1.core.`object`.Info
import club.someoneice.ovov1.util.register
import net.minecraft.item.*

object ItemTools {
    private val toolData = HashMap<String, (ItemToolData) -> Unit>()

    init {
        toolData["axe"]     = { setData((object : ItemAxe(getMeta(it.tool_meta)) {}), it)       }
        toolData["pickaxe"] = { setData((object : ItemPickaxe(getMeta(it.tool_meta)) {}), it)   }
        toolData["hoe"]     = { setData((object : ItemHoe(getMeta(it.tool_meta)) {}), it)       }
        toolData["shovel"]  = { setData((object : ItemSpade(getMeta(it.tool_meta)) {}), it)     }
    }

    private fun setData(item: Item, toolSet: ItemToolData) {
        item.unlocalizedName = toolSet.localization_name
        item.setTextureName("${Info.modid}:${toolSet.texture_name}")
        item.setMaxStackSize(1)
        if (DataList.getGroup.containsKey(toolSet.group)) item.creativeTab = DataList.getGroup[toolSet.group]
        else OVOMain.Logger.error("${toolSet.name}'s group is in error !")

        item.register(toolSet.name)
    }


    private fun getMeta(name: String) = when (name.lowercase()) {
            "wood"      -> Item.ToolMaterial.WOOD
            "stone"     -> Item.ToolMaterial.STONE
            "iron"      -> Item.ToolMaterial.WOOD
            "gold"      -> Item.ToolMaterial.GOLD
            "diamond"   -> Item.ToolMaterial.EMERALD

            else        -> Item.ToolMaterial.WOOD
    }

    fun registerTool(toolSet: ItemToolData) {
        val kit = toolSet.toolkit.lowercase()
        if (!toolData.containsKey(kit)) return
        toolData[kit]!!(toolSet)
    }
}