package club.someoneice.ovo.core

import club.someoneice.ovo.OVOMain
import club.someoneice.ovo.data.Group
import club.someoneice.ovo.json.data.CoreDataOutput
import club.someoneice.ovo.json.data.helper.RecipeJsonHelper
import club.someoneice.ovo.util.*
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Material
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.ToolMaterials
import net.minecraft.util.Identifier


class DataProcessor(private val mod_id: String?) {
    init {
        toolInfoProcessing()
        toolGroupProcessing()
        toolItemProcessing()
        toolBlockProcessing()
        if (Info.dataMode) {
            recipeDataProcessor()
            // TODO - Lang...
        }
    }

    private fun toolInfoProcessing() {
        Info.modid = this.mod_id ?: OVOMain.modid
        OVOMain.Logger.info("Get modid: $mod_id !")
    }

    private fun toolGroupProcessing() {
        OVOMain.Logger.info("Now create the new group!")

        for (group in DataList.dataGroup) {
            try {
                DataList.getGroup[group.name] = toolCreateGroupHelper(group)
                OVOMain.Logger.info("Create group: ${group.name} !")
            } catch (_: Exception) {
                OVOMain.Logger.error("Cannot create the new group!")
            }
        }
    }

    private fun toolCreateGroupHelper(group: Group): ItemGroup {
        return FabricItemGroupBuilder.build(Identifier(Info.modid, group.name)) {
            ItemStack(Util.findItemByText(group.icon) ?: Items.APPLE)
        }
    }

    private fun toolItemProcessing() {
        for (item in DataList.dataItem) {
            ItemBase(Item.Settings().group(DataList.getGroup[item.group]).maxCount(item.max_size), item)
        }

        for (item in DataList.dataItemFood) {
            val build = FoodComponent.Builder()
            build.hunger(item.hunger)
            build.saturationModifier(item.saturation)
            if (item.wolf) build.meat()
            if (item.fast_food) build.snack()
            if (item.always_eat) build.alwaysEdible()
            ItemFoodsBase(Item.Settings().group(DataList.getGroup[item.group]).maxCount(item.max_size).food(build.build()), item)
        }

        for (item in DataList.dataItemGift) {
            ItemGifts(Item.Settings().group(DataList.getGroup[item.group]).maxCount(item.max_size), item)
        }

        for (tool in DataList.dataItemTool) {
            ItemTools(tool)
        }

        for (weapons in DataList.dataItemWeapons) {
            val mate = when (weapons.tool_meta) {
                "wood" -> ToolMaterials.WOOD
                "stone" -> ToolMaterials.STONE
                "iron" -> ToolMaterials.WOOD
                "gold" -> ToolMaterials.GOLD
                "diamond" -> ToolMaterials.DIAMOND

                else -> ToolMaterials.WOOD
            }
            ItemWeapons(Item.Settings().group(DataList.getGroup[weapons.group]).maxCount(1), weapons, mate)
        }
    }

    private fun toolBlockProcessing() {
        if (DataList.dataBlock.size >= 1) {
            for (block in DataList.dataBlock) {
                BlockBase(AbstractBlock.Settings.of(Material.STONE).strength(block.hard), block)
            }
        }
    }

    public fun recipeDataProcessor() {
        for (recipe in DataList.dataRecipe) {
            CoreDataOutput(RecipeJsonHelper(), recipe)
        }
    }
}