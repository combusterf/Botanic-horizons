package net.fuzzycraft.botanichorizons.addons.tileentity;

import net.fuzzycraft.botanichorizons.util.multiblock.MultiblockHelper;
import net.minecraft.world.World;

public abstract class RecipeAutomationTileEntity extends AutomationTileEntity {
    public RecipeAutomationTileEntity(MultiblockHelper structure) {
        super(structure);
    }

    @Override
    protected void updateEntityCrafting() {

    }

    // Brock breaking
    public void dropItems(World world, int x, int y, int z) {

    }
}
