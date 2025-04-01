package net.fuzzycraft.botanichorizons.util.multiblock;

import net.fuzzycraft.botanichorizons.util.Facing2D;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class MultiblockHelper {
    public final MultiblockStructure[] blocks;

    public MultiblockHelper(MultiblockStructure[] blocks) {
        this.blocks = blocks;
    }

    public boolean checkStructurePart(@Nonnull World world, int rootX, int rootY, int rootZ, @Nonnull Facing2D orientation, int index)
    {
        MultiblockStructure part = blocks[index];
        int checkX = transformedX(rootX, orientation, part);
        int checkY = transformedY(rootY, part);
        int checkZ = transformedZ(rootZ, orientation, part);
        if (world.checkChunksExist(checkX, checkY, checkZ, checkX, checkY, checkZ)) {
            Block block = world.getBlock(checkX, checkY, checkZ);
            return part.check.check(block, world, checkX, checkY, checkZ);
        } else {
            // Can't validate structure if it's unloaded
            // assume it's ok for a partially unloaded structure so that it doesn't disable near the loaded edge
            return true;
        }
    }

    public boolean checkEntireStructure(@Nonnull World world, int rootX, int rootY, int rootZ, @Nonnull Facing2D orientation, int index) {
        for(MultiblockStructure part : blocks) {
            int checkX = transformedX(rootX, orientation, part);
            int checkY = transformedY(rootY, part);
            int checkZ = transformedZ(rootZ, orientation, part);
            if (world.checkChunksExist(checkX, checkY, checkZ, checkX, checkY, checkZ)) {
                Block block = world.getBlock(checkX, checkY, checkZ);
                if (!part.check.check(block, world, checkX, checkY, checkZ)) {
                    return false;
                }
            } else {
                // Can't validate structure if it's unloaded
                return false;
            }
        }
        return false;
    }

    private int transformedX(int rootX, @Nonnull Facing2D orientation, @Nonnull MultiblockStructure item) {
        return rootX + orientation.dx * item.dx + orientation.cw_dx * item.dz;
    }

    private int transformedY(int rootY, @Nonnull MultiblockStructure item) {
        return rootY + item.dy;
    }

    private int transformedZ(int rootZ, @Nonnull Facing2D orientation, @Nonnull MultiblockStructure item) {
        return rootZ + orientation.dz * item.dz + orientation.cw_dz * item.dx;
    }
}
