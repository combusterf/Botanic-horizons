package net.fuzzycraft.botanichorizons.patches;

import gregtech.api.enums.ToolDictNames;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

public class GregtechPatches {
    public static void applyPatches() {
        // mortar for shrooms/petals into floral powder
        for (int i = 0; i < 16; i++) {
            ItemStack shroom = new ItemStack(ModBlocks.mushroom, 1, i);
            ItemStack petal = new ItemStack(ModItems.petal, 1, i);
            ItemStack powder = new ItemStack(ModItems.dye, 1, i);

            // shrooms
            GT_ModHandler.addShapelessCraftingRecipe(powder, GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{ToolDictNames.craftingToolMortar, shroom});
            GT_ModHandler.addPulverisationRecipe(shroom, powder, powder, 50);

            // petals
            GT_ModHandler.addShapelessCraftingRecipe(powder, GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{ToolDictNames.craftingToolMortar, petal});
            GT_ModHandler.addPulverisationRecipe(petal, powder);
        }
    }
}
