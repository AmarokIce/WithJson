package club.someoneice.ovo.mana

import club.someoneice.ovo.util.*
import net.minecraft.item.ItemStack

class MMMProcessor {
    init {
        recipesRemover()
        dataSetter()
        addRecipes()
    }

    private fun recipesRemover() {
        val Injection = ArrayList<ItemStack>()
        val ManaCraft = ArrayList<ItemStack>()
        val MetalCraft = ArrayList<ItemStack>()

        for (data in MMMDataList.dataRemove) {
            when (data.type) {
                "manametalmod:ManaMetalInjection"   -> Injection.add((Util.findItemByText(data.item) ?: continue).itemStack())
                "manametalmod:ManaCraftTable"       -> ManaCraft.add((Util.findItemByText(data.item) ?: continue).itemStack())
                "manametalmod:MetalCraftTable"      -> MetalCraft.add((Util.findItemByText(data.item) ?: continue).itemStack())
            }
        }

        if (Injection.isNotEmpty())     OVOMMMAPI.deleteRecipesInManaMetalInjection(Injection.toList())
        if (ManaCraft.isNotEmpty())     OVOMMMAPI.deleteRecipesInManaCraftTable(ManaCraft.toList())
        if (MetalCraft.isNotEmpty())    OVOMMMAPI.deleteRecipesInMetalCraftTable(MetalCraft.toList())
    }

    private fun dataSetter() {
        for (data in MMMDataList.dataItemValue)  OVOMMMAPI.setItemValue((Util.findItemByText(data.item) ?: continue).itemStack(), data.value)
        for (data in MMMDataList.dataGoldItem)   OVOMMMAPI.addGoldItem((Util.findItemByText(data.item) ?: continue).itemStack(), data.value)
        for (data in MMMDataList.dataFrozenItem) OVOMMMAPI.addFrozenItem((Util.findItemByText(data.item) ?: continue).itemStack(), data.value)
        for (data in MMMDataList.dataDungeon)    OVOMMMAPI.addDungeonItem((Util.findItemByText(data) ?: continue).itemStack())
    }

    private fun addRecipes() {
        for (data in MMMDataList.dataRecipes) {
            when(data.type) {
                "manametalmod:ManaMetalInjection"   -> {
                    val list = ArrayList<ItemStack>()
                    for (item in data.items) list.add((Util.findItemByText(item) ?: continue).itemStack())
                    if (list.size < 9) continue
                    OVOMMMAPI.addManaMetalInjectionRecipeList(list, ItemStack((Util.findItemByText(data.output) ?: continue), data.count))
                }
                "manametalmod:ManaCraftTable"       -> {
                    val list = ArrayList<ItemStack>()
                    for (item in data.items) list.add((Util.findItemByText(item) ?: continue).itemStack())
                    if (list.size < 9) continue
                    OVOMMMAPI.addManaCraftTableRecipes(list, ItemStack((Util.findItemByText(data.output) ?: continue), data.count))
                }
                "manametalmod:MetalCraftTable"      -> {
                    val list = ArrayList<ItemStack>()
                    for (item in data.items) list.add((Util.findItemByText(item) ?: continue).itemStack())
                    if (list.size < 9) continue
                    OVOMMMAPI.addMetalCraftTableRecipes(list, ItemStack((Util.findItemByText(data.output) ?: continue), data.count))
                }
                "manametalmod:Baking"               -> {
                    val list = ArrayList<ItemStack>()
                    for (item in data.items) list.add((Util.findItemByText(item) ?: continue).itemStack())
                    if (list.size < 2) continue
                    OVOMMMAPI.addBakerRecipe(list[0], list[1], ItemStack((Util.findItemByText(data.output) ?: continue), data.count))
                }
                "manametalmod:Juicer"               -> {
                    val list = ArrayList<ItemStack>()
                    for (item in data.items) list.add((Util.findItemByText(item) ?: continue).itemStack())
                    if (list.size < 2) continue
                    OVOMMMAPI.addJuicerRecipe(list[0], list[1], ItemStack((Util.findItemByText(data.output) ?: continue), data.count))
                }
                "manametalmod:Grinding"             -> {
                    val list = ArrayList<ItemStack>()
                    for (item in data.items) list.add((Util.findItemByText(item) ?: continue).itemStack())
                    if (list.size < 2) continue
                    OVOMMMAPI.addGrindingRecipe(list[0], list[1], ItemStack((Util.findItemByText(data.output) ?: continue), data.count))
                }
                "manametalmod:Fermentation"         -> {
                    val list = ArrayList<ItemStack>()
                    for (item in data.items) list.add((Util.findItemByText(item) ?: continue).itemStack())
                    if (list.size < 2) continue
                    OVOMMMAPI.addFermentationRecipe(list[0], list[1], ItemStack((Util.findItemByText(data.output) ?: continue), data.count))
                }
                "manametalmod:Bartender"            -> {
                    val list = ArrayList<ItemStack>()
                    for (item in data.items) list.add((Util.findItemByText(item) ?: continue).itemStack())
                    if (list.size < 2) continue
                    OVOMMMAPI.addBartenderRecipe(list[0], list[1], ItemStack((Util.findItemByText(data.output) ?: continue), data.count))
                }
            }
        }
    }
}