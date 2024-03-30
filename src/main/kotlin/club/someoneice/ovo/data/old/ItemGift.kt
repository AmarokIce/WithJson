package club.someoneice.ovo.data.old

import club.someoneice.ovo.data.helper.ItemGiftHelper

data class ItemGift(
    val name: String,
    val localization_name: String           = name,
    val texture_name: String                = name,
    val max_size: Int                       = 64,
    val items: ArrayList<ItemGiftHelper>,
    val group: String                       = "null"
)
