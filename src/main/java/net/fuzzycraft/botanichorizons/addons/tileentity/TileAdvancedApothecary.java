package net.fuzzycraft.botanichorizons.addons.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.fuzzycraft.botanichorizons.addons.BHBlocks;
import net.fuzzycraft.botanichorizons.addons.Multiblocks;
import net.fuzzycraft.botanichorizons.util.InventoryHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TileAdvancedApothecary extends RecipeAutomationTileEntity<RecipePetals> {

    public static final int MAX_PARALLELS = 8;
    public static int cachedMaxRecipeWidth = 0;

    public TileAdvancedApothecary() {
        super(Multiblocks.placeholder);
    }

    @Override
    public int getManaMaximum() {
        return 0;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(BHBlocks.autoApothecary);
    }

    public boolean onWanded(EntityPlayer wandUser) {
        return false;
    }


    // Recipe wrangling

    @Override
    public Collection<RecipePetals> getAllRecipes() {
        return BotaniaAPI.petalRecipes;
    }

    @Override
    public int maxRecipeWidth() {
        int max = cachedMaxRecipeWidth;
        if (max > 0) {
            return max;
        } else {
            for (RecipePetals recipe: getAllRecipes()) {
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
    public List<Object> getInputs(@NotNull RecipePetals recipe) {
        return recipe.getInputs();
    }

    @Override
    public List<ItemStack> getOutputs(@NotNull RecipePetals recipe) {
        ArrayList<ItemStack> result = new ArrayList<>();
        result.add(recipe.getOutput());
        return result;
    }


    // Mana HUD

    @SideOnly(Side.CLIENT)
    public void renderHUD(@Nonnull Minecraft mc, @Nonnull ScaledResolution res) {
        //ChargeState state = ChargeState.genState(isOnline, storedMana, ACTIVATE_MANA);
        //String tooltip = state.getLocalisedHudString(BHBlocks.autoApothecary);
        //HUDHandler.drawSimpleManaHUD(state.color, storedMana, MANA_CAPACITY, tooltip, res);
    }
}
