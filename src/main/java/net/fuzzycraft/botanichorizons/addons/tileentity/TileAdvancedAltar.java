package net.fuzzycraft.botanichorizons.addons.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.fuzzycraft.botanichorizons.addons.BHBlocks;
import net.fuzzycraft.botanichorizons.addons.Multiblocks;
import net.fuzzycraft.botanichorizons.util.ChargeState;
import net.fuzzycraft.botanichorizons.util.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.block.ModBlocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TileAdvancedAltar extends RecipeAutomationTileEntity<RecipeRuneAltar> {

    public static final int MAX_PARALLELS = 16;
    public static final int IDLE_MANA = 10000;
    public static final int ACTIVATE_MANA = 5000;
    public static int cachedMaxRecipeWidth = 0;

    public TileAdvancedAltar() {
        super(Multiblocks.placeholder, 5);
    }

    @Override
    public int getManaMaximum() {
        return setRecipe == null ? IDLE_MANA : IDLE_MANA + lastCheckedMana;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(BHBlocks.autoAltar);
    }

    public boolean onWanded(EntityPlayer wandUser) {
        return false;
    }

    // Mana HUD

    // Mana HUD

    @SideOnly(Side.CLIENT)
    public void renderHUD(Minecraft mc, ScaledResolution res) {
        if (lastCheckedMana == 0 || setRecipe == null) {
            ChargeState state = ChargeState.genState(isOnline, storedMana, ACTIVATE_MANA);
            String tooltip = state.getLocalisedHudString(BHBlocks.autoAltar);
            HUDHandler.drawSimpleManaHUD(state.color, storedMana, IDLE_MANA, tooltip, res);
        } else {
            // crafting state
            String tooltip = StatCollector.translateToLocal(BHBlocks.autoAltar.getUnlocalizedName() + ".hud.collecting");
            HUDHandler.drawSimpleManaHUD(0xE0A044, storedMana, lastCheckedMana + IDLE_MANA, tooltip, res);
        }
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
        List<Object> baseInputs = new ArrayList<>(recipe.getInputs());
        baseInputs.add(new ItemStack(ModBlocks.livingrock, 1));

        return baseInputs;
    }

    @Override
    public List<ItemStack> getOutputs(@NotNull RecipeRuneAltar recipe) {
        List<ItemStack> outputs = new ArrayList<>(1);
        outputs.add(recipe.getOutput());

        return outputs;
    }

    @Override
    public int getManaRequired(@NotNull RecipeRuneAltar recipe, int copies) {
        return recipe.getManaUsage() * copies;
    }

    @Override
    public int getAvailableParallels(@NotNull RecipeRuneAltar recipe) {
        return MAX_PARALLELS;
    }
}
