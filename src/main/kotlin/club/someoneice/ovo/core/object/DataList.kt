package club.someoneice.ovo.core.`object`

import club.someoneice.ovo.data.*
import com.google.common.collect.Lists
import com.google.common.collect.Maps
import net.minecraft.creativetab.CreativeTabs

object DataList {
    // Retain this map for other package to use.
    val getGroup          :HashMap<String, CreativeTabs> = Maps.newHashMap()

    val dataItem          :ArrayList<ItemData>           = Lists.newArrayList()
    val dataItemGift      :ArrayList<ItemGift>           = Lists.newArrayList()
    val dataItemFood      :ArrayList<ItemFood>           = Lists.newArrayList()
    val dataItemTool      :ArrayList<ItemTool>           = Lists.newArrayList()
    val dataItemWeapons   :ArrayList<ItemWeapon>         = Lists.newArrayList()
    val dataBlock         :ArrayList<BlockData>          = Lists.newArrayList()

    val dataBiomes        :ArrayList<BiomesData>         = Lists.newArrayList()
    val dataGroup         :ArrayList<Group>              = Lists.newArrayList()
    val dataRecipes       :ArrayList<Recipe>             = Lists.newArrayList()
    val dataDeleteRecipes :ArrayList<String>             = Lists.newArrayList()

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