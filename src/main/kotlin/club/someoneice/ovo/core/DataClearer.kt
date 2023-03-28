package club.someoneice.ovo.core

import club.someoneice.ovo.core.obj.DataList

class DataClearer {
    init {
        DataList.dataItem.clear()
        DataList.dataBlock.clear()
        DataList.dataGroup.clear()
        DataList.dataItemFood.clear()
        DataList.dataItemTool.clear()
        DataList.dataItemWeapons.clear()

        DataList.dataRecipes.clear()
        DataList.dataDeleteRecipes.clear()

        DataList.dataBiomes.clear()

        // DataList.dataItemGift.clear()

    }
}