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

    public Exception checkEntireStructure(@Nonnull World world, int rootX, int rootY, int rootZ, @Nonnull Facing2D orientation) {
        for(MultiblockStructure part : blocks) {
            int checkX = transformedX(rootX, orientation, part);
            int checkY = transformedY(rootY, part);
            int checkZ = transformedZ(rootZ, orientation, part);
            if (world.checkChunksExist(checkX, checkY, checkZ, checkX, checkY, checkZ)) {
                Block block = world.getBlock(checkX, checkY, checkZ);
                if (!part.check.check(block, world, checkX, checkY, checkZ)) {
                    String message = String.format("x: (%d + %d * %d~%d), z: (%d + %d * %d~%d), b: %s",
                            rootX, part.dx, orientation.dx, orientation.cw_dx,
                            rootZ, part.dz, orientation.dz, orientation.cw_dz,
                            part.toString()
                    );
                    return new WrongBlockException(checkX, checkY, checkZ, message);
                }
            } else {
                // Can't validate structure if it's unloaded
                return new UnloadedBlockException(checkX, checkY, checkZ);
            }
        }
        return null;
    }

    // Pos + dz * dir - dx * dir.cw

    private int transformedX(int rootX, @Nonnull Facing2D orientation, @Nonnull MultiblockStructure item) {
        return rootX + item.dz * orientation.dx - item.dx * orientation.cw_dx;
    }

    private int transformedY(int rootY, @Nonnull MultiblockStructure item) {
        return rootY - item.dy;
    }

    private int transformedZ(int rootZ, @Nonnull Facing2D orientation, @Nonnull MultiblockStructure item) {
        return rootZ + item.dz * orientation.dz - item.dx * orientation.cw_dz;
    }
}
