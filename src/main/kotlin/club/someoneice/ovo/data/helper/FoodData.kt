package club.someoneice.ovo.data.helper

data class FoodData (
    val hunger:            Int,
    val saturation:        Float,
    val wolf:              Boolean              = false,
    val alwaysEat:         Boolean              = false,
    val fastFood:          Boolean              = false,
    val isDrink:           Boolean              = false,
    val returnItem:        List<Ingredient>     = ArrayList(),
)
