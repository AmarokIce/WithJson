package club.someoneice.ovo.mana

import club.someoneice.ovo.util.Util
import club.someoneice.ovo.util.itemStack
import club.someoneice.ovo.util.setSize
import com.google.common.collect.Lists
import net.minecraft.item.ItemStack

class MMMProcessor {
    init {
        recipesRemover()
        dataSetter()
        addRecipes()
    }

    private fun recipesRemover() {
        for (data in MMMDataList.dataRemove) when (data.type) {
            "manametalmod:ManaMetalInjection"   -> OVOMMMAPI.deleteRecipesInManaMetalInjection((Util.findItemByText(data.item) ?: continue))
            "manametalmod:ManaCraftTable"       -> OVOMMMAPI.deleteRecipesInManaCraftTable((Util.findItemByText(data.item) ?: continue))
            "manametalmod:MetalCraftTable"      -> OVOMMMAPI.deleteRecipesInMetalCraftTable((Util.findItemByText(data.item) ?: continue))
        }
    }

    private fun dataSetter() {
        for (data in MMMDataList.dataItemValue)  OVOMMMAPI.setItemValue((Util.findItemByText(data.item)  ?: continue).itemStack(), data.value)
        for (data in MMMDataList.dataGoldItem)   OVOMMMAPI.addGoldItem((Util.findItemByText(data.item)   ?: continue).itemStack(), data.value)
        for (data in MMMDataList.dataFrozenItem) OVOMMMAPI.addFrozenItem((Util.findItemByText(data.item) ?: continue).itemStack(), data.value)
        for (data in MMMDataList.dataDungeon)    OVOMMMAPI.addDungeonItem((Util.findItemByText(data)     ?: continue).itemStack())
    }

    private fun addRecipes() {
        for (data in MMMDataList.dataRecipes) {
            val output = (Util.findItemByText(data.output) ?: continue).itemStack().setSize(data.count)
            val list: ArrayList<ItemStack> = Lists.newArrayList()
            for (item in data.items) list.add((Util.findItemByText(item) ?: continue).itemStack())

            when(data.type) {
                "manametalmod:ManaMetalInjection"   -> {
                    if (list.size < 9) continue
                    OVOMMMAPI.addManaMetalInjectionRecipeList(list, output)
                }
                "manametalmod:ManaCraftTable"       -> {
                    if (list.size < 9) continue
                    OVOMMMAPI.addManaCraftTableRecipes(list, output)
                }
                "manametalmod:MetalCraftTable"      -> {
                    if (list.size < 9) continue
                    OVOMMMAPI.addMetalCraftTableRecipes(list, output)
                }
                "manametalmod:Baking"               -> {
                    if (list.size < 2) continue
                    OVOMMMAPI.addBakerRecipe(list[0], list[1], output)
                }
                "manametalmod:Juicer"               -> {
                    if (list.size < 2) continue
                    OVOMMMAPI.addJuicerRecipe(list[0], list[1], output)
                }
                "manametalmod:Grinding"             -> {
                    if (list.size < 2) continue
                    OVOMMMAPI.addGrindingRecipe(list[0], list[1], output)
                }
                "manametalmod:Fermentation"         -> {
                    if (list.size < 2) continue
                    OVOMMMAPI.addFermentationRecipe(list[0], list[1], output)
                }
                "manametalmod:Bartender"            -> {
                    if (list.size < 2) continue
                    OVOMMMAPI.addBartenderRecipe(list[0], list[1], output)
                }
            }
        }
    }
}