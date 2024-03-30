package club.someoneice.ovo.generator.old

data class ItemData(
    val name: String,
    val localization_name: String   = name,
    val texture_name: String        = name,
    val max_size: Int               = 64,
    val group: String               = "null",
    val meta: Int                   = 0,
    val info: List<String>          = ArrayList()
)
