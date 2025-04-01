package net.fuzzycraft.botanichorizons.util.multiblock;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public interface MultiblockCheck {
    boolean check(@Nonnull Block block, @Nonnull World world, int x, int y, int z);
}
