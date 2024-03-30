package club.someoneice.ovo.data

data class BiomesData (
    val id:             Int,
    val name:           String,
    val weight:         Int,
    val weatherType:    String,
    val biomesCag:      String,
    val flower:         Boolean         = false,
    val color:          Int,
    val grassColor:     Int,
    val rain:           Boolean         = true,
    val waterColor:     Int
)
