package net.fuzzycraft.botanichorizons.addons.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.fuzzycraft.botanichorizons.addons.BHBlocks;
import net.fuzzycraft.botanichorizons.addons.Multiblocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.api.recipe.RecipeRuneAltar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TileAdvancedAltar extends RecipeAutomationTileEntity<RecipeRuneAltar> {

    public static final int MAX_PARALLELS = 16;
    public static int cachedMaxRecipeWidth = 0;

    public TileAdvancedAltar() {
        super(Multiblocks.placeholder);
    }

    @Override
    public int getManaMaximum() {
        return 0;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(BHBlocks.autoAltar);
    }

    public boolean onWanded(EntityPlayer wandUser) {
        return false;
    }

    // Mana HUD

    @SideOnly(Side.CLIENT)
    public void renderHUD(Minecraft mc, ScaledResolution res) {
        //ChargeState state = ChargeState.genState(isOnline, storedMana, ACTIVATE_MANA);
        //String tooltip = state.getLocalisedHudString(BHBlocks.autoAltar);
        //HUDHandler.drawSimpleManaHUD(state.color, storedMana, MANA_CAPACITY, tooltip, res);
    }

    @Override
    public Collection<RecipeRuneAltar> getAllRecipes() {
        return BotaniaAPI.runeAltarRecipes;
    }

    @Override
    public int maxRecipeWidth() {
        int max = cachedMaxRecipeWidth;
        if (max > 0) {
            return max;
        } else {
            for (RecipeRuneAltar recipe: getAllRecipes()) {
                int recipeMax = recipe.getInputs().size();
                if (recipeMax > max) {
                    max = recipeMax;
                }
            }
            cachedMaxRecipeWidth = max;
            return max;
        }
    }

    @Override
    public List<Object> getInputs(@NotNull RecipeRuneAltar recipe) {
        return recipe.getInputs();
    }

    @Override
    public List<ItemStack> getOutputs(@NotNull RecipeRuneAltar recipe) {
        List<ItemStack> outputs = new ArrayList<>(1);
        outputs.add(recipe.getOutput());
        // TODO: return runes
        return outputs;
    }

}
