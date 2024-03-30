package club.someoneice.ovo.data.old


data class ItemToolData(
    val name: String,
    val localization_name: String = name,
    val texture_name: String      = name,
    val toolkit: String,
    val tool_meta: String         = "wood",
    val group: String             = "null"
)
