package club.someoneice.ovo.core

import club.someoneice.ovo.RecipesHelper
import club.someoneice.ovo.base.*
import club.someoneice.ovo.data.Group
import club.someoneice.ovo.data.Recipe
import club.someoneice.ovo.util.Util.findItemByText
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

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
        Info.modid = this.mod_id ?: OVOMain.modid

        OVOMain.Logger.info("Get modid: $mod_id !")
    }

    private fun groupProcessing() {
        OVOMain.Logger.info("Now create the new group!")

        for (group in DataList.dataGroup) {
            try {
                DataList.getGroup[group.name] = tabCreateGroup(group)
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
        for (item in DataList.dataDeleteRecipes) {
            RemoveRecipes.removeFurnaceRecipes(ItemStack(item))
            RemoveRecipes.removeItemRecipes(ItemStack(item))
        }
    }

    private fun itemDataProcessing() {
        OVOMain.Logger.info("Now create new items!")

        for (item in DataList.dataItem) {
            ItemBase(item)
        }

        for (item in DataList.dataItemFood) {
            ItemFoodsBase(item.hunger, item.saturation, item.wolf, item)
        }

        for (item in DataList.dataItemGift) {
            ItemGifts(item)
        }

        for (tool in DataList.dataItemTool) {
            ItemTools(tool)
        }

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
                    if (item.containsKey("item"))           (findItemByText(item["item"]!!)  ?: continue)
                    else if (item.containsKey("block"))     (findItemByText(item["block"]!!) ?: continue)
                    else if (item.containsKey("oredict"))   (item["oredict"]!!)
                    else continue
                )
            }

            r += 1
            RecipesHelper.addShapelessRecipe(ItemStack(output, if (recipe.result.containsKey("count")) Integer.valueOf(recipe.result["count"]) else 1), itemList.toArray())
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
            RecipesHelper.addShapedRecipe(ItemStack(output, if (recipe.result.containsKey("count")) Integer.valueOf(recipe.result["count"]) else 1), itemList.toArray())
        }

        OVOMain.Logger.info("Register $r recipe in Shaped.")
    }


    /*
        private fun addRecipes() {
            OVOMain.Logger.info("Now create new recipes!")

            for (recipe in DataList.dataRecipes) {
                when (recipe.type) {

                    "crafting_shaped" -> {
                        val reciList = ArrayList<String>()
                        val charList = ArrayList<String>()
                        val itemList = ArrayList<Any>()
                        var o = 0

                        reciList.add(recipe.items_list[0])

                        for (i in 1 until recipe.items_list.size) {
                            if (i == o) continue

                            if (findItemByText(recipe.items_list[i + 1]) != null || i > 2) {
                                charList.add(recipe.items_list[i])
                                if (recipe.items_list[i + 1].contains("forge:"))
                                    itemList.add(recipe.items_list[i + 1])
                                else
                                    itemList.add(findItemByText(recipe.items_list[i + 1]) ?: Sandman.sandman().item)

                                o = i + 1
                            } else {
                                reciList.add(recipe.items_list[i])
                            }
                        }

                        if (charList.size != itemList.size) {
                            OVOMain.Logger.error("Shaped Crafting is Error!")
                            return
                        }

                        OVOMain.Logger.info(charList)
                        OVOMain.Logger.info(itemList)

                        RecipesHelper.addRecipe(reciList, charList, itemList, ItemStack(findItemByText(recipe.output)))
                    }

                    "crafting_shapeless" -> {
                        val itemList = ArrayList<Any>()

                        for (item in recipe.items_list)
                            if (item.contains("forge:"))
                                itemList.add(item)
                            else
                                itemList.add(findItemByText(item) ?: Sandman.sandman().item)

                        RecipesHelper.addShapelessRecipe(itemList, ItemStack(findItemByText(recipe.output)))
                    }

                    "smelting" -> {
                        GameRegistry.addSmelting(
                            findItemByText(recipe.items_list[0]) ?: Sandman.sandman().item,
                            ItemStack(findItemByText(recipe.output)),
                            0.5F
                        )
                    }
                }
            }
        }
    */

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