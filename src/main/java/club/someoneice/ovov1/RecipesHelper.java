package club.someoneice.ovov1;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

// The Java Object cannot use Kotlin Any. IDK What's happened, so here is a Java class.
public class RecipesHelper {
    public static void addShapelessRecipe(ItemStack output, Object[] obj) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, obj));
    }

    public static void addShapedRecipe(ItemStack output, Object[] obj) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, obj));
    }
}
