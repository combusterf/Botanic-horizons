package net.fuzzycraft.botanichorizons.patches;

import net.fuzzycraft.botanichorizons.mod.OreDict;
import net.minecraft.block.Block;
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

        oredictThirdPartyBlocks("minecraft:yellow_flower", "flowerYellow");
        oredictThirdPartyBlocks("minecraft:yellow_flower", "flowerDandelion");

        oredictThirdPartyBlocks("minecraft:red_flower",
                "flowerRed",   "flowerLightBlue",  "flowerMagenta", "flowerLightGray",  "flowerRed",      "flowerOrange",      "flowerWhite",      "flowerPink",      "flowerLightGray");
        oredictThirdPartyBlocks("minecraft:red_flower",
                "flowerPoppy", "flowerBlueOrchid", "flowerAllium",  "flowerAzureBluet", "flowerTulipRed", "flowerTulipOrange", "flowerTulipWhite", "flowerTulipPink", "flowerOxeyeDaisy");

        oredictThirdPartyBlocks("BiomesOPlenty:flowers", "flowerClover", "flowerSwamp");
        oredictThirdPartyBlocks("BiomesOPlenty:flowers2", "flowerHibiscusPink", "flowerLilyValley");

    }

    public static void oredictThirdPartyBlocks(final String id, final String... names) {
        Block root = Block.getBlockFromName(id);
        for (int i = 0; i < names.length; i++) {
            OreDictionary.registerOre(names[i], new ItemStack(root, 1, i));
        }
    }
}
