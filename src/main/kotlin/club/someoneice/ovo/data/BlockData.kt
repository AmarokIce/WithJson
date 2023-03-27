package club.someoneice.ovo.data

data class BlockData(
    val name: String,
    val localization_name: String,
    val texture_name: String,
    val hard: Int,
    val hard_level: Int,
    val break_tool: String,
    val is_glow: Boolean,
    val drop_item: String,
    val group: String
    )