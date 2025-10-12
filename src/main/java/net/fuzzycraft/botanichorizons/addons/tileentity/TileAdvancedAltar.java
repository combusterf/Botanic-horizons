package net.fuzzycraft.botanichorizons.addons.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.fuzzycraft.botanichorizons.addons.BHBlocks;
import net.fuzzycraft.botanichorizons.addons.Multiblocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileAdvancedAltar extends RecipeAutomationTileEntity {

    public static int MAX_PARALLELS = 16;

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
}
