package club.someoneice.ovo.data

import club.someoneice.ovo.IDataGem

data class ItemWeapon(
    val name: String,
    val attackDamage: Int       = 0,
    val tool_meta: String       = "wood",
    val group: String           = "null"
): IDataGem
