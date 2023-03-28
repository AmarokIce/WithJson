package club.someoneice.ovo.core.obj

import club.someoneice.ovo.data.*
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

object DataList {
    var getGroup = HashMap<String, CreativeTabs>()

    var dataItem = ArrayList<ItemData>()
    var dataItemGift = ArrayList<ItemGift>()
    var dataItemFood = ArrayList<ItemFoods>()
    var dataItemTool = ArrayList<ItemTool>()
    var dataItemWeapons = ArrayList<ItemKnife>()

    var dataBlock = ArrayList<BlockData>()

    var dataGroup = ArrayList<Group>()

    var dataRecipes = ArrayList<Recipe>()
    var dataDeleteRecipes = ArrayList<Item>()

    var dataBiomes = ArrayList<BiomesData>()
}