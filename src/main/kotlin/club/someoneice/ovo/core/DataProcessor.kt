package club.someoneice.ovo.core

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
                    val a0: Item? = if (recipe.items_list.size > 3)    findItemByText(recipe.items_list[3])     ?: Sandman.sandman().item else Sandman.sandman().item
                    val a1: Item? = if (recipe.items_list.size > 4)    findItemByText(recipe.items_list[4])     ?: Sandman.sandman().item else Sandman.sandman().item
                    val a2: Item? = if (recipe.items_list.size > 5)    findItemByText(recipe.items_list[5])     ?: Sandman.sandman().item else Sandman.sandman().item
                    val a3: Item? = if (recipe.items_list.size > 6)    findItemByText(recipe.items_list[6])     ?: Sandman.sandman().item else Sandman.sandman().item
                    val a4: Item? = if (recipe.items_list.size > 7)    findItemByText(recipe.items_list[7])     ?: Sandman.sandman().item else Sandman.sandman().item
                    val a5: Item? = if (recipe.items_list.size > 8)    findItemByText(recipe.items_list[8])     ?: Sandman.sandman().item else Sandman.sandman().item
                    val a6: Item? = if (recipe.items_list.size > 9)    findItemByText(recipe.items_list[9])   ?: Sandman.sandman().item else Sandman.sandman().item
                    val a7: Item? = if (recipe.items_list.size > 10)   findItemByText(recipe.items_list[10])   ?: Sandman.sandman().item else Sandman.sandman().item
                    val a8: Item? = if (recipe.items_list.size > 11)   findItemByText(recipe.items_list[11])   ?: Sandman.sandman().item else Sandman.sandman().item

                    GameRegistry.addShapedRecipe(
                        ItemStack(findItemByText(recipe.output)),
                        recipe.items_list[0], recipe.items_list[1], recipe.items_list[2],
                        'A', a0,
                        'B', a1,
                        'C', a2,
                        'D', a3,
                        'E', a4,
                        'F', a5,
                        'G', a6,
                        'H', a7,
                        'I', a8
                    )
                }

                "crafting_shapeless" -> {
                    // val list = ArrayList<Item>()
                    // for (i in recipe.items_list)
                    //     list.add(findItemByText(i) ?: Sandman.sandman().item)

                    val a0: Item? = if (recipe.items_list.size >= 1) findItemByText(recipe.items_list[0]) ?: Sandman.sandman().item else Sandman.missingNo() as Item?
                    val a1: Item? = if (recipe.items_list.size >= 2) findItemByText(recipe.items_list[1]) ?: Sandman.sandman().item else Sandman.missingNo() as Item?
                    val a2: Item? = if (recipe.items_list.size >= 3) findItemByText(recipe.items_list[2]) ?: Sandman.sandman().item else Sandman.missingNo() as Item?
                    val a3: Item? = if (recipe.items_list.size >= 4) findItemByText(recipe.items_list[3]) ?: Sandman.sandman().item else Sandman.missingNo() as Item?
                    val a4: Item? = if (recipe.items_list.size >= 5) findItemByText(recipe.items_list[4]) ?: Sandman.sandman().item else Sandman.missingNo() as Item?
                    val a5: Item? = if (recipe.items_list.size >= 6) findItemByText(recipe.items_list[5]) ?: Sandman.sandman().item else Sandman.missingNo() as Item?
                    val a6: Item? = if (recipe.items_list.size >= 7) findItemByText(recipe.items_list[6]) ?: Sandman.sandman().item else Sandman.missingNo() as Item?
                    val a7: Item? = if (recipe.items_list.size >= 8) findItemByText(recipe.items_list[7]) ?: Sandman.sandman().item else Sandman.missingNo() as Item?
                    val a8: Item? = if (recipe.items_list.size >= 9) findItemByText(recipe.items_list[8]) ?: Sandman.sandman().item else Sandman.missingNo() as Item?

                    if (recipe.items_list.size <= 1) GameRegistry.addShapelessRecipe(ItemStack(findItemByText(recipe.output)), a0)
                    else if (recipe.items_list.size <= 2) GameRegistry.addShapelessRecipe(ItemStack(findItemByText(recipe.output)), a0, a1)
                    else if (recipe.items_list.size <= 3) GameRegistry.addShapelessRecipe(ItemStack(findItemByText(recipe.output)), a0, a1, a2)
                    else if (recipe.items_list.size <= 4) GameRegistry.addShapelessRecipe(ItemStack(findItemByText(recipe.output)), a0, a1, a2, a3)
                    else if (recipe.items_list.size <= 5) GameRegistry.addShapelessRecipe(ItemStack(findItemByText(recipe.output)), a0, a1, a2, a3, a4)
                    else if (recipe.items_list.size <= 6) GameRegistry.addShapelessRecipe(ItemStack(findItemByText(recipe.output)), a0, a1, a2, a3, a4, a5)
                    else if (recipe.items_list.size <= 7) GameRegistry.addShapelessRecipe(ItemStack(findItemByText(recipe.output)), a0, a1, a2, a3, a4, a5, a6)
                    else if (recipe.items_list.size <= 8) GameRegistry.addShapelessRecipe(ItemStack(findItemByText(recipe.output)), a0, a1, a2, a3, a4, a5, a6, a7)
                    else if (recipe.items_list.size <= 9) GameRegistry.addShapelessRecipe(ItemStack(findItemByText(recipe.output)), a0, a1, a2, a3, a4, a5, a6, a7, a8)
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