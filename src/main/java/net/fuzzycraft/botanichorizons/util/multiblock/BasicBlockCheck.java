package net.fuzzycraft.botanichorizons.util.multiblock;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public final class BasicBlockCheck implements MultiblockCheck {
    @Nonnull
    final Block referenceBlock;

    public BasicBlockCheck(@Nonnull Block referenceBlock) {
        this.referenceBlock = referenceBlock;
    }

    @Override
    public boolean check(@Nonnull Block block, @Nonnull World world, int x, int y, int z) {
        return block == referenceBlock;
    }
}
