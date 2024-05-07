package club.someoneice.ovo.data

import club.someoneice.ovo.data.helper.Ingredient
import com.google.common.collect.Lists
import cpw.mods.fml.common.registry.GameRegistry

data class Recipe(
    val type:               String,
    val pattern:            ArrayList<String>?,
    val key:                HashMap<String, Ingredient>?,
    val ingredients:        ArrayList<Ingredient>?,
    val result:             Ingredient
) {
    fun addRecipe() {
        when (type.lowercase()) {
            "minecraft:shaped_recipe" -> {
                val list: ArrayList<Any> = Lists.newArrayList(pattern ?: return)
                (key ?: return).forEach { (key, value) ->
                    list.add(key[0])
                    list.add(value.toItemStack() ?: return)
                }

                GameRegistry.addShapedRecipe(result.toItemStack(), list.toArray())
            }

            "minecraft:shapeless_recipe" -> {
                GameRegistry.addShapelessRecipe(result.toItemStack(), (ingredients ?: return)
                    .map { it.toItemStack() ?: return })
            }

            "minecraft:smelting" -> {
                GameRegistry.addSmelting((ingredients ?: return)[0].toItemStack() ?: return, result.toItemStack(), 0.5f)
            }
        }
    }
}
