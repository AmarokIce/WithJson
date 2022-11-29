package club.someoneice.ovo.data

import club.someoneice.ovo.IDataGem

data class ItemData(
    val name: String,
    val max_size: Int,
    val group: String
): IDataGem
