package club.someoneice.ovo.data

data class ItemFoods(
    val name: String,
    val localization_name: String,
    val texture_name: String,
    val max_size: Int,
    val hunger: Int,
    val saturation: Float,
    val wolf: Boolean,
    val always_eat: Boolean,
    val fast_food: Boolean,
    val isDrink: Boolean,
    val return_item: String,
    val group: String
)
