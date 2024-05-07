package club.someoneice.ovo.data

import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.world.biome.BiomeGenBase
import net.minecraftforge.common.BiomeDictionary
import net.minecraftforge.common.BiomeManager

data class BiomesData (
    val id:             Int,
    val name:           String,
    val weight:         Int,
    val weatherType:    String,
    val biomesTag:      String,
    val flower:         Boolean         = false,
    val color:          Int,
    val grassColor:     Int,
    val rain:           Boolean         = true,
    val waterColor:     Int
) {
    fun register() {
        object: BiomeGenBase(this@BiomesData.id) {
            init {
                this.setBiomeName(this@BiomesData.name)
                this.theBiomeDecorator.treesPerChunk = 10
                this.theBiomeDecorator.grassPerChunk = 2
                this.enableRain = this@BiomesData.rain
                this.color = this@BiomesData.color

                if (this@BiomesData.flower) this.addDefaultFlowers()

                val type: BiomeManager.BiomeType = BiomeManager.BiomeType.getType(this@BiomesData.weatherType)

                this@BiomesData.addBiome(this, this@BiomesData.weight, type, BiomeDictionary.Type.getType(this@BiomesData.biomesTag))
            }

            @SideOnly(Side.CLIENT) override fun getModdedBiomeGrassColor(original: Int): Int = this@BiomesData.grassColor
            @SideOnly(Side.CLIENT) override fun getWaterColorMultiplier(): Int = this@BiomesData.waterColor
            @SideOnly(Side.CLIENT) override fun getBiomeGrassColor(x: Int, y: Int, z: Int): Int = this@BiomesData.grassColor
            @SideOnly(Side.CLIENT) override fun getBiomeFoliageColor(x: Int, y: Int, z: Int): Int = this@BiomesData.grassColor
        }
    }

    private fun addBiome(biome: BiomeGenBase?, weight: Int, type: BiomeManager.BiomeType?, vararg types: BiomeDictionary.Type?) {
        BiomeManager.addBiome(type, BiomeManager.BiomeEntry(biome, weight))
        BiomeDictionary.registerBiomeType(biome, *types)
    }
}
