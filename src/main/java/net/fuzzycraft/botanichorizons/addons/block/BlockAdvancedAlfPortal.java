package net.fuzzycraft.botanichorizons.addons.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.fuzzycraft.botanichorizons.addons.tileentity.TileAdvancedAlfPortal;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.common.block.BlockAlfPortal;
import vazkii.botania.common.item.block.ItemBlockMod;

public class BlockAdvancedAlfPortal extends BlockAlfPortal {

    public static final String NAME = "automatedAlfPortal";

    public BlockAdvancedAlfPortal() {
        super();
        setBlockName(NAME);
        GameRegistry.registerBlock(this, ItemBlockMod.class, NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileAdvancedAlfPortal();
    }

}
