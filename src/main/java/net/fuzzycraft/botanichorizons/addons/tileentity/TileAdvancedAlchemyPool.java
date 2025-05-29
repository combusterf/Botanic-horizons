package net.fuzzycraft.botanichorizons.addons.tileentity;

import net.fuzzycraft.botanichorizons.addons.Multiblocks;
import net.fuzzycraft.botanichorizons.util.InventoryHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeManaInfusion;

public class TileAdvancedAlchemyPool extends TileAdvancedManaPool {

    public TileAdvancedAlchemyPool() {
        super(Multiblocks.poolAlchemy);
    }

    @Override
    public @Nullable RecipeManaInfusion findMatchingRecipe(ItemStack stack) {
        for(RecipeManaInfusion recipe : BotaniaAPI.manaInfusionRecipes) {
            if (recipe.isAlchemy() && InventoryHelper.isIngredient(stack, recipe.getInput())) {
                return recipe;
            }
        }
        return null;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return null;
    }
}
