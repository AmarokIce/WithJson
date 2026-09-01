package club.someoneice.ovo.handler

import club.someoneice.json.node.JsonNode
import club.someoneice.json.node.MapNode
import club.someoneice.ovo.api.IObjectProcessor
import club.someoneice.ovo.asTypeOf
import club.someoneice.ovo.core.PacketInfo
import club.someoneice.ovo.createId
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.util.ResourceLocation

object BlockMaterialHandler: IObjectProcessor<Material> {
  override val nodeName: String = "block_material"

  override fun handle(
    info: PacketInfo,
    dat: MapNode
  ): Pair<ResourceLocation, Material> {
    val name = dat["name"]
      .asTypeNodeOrThrow(JsonNode.NodeType.String,
        "The name of the block material can't be used or null!").toString()

    val color = dat["color"] asTypeOf 0
    val translucent = dat["translucent"] asTypeOf false
    val requiresTool = dat["requires_tool"] asTypeOf false
    val canBurning = dat["can_burning"] asTypeOf false
    val replaceable = dat["replaceable"] asTypeOf false
    val adventureModeExempt = dat["adventure_mode_exempt"] asTypeOf false

    return (info createId name) to Material(MapColor.getMapColorForBlockColored(color)).apply {
      val clazz = Material::class.java

      fun doMethod(name: String) {
        clazz.declaredMethods.first{ it.name == name }.invoke(this)
      }

      if (translucent) {
        doMethod("setTranslucent")
      }

      if (requiresTool) {
        doMethod("setRequiresTool")
      }

      if (canBurning) {
        doMethod("setBurning")
      }

      if (replaceable) {
        this.setReplaceable()
      }

      if (adventureModeExempt) {
        doMethod("setAdventureModeExempt")
      }
    }
  }
}

