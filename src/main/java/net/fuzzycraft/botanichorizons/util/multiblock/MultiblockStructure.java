package net.fuzzycraft.botanichorizons.util.multiblock;

public final class MultiblockStructure {
    final int dx;
    final int dy;
    final int dz;
    final MultiblockCheck check;

    public MultiblockStructure(int dx, int dy, int dz, MultiblockCheck check) {
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
        this.check = check;
    }
}
