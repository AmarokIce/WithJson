package club.someoneice.ovo.data

import club.someoneice.ovo.data.helper.Ingredient

data class Recipe(
    val type:               String,
    val pattern:            ArrayList<String>?,
    val key:                HashMap<String, Ingredient>?,
    val ingredients:        ArrayList<Ingredient>?,
    val result:             Ingredient
) {
    fun addRecipe() {

    }
}
