package net.fuzzycraft.botanichorizons.addons.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.fuzzycraft.botanichorizons.addons.BHBlocks;
import net.fuzzycraft.botanichorizons.addons.Multiblocks;
import net.fuzzycraft.botanichorizons.util.ChargeState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.client.core.handler.HUDHandler;

public class TileAdvancedTerraPlate extends AutomationTileEntity {
    public static int MAX_PARALLELS = 64;

    public TileAdvancedTerraPlate() {
        super(Multiblocks.placeholder);
    }

    @Override
    public int getManaMaximum() {
        return 0;
    }

    @Override
    protected void updateEntityCrafting() {

    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(BHBlocks.autoPlate);
    }

    // Brock breaking
    public void dropItems(World world, int x, int y, int z) {

    }

    public boolean onWanded(EntityPlayer wandUser) {
        return false;
    }

    // Mana HUD

    @SideOnly(Side.CLIENT)
    public void renderHUD(Minecraft mc, ScaledResolution res) {
        //ChargeState state = ChargeState.genState(isOnline, storedMana, ACTIVATE_MANA);
        //String tooltip = state.getLocalisedHudString(BHBlocks.autoPlate);
        //HUDHandler.drawSimpleManaHUD(state.color, storedMana, MANA_CAPACITY, tooltip, res);
    }
}
