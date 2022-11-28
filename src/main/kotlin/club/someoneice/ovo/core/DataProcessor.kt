package club.someoneice.ovo.core

import club.someoneice.ovo.RecipesHelper
import club.someoneice.ovo.data.Group
import club.someoneice.ovo.json.Sandman
import club.someoneice.ovo.util.*
import club.someoneice.ovo.util.Util.findItemByText
import com.google.common.annotations.Beta
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import java.util.*

class DataProcessor(private val mod_id: String?) {

    init {
        OVOMain.Logger.info("Now DataProcessor is running!")
    }

    fun init() {
        infoProcessing()            // Init the json pockage.
        groupProcessing()           // Add creativeTabs. And put them in groupMap.
        itemDataProcessing()        // Add and registry the Items.
        blockDataProcessing()       // Add and registry the Blocks and BlockItems.
    }

    private fun infoProcessing() {
        Info.modid = this.mod_id ?: OVOMain.modid

        OVOMain.Logger.info("Get modid: $mod_id !")
    }

    private fun groupProcessing() {
        OVOMain.Logger.info("Now create the new group!")

        for (group in DataList.dataGroup) {
            try {
                DataList.getGroup.put(group.name, CreateGroup(group))
                OVOMain.Logger.info("Create group: ${group.name} !")
            } catch (_: Exception) {
                OVOMain.Logger.error("Cannot create the new group!")
            }
        }
    }

    private fun CreateGroup(group: Group): CreativeTabs {
        return object : CreativeTabs(group.name) {
            override fun getTabIconItem(): Item {
                return findItemByText(group.icon) ?: Items.apple
            }
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
                "wood" -> Item.ToolMaterial.WOOD
                "stone" -> Item.ToolMaterial.STONE
                "iron" -> Item.ToolMaterial.WOOD
                "gold" -> Item.ToolMaterial.GOLD
                "diamond" -> Item.ToolMaterial.EMERALD

                else -> Item.ToolMaterial.WOOD
            }
            ItemWeapons(weapons, mate)
        }

        OVOMain.Logger.info("Now create new recipes!")

        @Beta
        for (recipe in DataList.dataRecipes) { // TODO - It so... I don't know how to description these shit code.
            when (recipe.recipe) {

                "crafting_shaped" -> {
                    val ReciList = ArrayList<String>()
                    val CharList = ArrayList<String>()
                    val ItemList = ArrayList<Item>()
                    var o = 0

                    if (recipe.items_list[0] != null) {
                        ReciList.add(recipe.items_list[0])

                        for (i in 1..recipe.items_list.size - 1) {
                            if (i == o) continue

                            if (findItemByText(recipe.items_list[i + 1]) != null || i > 2) {
                                CharList.add(recipe.items_list[i])
                                ItemList.add(findItemByText(recipe.items_list[i + 1]) ?: Sandman.sandman().item)
                                o = i + 1
                            } else {
                                ReciList.add(recipe.items_list[i])
                            }
                        }

                        if (CharList.size != ItemList.size) {
                            OVOMain.Logger.error("Shaped Crafting is Error!")
                            return
                        }
                    }

                    OVOMain.Logger.info(CharList)
                    OVOMain.Logger.info(ItemList)

                    RecipesHelper.addRecipe(ReciList, CharList, ItemList, ItemStack(findItemByText(recipe.output)))
                }

                "crafting_shapeless" -> {
                    val ItemList = ArrayList<Item>()

                    for (i in recipe.items_list) {
                        ItemList.add(findItemByText(i) ?: Sandman.sandman().item)
                    }

                    RecipesHelper.addShapelessRecipe(ItemList, ItemStack(findItemByText(recipe.output)))
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

    private fun blockDataProcessing() {
        if (DataList.dataBlock.size > 0) {
            for (block in DataList.dataBlock) {
                BlockBase(Material.rock, block)
            }
        }
    }
}