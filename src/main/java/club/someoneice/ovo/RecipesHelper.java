package club.someoneice.ovo;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;

public class RecipesHelper {
    public static void addShapelessRecipe(ArrayList<Object> itemList, ItemStack output) {
        GameRegistry.addShapelessRecipe(output, new ArrayList<Object>(itemList).toArray());
    }

    public static void addRecipe(ArrayList<String> recipe, ArrayList<String> charList, ArrayList<Object> itemList, ItemStack output) {

        ArrayList<Object> recipeList = new ArrayList<Object>();

        if (recipe != null) {
            recipeList.addAll(recipe);

            for (int i = 0; i < charList.size(); i++) {
                recipeList.add(charList.get(i).toCharArray()[0]);
                recipeList.add(itemList.get(i));
            }
        }

        GameRegistry.addRecipe(new ShapedOreRecipe(output, recipeList.toArray()));
    }
}
