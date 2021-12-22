package net.fuzzycraft.botanichorizons.patches;

import gregtech.api.enums.Materials;
import gregtech.api.enums.ToolDictNames;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.crafting.ModCraftingRecipes;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibOreDict;

import javax.annotation.Nullable;
import java.util.List;

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

        // Better floral fertiliser
        List<ItemStack> mutandis = OreDictionary.getOres("itemMutandis");
        List<ItemStack> vinteum = OreDictionary.getOres("dustVinteum");
        for (ItemStack option: mutandis) {
            addMixerRecipe(new ItemStack(ModItems.fertilizer, 2, 0), 16, 80, option, new ItemStack(Items.dye, 1, 15));
        }
        for (ItemStack option: vinteum) {
            addMixerRecipe(new ItemStack(ModItems.fertilizer, 2, 0), 16, 80, option, new ItemStack(Items.dye, 1, 15));
        }

        // Slabs
        addSlabRecipe(new ItemStack(ModFluffBlocks.blazeQuartzSlab, 2), new ItemStack(ModFluffBlocks.blazeQuartz, 1), 16, 80);
        //addSlabRecipe(new ItemStack(ModFluffBlocks.darkPrismarineSlab, 2), new ItemStack(??, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.darkQuartzSlab, 2), new ItemStack(ModFluffBlocks.darkQuartz, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.dirtPathSlab, 2), new ItemStack(ModBlocks.dirtPath, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.dreamwoodPlankSlab, 2), new ItemStack(ModBlocks.dreamwood, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.elfQuartzSlab, 2), new ItemStack(ModFluffBlocks.elfQuartz, 1), 16, 80);
        //addSlabRecipe(new ItemStack(ModFluffBlocks.enderBrickSlab, 2), new ItemStack(??, 1), 16, 80);
        //addSlabRecipe(new ItemStack(ModFluffBlocks.endStoneSlab, 2), new ItemStack(??, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.lavenderQuartzSlab, 2), new ItemStack(ModFluffBlocks.lavenderQuartz, 1), 16, 80);
        //addSlabRecipe(new ItemStack(ModFluffBlocks.livingrockBrickSlab, 2), new ItemStack(??, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.livingrockSlab, 2), new ItemStack(ModBlocks.livingrock, 1), 16, 80);
        //addSlabRecipe(new ItemStack(ModFluffBlocks.livingwoodPlankSlab, 2), new ItemStack(??, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.livingwoodSlab, 2), new ItemStack(ModBlocks.livingwood, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.manaQuartzSlab, 2), new ItemStack(ModFluffBlocks.manaQuartz, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.netherBrickSlab, 2), new ItemStack(Blocks.nether_brick, 1), 16, 80);
        //addSlabRecipe(new ItemStack(ModFluffBlocks.prismarineBrickSlab, 2), new ItemStack(??, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.prismarineSlab, 2), new ItemStack(ModBlocks.prismarine, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.redQuartzSlab, 2), new ItemStack(ModFluffBlocks.redQuartz, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.reedSlab, 2), new ItemStack(ModBlocks.reedBlock, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.shimmerrockSlab, 2), new ItemStack(ModBlocks.shimmerrock, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.shimmerwoodPlankSlab, 2), new ItemStack(ModBlocks.shimmerwoodPlanks, 1), 16, 80);
        //addSlabRecipe(new ItemStack(ModFluffBlocks.soulBrickSlab, 2), new ItemStack(??, 1), 16, 80);
        //addSlabRecipe(new ItemStack(ModFluffBlocks.snowBrickSlab, 2), new ItemStack(??, 2), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.sunnyQuartzSlab, 2), new ItemStack(ModFluffBlocks.sunnyQuartz, 1), 16, 80);
        addSlabRecipe(new ItemStack(ModFluffBlocks.thatchSlab, 2), new ItemStack(ModBlocks.thatch, 1), 16, 80);
        //addSlabRecipe(new ItemStack(ModFluffBlocks.tileSlab, 2), new ItemStack(??, 1), 16, 80);

    }

    private static void addMixerRecipe(ItemStack output, int volt, int ticks, ItemStack... input) {
        GT_Recipe.GT_Recipe_Map.sMixerRecipes.addRecipe(
                true,
                input,
                new ItemStack[]{output},
                null, null, null, null,
                ticks, volt,
                0
        );
    }

    @Nullable
    private static IRecipe addSlabRecipe(ItemStack output, ItemStack input, int volt, int ticks) {
        GT_Recipe.GT_Recipe_Map.sCutterRecipes.addRecipe(
                true,
                new ItemStack[]{input},
                new ItemStack[]{output},
                null, null,
                new FluidStack[]{Materials.Water.getFluid(4)},
                null,
                ticks, volt, 0
        );
        GT_Recipe.GT_Recipe_Map.sCutterRecipes.addRecipe(
                true,
                new ItemStack[]{input},
                new ItemStack[]{output},
                null, null,
                new FluidStack[]{GT_ModHandler.getDistilledWater(3)},
                null,
                ticks, volt, 0
        );
        GT_Recipe.GT_Recipe_Map.sCutterRecipes.addRecipe(
                true,
                new ItemStack[]{input},
                new ItemStack[]{output},
                null, null,
                new FluidStack[]{Materials.Lubricant.getFluid(1)},
                null,
                ticks / 2, volt, 0
        );
        if (volt < 32 && (output.stackSize % 2) == 0) {
            ItemStack half_output = new ItemStack(output.getItem(), output.stackSize / 2, output.getItemDamage());
            GT_ModHandler.addCraftingRecipe(
                    half_output,
                    new Object[]{
                            "sR",
                            'R', input
                    }
            );
            /* Might need this later for the lexicon?
            return new ShapedOreRecipe(
                    half_output,
                    "sR",
                    's', ToolDictNames.craftingToolSaw.name(),
                    'R', input
            );*/
        }
        return null;
    }
}
