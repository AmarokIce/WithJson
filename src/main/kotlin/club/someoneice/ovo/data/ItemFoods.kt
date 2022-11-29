package club.someoneice.ovo.data

import club.someoneice.ovo.IDataGem

data class ItemFoods(
    val name: String,
    val max_size: Int,
    val hunger: Int,
    val saturation: Float,
    val wolf: Boolean,
    val always_eat: Boolean,
    val fast_food: Boolean,
    val isDrink: Boolean,
    val return_item: String,
    val group: String
): IDataGem
