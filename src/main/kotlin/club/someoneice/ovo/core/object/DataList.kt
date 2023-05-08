package club.someoneice.ovo.core.`object`

import club.someoneice.ovo.data.*
import com.google.common.collect.Lists
import com.google.common.collect.Maps
import net.minecraft.creativetab.CreativeTabs

object DataList {
    // Retain this map for other package to use.
    var getGroup          :HashMap<String, CreativeTabs> = Maps.newHashMap()

    var dataItem          :ArrayList<ItemData>           = Lists.newArrayList()
    var dataItemGift      :ArrayList<ItemGift>           = Lists.newArrayList()
    var dataItemFood      :ArrayList<ItemFood>           = Lists.newArrayList()
    var dataItemTool      :ArrayList<ItemTool>           = Lists.newArrayList()
    var dataItemWeapons   :ArrayList<ItemWeapon>         = Lists.newArrayList()
    var dataBlock         :ArrayList<BlockData>          = Lists.newArrayList()

    var dataBiomes        :ArrayList<BiomesData>         = Lists.newArrayList()
    var dataGroup         :ArrayList<Group>              = Lists.newArrayList()
    var dataRecipes       :ArrayList<Recipe>             = Lists.newArrayList()
    var dataDeleteRecipes :ArrayList<String>             = Lists.newArrayList()

    // Clean all of the data list.
    fun init() {
        dataItem.clear()
        dataBlock.clear()
        dataGroup.clear()
        dataItemFood.clear()
        dataItemTool.clear()
        dataItemWeapons.clear()
        dataItemGift.clear()

        dataRecipes.clear()
        dataDeleteRecipes.clear()

        dataBiomes.clear()
    }
}