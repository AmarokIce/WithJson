package club.someoneice.ovo.data

import club.someoneice.ovo.IDataGem

data class BlockData(
    val name: String,
    val hard: Float       = 0.1F,
    val drop_item: String = "null",
    val group: String     = "null"
    ): IDataGem