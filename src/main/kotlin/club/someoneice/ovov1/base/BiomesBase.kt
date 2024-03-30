package club.someoneice.ovov1.base

import club.someoneice.ovo.data.old.BiomesData
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.world.biome.BiomeGenBase
import net.minecraftforge.common.BiomeDictionary
import net.minecraftforge.common.BiomeManager
import net.minecraftforge.common.BiomeManager.BiomeEntry
import net.minecraftforge.common.BiomeManager.BiomeType

class BiomesBase(private val data: BiomesData): BiomeGenBase(data.id) {
    init {
        this.setBiomeName(data.name)
        this.theBiomeDecorator.treesPerChunk = 10
        this.theBiomeDecorator.grassPerChunk = 2
        this.enableRain = data.rain
        this.color = data.color

        if (data.flower) this.addDefaultFlowers()

        val type: BiomeType = when(data.weather_type) {
            "DESERT" -> BiomeType.DESERT
            "WARM" -> BiomeType.WARM
            "COOL" -> BiomeType.COOL
            "ICY" -> BiomeType.ICY

            else -> BiomeType.WARM
        }

        addBiome(this, data.weight, type, getTag(data.biomes_tag))

    }

    private fun getTag(str: String): BiomeDictionary.Type {
        return when (str) {
            "HOT"           -> BiomeDictionary.Type.HOT
            "COLD"          -> BiomeDictionary.Type.COLD
            "SPARSE"        -> BiomeDictionary.Type.SPARSE
            "DENSE"         -> BiomeDictionary.Type.DENSE
            "WET"           -> BiomeDictionary.Type.WET
            "DRY"           -> BiomeDictionary.Type.DRY
            "SAVANNA"       -> BiomeDictionary.Type.SAVANNA
            "CONIFEROUS"    -> BiomeDictionary.Type.CONIFEROUS
            "JUNGLE"        -> BiomeDictionary.Type.JUNGLE
            "SPOOKY"        -> BiomeDictionary.Type.SPOOKY
            "DEAD"          -> BiomeDictionary.Type.DEAD
            "LUSH"          -> BiomeDictionary.Type.LUSH
            "NETHER"        -> BiomeDictionary.Type.NETHER
            "END"           -> BiomeDictionary.Type.END
            "MUSHROOM"      -> BiomeDictionary.Type.MUSHROOM
            "MAGICAL"       -> BiomeDictionary.Type.MAGICAL
            "OCEAN"         -> BiomeDictionary.Type.OCEAN
            "RIVER"         -> BiomeDictionary.Type.RIVER
            "WATER"         -> BiomeDictionary.Type.WATER
            "MESA"          -> BiomeDictionary.Type.MESA
            "FOREST"        -> BiomeDictionary.Type.FOREST
            "PLAINS"        -> BiomeDictionary.Type.PLAINS
            "MOUNTAIN"      -> BiomeDictionary.Type.MOUNTAIN
            "HILLS"         -> BiomeDictionary.Type.HILLS
            "SWAMP"         -> BiomeDictionary.Type.SWAMP
            "SANDY"         -> BiomeDictionary.Type.SANDY
            "SNOWY"         -> BiomeDictionary.Type.SNOWY
            "WASTELAND"     -> BiomeDictionary.Type.WASTELAND
            "BEACH"         -> BiomeDictionary.Type.BEACH

            else            -> BiomeDictionary.Type.LUSH
        }
    }

    private fun addBiome(biome: BiomeGenBase?, weight: Int, type: BiomeType?, vararg types: BiomeDictionary.Type?) {
        BiomeManager.addBiome(type, BiomeEntry(biome, weight))
        BiomeDictionary.registerBiomeType(biome, *types)
    }

    @SideOnly(Side.CLIENT)
    override fun getModdedBiomeGrassColor(original: Int): Int {
        return data.grass_color
    }

    @SideOnly(Side.CLIENT)
    override fun getWaterColorMultiplier(): Int {
        return data.water_color
    }

    @SideOnly(Side.CLIENT)
    override fun getBiomeGrassColor(p_150558_1_: Int, p_150558_2_: Int, p_150558_3_: Int): Int {
        return data.grass_color
    }

    @SideOnly(Side.CLIENT)
    override fun getBiomeFoliageColor(p_150571_1_: Int, p_150571_2_: Int, p_150571_3_: Int): Int {
        return data.grass_color
    }
}