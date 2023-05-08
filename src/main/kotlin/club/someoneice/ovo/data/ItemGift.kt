package club.someoneice.ovo.data

import club.someoneice.ovo.IDataGem
import club.someoneice.ovo.data.helper.ItemGiftHelper

data class ItemGift(
    val name: String,
    val max_size: Int                       = 64,
    val items: ArrayList<ItemGiftHelper>,
    val group: String                       = "null"
): IDataGem
