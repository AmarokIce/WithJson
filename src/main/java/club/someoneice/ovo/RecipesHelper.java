package club.someoneice.ovo;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class RecipesHelper {
    public static void addShapelessRecipe(ArrayList<Item> itemList, ItemStack output) {
        ArrayList<Object> recipeList = new ArrayList<Object>(itemList);

        GameRegistry.addShapelessRecipe(output, recipeList.toArray());
    }

    public static void addRecipe(ArrayList<String> recipe, ArrayList<String> charList, ArrayList<Item> itemList, ItemStack output) {

        ArrayList<Object> recipeList = new ArrayList<Object>();

        if (recipe != null) {
            recipeList.addAll(recipe);

            for (int i = 0; i < charList.size(); i++) {
                recipeList.add(charList.get(i).toCharArray()[0]);
                recipeList.add(itemList.get(i));
            }
        }

        GameRegistry.addShapedRecipe(output, recipeList.toArray());
    }
}
