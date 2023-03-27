package club.someoneice.ovo.mana

import club.someoneice.ovo.util.ore
import net.minecraft.item.ItemStack
import project.studio.manametalmod.Lapuda.IGoldItem
import project.studio.manametalmod.ManaMetalAPI.*
import project.studio.manametalmod.core.RecipeOre
import project.studio.manametalmod.magic.magicItem.IMagicEffect
import project.studio.manametalmod.magic.magicItem.MagicItemType

object OVOMMMAPI {
    fun addGoldItem(item: ItemStack, count: Int) {
        GoldItemList.add(IGoldItem(item, count))
    }

    fun addFrozenItem(item: ItemStack, value: Int) {
        addFrozenItem(item, value)
    }

    fun setItemValue(item: ItemStack, money: Int) {
        setItemValue(item, money)
    }

    fun addDungeonItem(item: ItemStack) {
        DungeonItemsLV2.add(item)
        DungeonItems.add(item)
    }

    fun addHotSoupBaseFood(item: ItemStack, type: MagicItemType, value: Float) {
        HotPotFoodList[item.item] = IMagicEffect(type, value)
    }


    /* Add Recipes */

    fun addManaMetalInjectionRecipeList(list: ArrayList<ItemStack>, output: ItemStack) {
        ManaMetalInjectionRecipeList.add(arrayOf(list[0], list[1], list[2], list[3], list[4], list[5], list[6], list[7], list[8], output))
    }

    fun addManaCraftTableRecipes(list: ArrayList<ItemStack>, output: ItemStack) {
        addManaCraftTableRecipes(list[0], list[1], list[2], list[3], list[4], list[5], list[6], list[7], list[8], output)
    }

    fun addMetalCraftTableRecipes(list: ArrayList<ItemStack>, output: ItemStack) {
        addMetalCraftTableRecipes(list[0], list[1], list[2], list[3], list[4], list[5], list[6], list[7], list[8], output)
    }

    fun addGrindingRecipe(input1: ItemStack, input2: ItemStack, output: ItemStack) {
        FoodGrindingRecipeList.add(RecipeOre(input1.ore(), input2.ore(), output))
    }

    fun addJuicerRecipe(input1: ItemStack, input2: ItemStack, output: ItemStack) {
        FoodJuiceRecipeList.add(RecipeOre(input1.ore(), input2.ore(), output))
    }

    fun addBakerRecipe(input1: ItemStack, input2: ItemStack, output: ItemStack) {
        FoodBakingRecipeList.add(RecipeOre(input1.ore(), input2.ore(), output))
    }

    fun addFermentationRecipe(input1: ItemStack, input2: ItemStack, output: ItemStack) {
        FoodFermentationRecipeList.add(RecipeOre(input1.ore(), input2.ore(), output))
    }

    fun addBartenderRecipe(input: ItemStack, cap: ItemStack, output: ItemStack) {
        FoodBartendingRecipeList.add(RecipeOre(input.ore(), cap.ore(), output))
    }


    /* Delete Recipes */

    fun deleteRecipesInManaMetalInjection(list: List<ItemStack>) {
        var count = 0
        for (i in 0 until ManaMetalInjectionRecipeList.size) {
            if (count >= list.size) return
            if (list.contains(ManaMetalInjectionRecipeList[i][10])) {
                ManaMetalInjectionRecipeList.removeAt(i)
                count += 1
            }
        }
    }
    fun deleteRecipesInManaCraftTable(list: List<ItemStack>) {
        var count = 0
        for (i in 0 until ManaCraftTableRecipes.size) {
            if (count >= list.size) return
            if (list.contains(ManaCraftTableRecipes[i].items[10])) {
                ManaCraftTableRecipes.removeAt(i)
                count += 1
            }
        }
    }

    fun deleteRecipesInMetalCraftTable(list: List<ItemStack>) {
        var count = 0
        for (i in 0 until MetalCraftTableRecipes.size) {
            if (count >= list.size) return
            if (list.contains(MetalCraftTableRecipes[i].items[10])) {
                MetalCraftTableRecipes.removeAt(i)
                count += 1
            }
        }
    }
}