package club.someoneice.ovo.generator.old

data class Recipe(
    val type: String,
    val pattern: ArrayList<String>?,
    val key: HashMap<String, HashMap<String, String>>?,
    val ingredients: ArrayList<HashMap<String, String>>?,
    val result: HashMap<String, String>
)
