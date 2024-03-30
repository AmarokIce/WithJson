package club.someoneice.ovo.data.old

data class ItemFood(
    val name: String,
    val localization_name: String   = name,
    val texture_name: String        = name,
    val max_size: Int               = 64,
    val hunger: Int,
    val saturation: Float,
    val wolf: Boolean               = false,
    val always_eat: Boolean         = false,
    val fast_food: Boolean          = false,
    val isDrink: Boolean            = false,
    val return_item: String         = "null",
    val group: String               = "null"
)
