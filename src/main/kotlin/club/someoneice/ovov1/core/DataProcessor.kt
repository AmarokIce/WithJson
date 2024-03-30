package club.someoneice.ovov1.core

import club.someoneice.ovo.core.OVOMain
import club.someoneice.ovo.data.old.Group
import club.someoneice.ovo.data.old.Recipe
import club.someoneice.ovov1.base.*
import club.someoneice.ovov1.core.`object`.DataList
import club.someoneice.ovov1.core.`object`.Info
import club.someoneice.ovov1.core.`object`.RemoveRecipes
import club.someoneice.ovov1.util.Util.findItemByText
import club.someoneice.ovov1.util.itemStack
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.ShapedOreRecipe
import net.minecraftforge.oredict.ShapelessOreRecipe

class DataProcessor(private val mod_id: String?) {

    init {
        OVOMain.Logger.info("Now DataProcessor is running!")
    }

    fun init() {
        infoProcessing()            // Init the json pockage.
        groupProcessing()           // Add creativeTabs. And put them in groupMap.
        itemDataProcessing()        // Add and registry the Items.
        blockDataProcessing()       // Add and registry the Blocks and BlockItems.
        recipesDeletRecipe()        // Delete the recipes.
        addRecipes()                // Then add new recipes.
        addBiomes()                 // Add the biomes.
    }

    private fun infoProcessing() {
        Info.modid = this.mod_id ?: OVOMain.MODID

        OVOMain.Logger.info("Get modid: $mod_id !")
    }

    private fun groupProcessing() {
        OVOMain.Logger.info("Now create the new group!")

        for (group in DataList.dataGroup) {
            try {
                DataList.getGroup["${Info.modid}:${group.name}"] = tabCreateGroup(group)
                OVOMain.Logger.info("Create group: ${group.name} !")
            } catch (_: Exception) {
                OVOMain.Logger.error("Cannot create the new group!")
            }
        }
    }

    private fun tabCreateGroup(group: Group): CreativeTabs {
        return object : CreativeTabs(group.name) {
            override fun getTabIconItem(): Item {
                return findItemByText(group.icon) ?: Items.apple
            }
        }
    }

    private fun recipesDeletRecipe() {
        for (itemName in DataList.dataDeleteRecipes) {
            val item = findItemByText(itemName) ?: continue
            RemoveRecipes.removeFurnaceRecipes(item.itemStack())
            RemoveRecipes.removeItemRecipes(item.itemStack())
        }
    }

    private fun itemDataProcessing() {
        OVOMain.Logger.info("Now create new items!")

        for (item in DataList.dataItem)     ItemBase(item)
        for (item in DataList.dataItemFood) ItemFoodsBase(item.hunger, item.saturation, item.wolf, item)
        for (item in DataList.dataItemGift) ItemGifts(item)
        for (tool in DataList.dataItemTool) ItemTools.registerTool(tool)
        for (weapons in DataList.dataItemWeapons) {
            val mate = when (weapons.tool_meta) {
                "wood"      -> Item.ToolMaterial.WOOD
                "stone"     -> Item.ToolMaterial.STONE
                "iron"      -> Item.ToolMaterial.WOOD
                "gold"      -> Item.ToolMaterial.GOLD
                "diamond"   -> Item.ToolMaterial.EMERALD

                else        -> Item.ToolMaterial.WOOD
            }
            ItemWeapons(weapons, mate)
        }
    }

    private fun addRecipes() {
        val recipesShapelessList = ArrayList<Recipe>()
        val recipeCookingList = ArrayList<Recipe>()
        val recipesShapedList = ArrayList<Recipe>()

        OVOMain.Logger.debug("recipes size: " + DataList.dataRecipes.size.toString())

        for (recipe in DataList.dataRecipes) {
            when (recipe.type) {
                "minecraft:crafting_shapeless"  -> recipesShapelessList.add(recipe)
                "minecraft:crafting_shaped"     -> recipesShapedList.add(recipe)
                "minecraft:smelting"            -> recipeCookingList.add(recipe)
            }
        }

        recipesShapelessHandler(recipesShapelessList)
        recipesShapedHandler(recipesShapedList)
        recipesCookingHandler(recipeCookingList)
    }

