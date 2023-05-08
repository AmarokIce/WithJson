package club.someoneice.ovo.data

data class ItemWeapon(
    val name: String,
    val localization_name: String   = name,
    val texture_name: String        = name,
    val tool_meta: String           = "wood",
    val group: String               = "null"
)
