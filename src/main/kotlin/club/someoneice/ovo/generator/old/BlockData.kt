package club.someoneice.ovo.generator.old

data class BlockData(
    val name: String,
    val localization_name: String   = name,
    val texture_name: String        = name,
    val hard: Int                   = 1,
    val hard_level: Int             = 0,
    val break_tool: String          = "",
    val is_glow: Boolean            = false,
    val drop_item: String           = name,
    val group: String               = "null"
)