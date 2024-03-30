package club.someoneice.ovo.data

data class ItemToolData(
    val name:               String,
    val localizationName:   String      = name,
    val textureName:        String      = name,
    val toolkit:            String      = "pickaxe",
    val toolMeta:           String      = "wood",
    val group:              String      = ""
)
