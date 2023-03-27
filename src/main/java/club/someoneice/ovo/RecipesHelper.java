package club.someoneice.ovo;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipesHelper {
    public static void addShapelessRecipe(ItemStack output, Object[] obj) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, obj));
    }

    public static void addShapedRecipe(ItemStack output, Object[] obj) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, obj));
    }
}
