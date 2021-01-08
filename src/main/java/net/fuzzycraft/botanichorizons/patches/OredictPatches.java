package net.fuzzycraft.botanichorizons.patches;

import net.fuzzycraft.botanichorizons.mod.OreDict;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

public class OredictPatches {
    public static void applyPatches() {
        // fold mushrooms and petals together
        for(int i = 0; i < 16; i++) {
            OreDictionary.registerOre(OreDict.FLOWER_INGREDIENT[i], new ItemStack(ModBlocks.mushroom, 1, i));
            OreDictionary.registerOre(OreDict.FLOWER_INGREDIENT[i], new ItemStack(ModItems.petal, 1, i));
            OreDictionary.registerOre(OreDict.MUSHROOM, new ItemStack(ModBlocks.mushroom, 1, i));
        }
    }
}
