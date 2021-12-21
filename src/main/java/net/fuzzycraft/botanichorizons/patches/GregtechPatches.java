package net.fuzzycraft.botanichorizons.patches;

import gregtech.api.enums.ToolDictNames;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.crafting.ModCraftingRecipes;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibOreDict;

public class GregtechPatches {
    public static void applyPatches() {

        // GT mortar for petals into floral powder
        for (int i = 0; i < 16; i++) {
            ItemStack flower = new ItemStack(ModBlocks.flower, 1, i);
            ItemStack petal = new ItemStack(ModItems.petal, 1, i);
            ItemStack powder = new ItemStack(ModItems.dye, 1, i);

            // petals
            GT_ModHandler.addShapelessCraftingRecipe(powder, GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{ToolDictNames.craftingToolMortar, petal});
            GT_ModHandler.addPulverisationRecipe(petal, powder);

            // flowers
            GT_ModHandler.addShapelessCraftingRecipe(powder, GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{ToolDictNames.craftingToolMortar, flower});
            GT_ModHandler.addPulverisationRecipe(flower, powder);
        }

        // Petal Block from 9x in compressor + reverse in extractor
        for(int i = 0; i < 16; i++) {
            GT_ModHandler.addCompressionRecipe(new ItemStack(ModItems.petal, 9, i), new ItemStack(ModBlocks.petalBlock, 1, i));
            GT_ModHandler.addExtractionRecipe(new ItemStack(ModBlocks.petalBlock, 1, i), new ItemStack(ModItems.petal, 9, i));
        }

        // Petals from flowers
        for(int i = 0; i < 16; i++) {
            GT_ModHandler.addCompressionRecipe(new ItemStack(ModItems.petal, 1, i), new ItemStack(ModBlocks.petalBlock, 1, i));
        }

    }
}
