package club.someoneice.ovo.data

import club.someoneice.ovo.data.helper.Ingredient
import com.google.common.collect.Lists

data class BlockData (
    val name:               String,
    val localizationName:   String              = name,
    val textureName:        List<String>        = Lists.newArrayList(name),
    val hard:               Float               = 0.25f,
    val breakTool:          String              = "pickaxe",
    val sound:              String              = "minecraft:stone",
    val glowLevel:          Float               = 0.0f,
    val dropItem:           List<Ingredient>    = ArrayList(),
    val group:              String              = "",

    // 0: Simple, 1: all sides, 2: complex
    val rotationType:       Int                 = 0
) {

}