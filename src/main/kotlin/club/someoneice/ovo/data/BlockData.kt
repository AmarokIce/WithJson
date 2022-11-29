package club.someoneice.ovo.data

import club.someoneice.ovo.IDataGem

data class BlockData(
    val name: String,
    val hard: Float,
    val drop_item: String,
    val group: String
    ): IDataGem