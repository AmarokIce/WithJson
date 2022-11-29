package club.someoneice.ovo.core

import club.someoneice.ovo.data.*
import net.minecraft.item.ItemGroup
import java.util.*

object DataList {
    var getGroup: HashMap<String, ItemGroup> = HashMap<String, ItemGroup>()

    var dataItem = ArrayList<ItemData>()
    var dataItemGift = ArrayList<ItemGift>()
    var dataItemFood = ArrayList<ItemFoods>()
    var dataItemTool = ArrayList<ItemTool>()
    var dataItemWeapons = ArrayList<ItemKnife>()

    var dataBlock = ArrayList<BlockData>()

    var dataGroup = ArrayList<Group>()

    var dataRecipe = ArrayList<Recipes>()
}