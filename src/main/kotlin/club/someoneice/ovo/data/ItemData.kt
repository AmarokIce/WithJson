package club.someoneice.ovo.data

import club.someoneice.ovo.data.helper.FoodData

data class ItemData (
    val name:               String,
    val localizationName:   String          = name,
    val textureName:        String          = name,
    val maxSize:            Int             = 64,
    val group:              String          = "null",
    val meta:               Int             = 0,
    val info:               List<String>    = ArrayList(),

    val foodData:           FoodData?       = null
)
