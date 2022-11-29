package club.someoneice.ovo.data

import club.someoneice.ovo.IDataGem

data class ItemKnife(
    val name: String,
    val attackDamage: Int,
    val tool_meta: String,
    val group: String
): IDataGem
