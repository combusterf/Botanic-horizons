package net.fuzzycraft.botanichorizons.patches;

import net.fuzzycraft.botanichorizons.mod.ForgeMod;
import net.fuzzycraft.botanichorizons.util.ResearchBuilder;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;
import vazkii.botania.common.block.ModBlocks;

public class ThaumcraftPatches {
    public static void applyPatches() {

        // make the research page
        ResearchCategories.registerCategory(
                ResearchBuilder.category,
                new ResourceLocation("botania", "textures/items/grassSeeds0.png"),
                new ResourceLocation(ForgeMod.MOD_ID, "textures/tc_bg.png")
        );

        // MV method of upgrading shrooms into flowers
        new ResearchBuilder("FLOWERS")
                .setBookLocation(0,0)
                .setResearchIconItem("botania", "grassSeeds0.png")
                .setResearchAspects(Aspect.PLANT, Aspect.EXCHANGE, Aspect.MAGIC)
                .setDifficulty(1)
                .addSingleTextPage()
                .apply(builder -> {
                    for (int i = 0; i < 16; i++) {
                        builder.addCrucibleRecipe(
                            ThaumcraftAspects.FLOWER,
                            new ItemStack(ModBlocks.flower, 1, i),
                            new ItemStack(ModBlocks.mushroom, 2, i)
                        );
                    }
                }).commit();

        // MV method of duplicating flowers
        new ResearchBuilder("FLOWERDUPE")
            .setBookLocation(-2,0)
            .setResearchIconItem("botania", "grassSeeds1.png")
            .setResearchAspects(Aspect.PLANT, Aspect.SENSES, Aspect.MAGIC)
            .setDifficulty(1)
            .addSingleTextPage()
            .apply(builder -> {
                for (int i = 0; i < 16; i++) {
                    builder.addCrucibleRecipe(
                        ThaumcraftAspects.FLOWER,
                        new ItemStack(ModBlocks.flower, 1, i),
                        new ItemStack(ModBlocks.flower, 2, i)
                    );
                }
            }).commit();

        // HV method of mutating flowers
        new ResearchBuilder("FLOWERCOLOUR")
            .setBookLocation(2,0)
            .setResearchAspects(Aspect.PLANT, Aspect.EXCHANGE, Aspect.SENSES, Aspect.CRAFT)
            .setDifficulty(2)
            .addSingleTextPage()
            .apply(builder -> {
                for (int i = 0; i < 16; i++) {
                    ItemStack dye = new ItemStack(Items.dye, 1, 15 - i); // vanilla dyes sort the other way
                    builder.addInfusionRecipe(
                        new AspectList().add(Aspect.PLANT, 8).add(Aspect.EXCHANGE, 8).add(Aspect.SENSES, 16),
                        new ItemStack(ModBlocks.flower, 1, i),
                        5,
                        new ItemStack(ModBlocks.flower, 1, OreDictionary.WILDCARD_VALUE),
                        dye, dye, dye, dye
                    );
                }
            })
            .commit();
    }

    public static AspectList researchList(Aspect... aspects) {
        AspectList list = new AspectList();
        for (Aspect aspect: aspects) {
            list.add(aspect, 1);
        }
        return list;
    }

    public static ResearchPage textPage(String category, String key) {
        return new ResearchPage(key, category + "." + key + ".body");
    }
}
