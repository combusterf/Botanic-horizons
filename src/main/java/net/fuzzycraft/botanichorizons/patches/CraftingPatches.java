package net.fuzzycraft.botanichorizons.patches;

import net.fuzzycraft.botanichorizons.mod.OreDict;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.crafting.ModCraftingRecipes;
import vazkii.botania.common.item.ItemTwigWand;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibOreDict;

public class CraftingPatches {
    public static void applyPatches() {

        // Lexicon Recipe
        addShapelessOreDictRecipe(new ItemStack(ModItems.lexicon), OreDict.MUSHROOM, Items.book);
        ModCraftingRecipes.recipeLexicon = BotaniaAPI.getLatestAddedRecipe();

        // Petal/Dye Recipes
        // TODO: make this 1:1 and add GT extractor version.
        for(int i = 0; i < 16; i++)
            addShapelessOreDictRecipe(new ItemStack(ModItems.petal, 2, i), LibOreDict.FLOWER[i]);
        ModCraftingRecipes.recipesPetals = BotaniaAPI.getLatestAddedRecipes(16);


        // Petal Block Recipes
        // TODO: GT compressor recipe
        for(int i = 0; i < 16; i++)
            addOreDictRecipe(new ItemStack(ModBlocks.petalBlock, 1, i),
                    "PPP", "PPP", "PPP", // PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
                    'P', LibOreDict.PETAL[i]);
        ModCraftingRecipes.recipesPetalBlocks = BotaniaAPI.getLatestAddedRecipes(16);

        // Pestle and Mortar Recipe
        // TODO: should be removed.
        addOreDictRecipe(new ItemStack(ModItems.pestleAndMortar),
                " S", "W ", "B ",
                'S', "stickWood",
                'W', "plankWood",
                'B', Items.bowl);
        ModCraftingRecipes.recipePestleAndMortar = BotaniaAPI.getLatestAddedRecipe();

        // Wand of the Forest Recipes
        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 16; j++) {
                addOreDictRecipe(ItemTwigWand.forColors(i, j),
                        " AS", " SB", "S  ",
                        'A', OreDict.FLOWER_INGREDIENT[i],
                        'B', OreDict.FLOWER_INGREDIENT[j],
                        'S', LibOreDict.LIVINGWOOD_TWIG);
            }
        ModCraftingRecipes.recipesTwigWand = BotaniaAPI.getLatestAddedRecipes(256);

        // Petal Apothecary Recipes
        // TODO: gregify
        for(int i = 0; i < 16; i++)
            addOreDictRecipe(new ItemStack(ModBlocks.altar),
                    "SPS", " C ", "CCC",
                    'S', "slabCobblestone",
                    'P', OreDict.FLOWER_INGREDIENT[i],
                    'C', "cobblestone");
        ModCraftingRecipes.recipesApothecary = BotaniaAPI.getLatestAddedRecipes(16);

        // TODO: from recipesSpreader
    }

    private static void addOreDictRecipe(ItemStack output, Object... recipe) {
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
    }

    private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
        CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
    }
}
