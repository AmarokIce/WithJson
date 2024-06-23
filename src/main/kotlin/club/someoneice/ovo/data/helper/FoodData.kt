package club.someoneice.ovo.data.helper

data class FoodData (
    val hunger:            Int,
    val saturation:        Float,
    val wolf:              Boolean                  = false,
    val alwaysEat:         Boolean                  = false,
    val speed:             Int                      = 32,
    val isDrink:           Boolean                  = false,
    val returnItem:         ArrayList<Ingredient>   = ArrayList(),
)
