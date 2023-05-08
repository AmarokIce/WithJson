package club.someoneice.ovo.core.`object`

import club.someoneice.ovo.data.*
import net.minecraft.item.ItemGroup

object DataList {
    var getGroup: HashMap<String, ItemGroup> = HashMap<String, ItemGroup>()

    var dataItem = ArrayList<ItemData>()
    var dataItemGift = ArrayList<ItemGift>()
    var dataItemFood = ArrayList<ItemFood>()
    var dataItemTool = ArrayList<ItemTool>()
    var dataItemWeapons = ArrayList<ItemWeapon>()

    var dataBlock = ArrayList<BlockData>()

    var dataGroup = ArrayList<Group>()

    var dataRecipe = ArrayList<Recipes>()

    fun init() {
        dataItem.clear()
        dataBlock.clear()
        dataGroup.clear()
        dataItemFood.clear()
        dataItemTool.clear()
        dataItemWeapons.clear()
        dataItemGift.clear()

        dataRecipe.clear()
    }
}