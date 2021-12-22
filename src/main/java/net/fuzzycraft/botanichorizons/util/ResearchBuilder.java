package net.fuzzycraft.botanichorizons.util;

import net.fuzzycraft.botanichorizons.mod.ForgeMod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

import java.util.LinkedList;

public class ResearchBuilder {

    public static final String category = ForgeMod.MOD_ID;
    public static final String prefix = "BH_";

    public final String key;
    public int row = 0;
    public int col = 0;
    public int researchDifficulty = 1;
    public AspectList researchAspects;
    public LinkedList<ResearchPage> content = new LinkedList<>();
    public ResourceLocation researchIcon = new ResourceLocation("botania", "textures/items/grassSeeds0.png");

    public ResearchBuilder(String key) {
        this.key = prefix + key;
    }

    public ResearchBuilder setResearchAspects(Aspect... aspects) {
        AspectList list = new AspectList();
        for (Aspect aspect: aspects) {
            list.add(aspect, 1);
        }
        this.researchAspects = list;
        return this;
    }

    // goes in units of whole tiles - have >= 2 difference to see lines
    public ResearchBuilder setBookLocation(int x, int y) {
        this.row = y;
        this.col = x;
        return this;
    }

    public ResearchBuilder setDifficulty(int difficulty) {
        this.researchDifficulty = difficulty;
        return this;
    }

    public ResearchBuilder setResearchIconItem(String mod, String filename) {
        this.researchIcon = new ResourceLocation(mod, "textures/items/" + filename);
        return this;
    }

    public ResearchBuilder addSingleTextPage() {
        content.add(new ResearchPage(this.key, category + "." + key + ".body"));
        return this;
    }

    public ResearchBuilder addTextPages(int i18n_start, int i18n_end) {
        for (int i = i18n_start; i <= i18n_end; i++) {
            content.add(new ResearchPage(this.key, category + "." + key + ".body_" + i));
        }
        return this;
    }

    public ResearchBuilder addCrucibleRecipe(AspectList aspects, ItemStack out, ItemStack in) {
        CrucibleRecipe recipe = new CrucibleRecipe(key, out, in, aspects);
        content.add(new ResearchPage(recipe));
        return this;
    }

    public ResearchBuilder addInfusionRecipe(AspectList aspects, ItemStack out, int instability, ItemStack centerItem, ItemStack... inputs) {
        InfusionRecipe recipe = new InfusionRecipe(key,out, instability, aspects, centerItem, inputs);
        content.add(new ResearchPage(recipe));
        return this;
    }

    public ResearchBuilder apply(WithResearchBuilder lambda) {
        lambda.apply(this);
        return this;
    }

    public void commit() {
        ResearchItem research = new ResearchItem(
            key, category, researchAspects,
            col, row, researchDifficulty,
            researchIcon
        );
        research.setPages(content.toArray(new ResearchPage[0]));
        ResearchCategories.addResearch(research);
    }

    public interface WithResearchBuilder {
        void apply(ResearchBuilder builder);
    }
}
