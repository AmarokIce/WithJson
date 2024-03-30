package club.someoneice.ovov1.mana

import club.someoneice.ovov1.mana.data.MMMCraftTable
import club.someoneice.ovov1.mana.data.MMMMapData
import club.someoneice.ovov1.mana.data.MMMRemoveRecipes

object MMMDataList {
    val dataRemove      = ArrayList<MMMRemoveRecipes>()
    val dataGoldItem    = ArrayList<MMMMapData>()
    val dataFrozenItem  = ArrayList<MMMMapData>()
    val dataItemValue   = ArrayList<MMMMapData>()
    val dataDungeon     = ArrayList<String>()
    val dataRecipes     = ArrayList<MMMCraftTable>()

    fun clean() {
        dataRemove.clear()
        dataGoldItem.clear()
        dataFrozenItem.clear()
        dataItemValue.clear()
        dataDungeon.clear()
        dataRecipes.clear()
    }
}