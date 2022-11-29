package club.someoneice.ovo.data

import club.someoneice.ovo.IDataGem
import club.someoneice.ovo.data.helper.ItemGiftHelper
import java.util.*

data class ItemGift(
    val name: String,
    val max_size: Int,
    val items: ArrayList<ItemGiftHelper>,
    val group: String
): IDataGem
