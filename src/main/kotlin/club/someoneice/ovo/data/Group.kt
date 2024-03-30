package club.someoneice.ovo.data

import club.someoneice.ovo.data.helper.Ingredient

data class Group (
    val name: String,
    val icon: Ingredient = Ingredient("minecraft:apple")
)
