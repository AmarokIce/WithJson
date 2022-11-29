package club.someoneice.ovo.data

import club.someoneice.ovo.IDataGem

data class Recipes(
    val type: String,
    val count: Int,
    val output: String,
    val input: List<String>
): IDataGem