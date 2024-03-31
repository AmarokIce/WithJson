package club.someoneice.ovo.util

import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraft.item.crafting.IRecipe

object RemoveRecipes {
    fun removeAllRecipe(item: ItemStack) {
        this.removeItemRecipes(item)
        this.removeFurnaceRecipes(item)
    }

    @SuppressWarnings("unchecked")
    fun removeItemRecipes(item: ItemStack) {
        val recipes: ArrayList<IRecipe?> = CraftingManager.getInstance().recipeList as ArrayList<IRecipe?>
        var i = 0
        while (i < recipes.size) {
            val tmpRecipe = recipes[i]
            val recipeResult = tmpRecipe!!.recipeOutput
            if (ItemStack.areItemStacksEqual(item, recipeResult)) recipes.removeAt(i--)
            i++
        }
    }

    @SuppressWarnings("unchecked")
    fun removeFurnaceRecipes(item: ItemStack) {
        val recipes: HashMap<ItemStack, ItemStack> = FurnaceRecipes.smelting().smeltingList as HashMap<ItemStack, ItemStack>
        val iterator: Iterator<*> = recipes.entries.iterator()
        var entry: Map.Entry<*, *>
        do {
            if (!iterator.hasNext()) return
            entry = iterator.next() as Map.Entry<*, *>
        } while (!getID(item, entry.key as ItemStack))
        recipes.remove(entry.key as ItemStack)
    }

    private fun getID(item: ItemStack, itemStack: ItemStack): Boolean {
        return itemStack.item === item.item && (itemStack.itemSpriteNumber == 32767 || itemStack.itemSpriteNumber == item.itemSpriteNumber)
    }
}