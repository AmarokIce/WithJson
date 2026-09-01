package club.someoneice.ovo.core

data class PacketInfo(
  val id: String,
  val version: String = "v0.0.0",
  val dependence: List<String>,
  val after: List<String>
)
