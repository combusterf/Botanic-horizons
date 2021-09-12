package net.fuzzycraft.botanichorizons.patches;

import net.fuzzycraft.botanichorizons.mod.ForgeMod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import vazkii.botania.common.block.ModBlocks;

import java.util.LinkedList;

public class ThaumcraftPatches {
    public static void applyPatches() {

        final String category = ForgeMod.MOD_ID;
        final String prefix = "BH_";

        ResearchCategories.registerCategory(
                category,
                new ResourceLocation("botania", "textures/items/grassSeeds0.png"),
                new ResourceLocation(ForgeMod.MOD_ID, "textures/tc_bg.png")

        );

        // MV method of upgrading shrooms into flowers
        String basicFlowerKey = prefix + "FLOWERS";
        ResearchItem basicFlowerResearch = new ResearchItem(
                basicFlowerKey, category,
                researchList(Aspect.PLANT, Aspect.EXCHANGE, Aspect.MAGIC),
                0, 0, 1,
                new ResourceLocation("botania", "textures/items/grassSeeds0.png")
        );
        LinkedList<ResearchPage> basicFlowerCrafts = new LinkedList<>();
        basicFlowerCrafts.add(textPage(category, basicFlowerKey));
        for (int i = 0; i < 16; i++) {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    basicFlowerKey,
                    new ItemStack(ModBlocks.flower, 1, i),
                    new ItemStack(ModBlocks.mushroom, 1, i),
                    new AspectList().add(Aspect.PLANT, 4).add(Aspect.MAGIC, 4)
            );
            basicFlowerCrafts.add(new ResearchPage(recipe));
        }
        basicFlowerResearch.setPages(basicFlowerCrafts.toArray(new ResearchPage[0]));
        ResearchCategories.addResearch(basicFlowerResearch);

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