    private fun recipesShapelessHandler(list: ArrayList<Recipe>) {
        OVOMain.Logger.info("Now start create recipes Shapeless.")
        var r = 0
        for (recipe in list) {
            val itemList = ArrayList<Any>()
            val output: Item = if (recipe.result.containsKey("item")) findItemByText(recipe.result["item"]!!) ?: return else continue
            for (item in recipe.ingredients!!) {
                itemList.add (
                    if (item.containsKey("item"))           ItemStack(findItemByText(item["item"]!!) ?: continue, 1, item["meta"]?.toInt() ?: 0)
                    else if (item.containsKey("block"))     ItemStack(findItemByText(item["block"]!!) ?: continue, 1, item["meta"]?.toInt() ?: 0)
                    else if (item.containsKey("oredict"))   (item["oredict"]!!)
                    else continue
                )
            }

            r += 1
            GameRegistry.addRecipe(ShapelessOreRecipe(ItemStack(output, if (recipe.result.containsKey("count")) Integer.valueOf(recipe.result["count"]) else 1), itemList.toArray()))
        }
        OVOMain.Logger.info("Register $r recipe in Shapeless.")
    }

    private fun recipesCookingHandler(list: ArrayList<Recipe>) {
        OVOMain.Logger.info("Now start create recipes Cooking.")
        var r = 0

        for (recipe in list) {
            val output: Item =
                if (recipe.result.containsKey("item")) findItemByText(recipe.result["item"]!!) ?: continue else continue
            val item =
                if (recipe.ingredients!![0].containsKey("item")) findItemByText(recipe.ingredients[0]["item"]!!) ?: continue
                else if (recipe.ingredients[0].containsKey("block")) findItemByText(recipe.ingredients[0]["block"]!!) ?: continue
                else continue

            val i = if (recipe.result.containsKey("count")) Integer.valueOf(recipe.result["count"]) else 1

            r += 1
            GameRegistry.addSmelting(item, ItemStack(output, i), 0.5F)
        }

        OVOMain.Logger.info("Register $r recipe in Smelting.")
    }

    private fun recipesShapedHandler(list: ArrayList<Recipe>) {
        OVOMain.Logger.info("Now start create recipes Shaped.")
        var r = 0

        for (recipe in list) {
            val itemList = ArrayList<Any>()
            for (i in recipe.pattern!!)
                itemList.add(i)

            for (key in recipe.key!!.keys) {
                if (recipe.key[key]!!.containsKey("item")) {
                    itemList.add(key[0])
                    itemList.add(findItemByText(recipe.key[key]!!["item"]!!) ?: continue)
                } else if (recipe.key[key]!!.containsKey("block")) {
                    itemList.add(key[0])
                    itemList.add(findItemByText(recipe.key[key]!!["block"]!!) ?: continue)
                } else if (recipe.key[key]!!.containsKey("oredict")) {
                    itemList.add(key[0])
                    itemList.add(recipe.key[key]!!["oredict"]!!)
                }
            }

            val output = if (recipe.result.containsKey("item")) findItemByText(recipe.result["item"]!!) ?: continue else continue

            r += 1
            GameRegistry.addRecipe(ShapedOreRecipe(ItemStack(output, if (recipe.result.containsKey("count")) Integer.valueOf(recipe.result["count"]) else 1), itemList.toArray()))
        }

        OVOMain.Logger.info("Register $r recipe in Shaped.")
    }

    private fun blockDataProcessing() {
        if (DataList.dataBlock.size > 0) {
            for (block in DataList.dataBlock) {
                BlockBase(Material.rock, block)
            }
        }
    }

    private fun addBiomes() {
        for (biome in DataList.dataBiomes) {
            BiomesBase(biome)
        }
    }
}