package net.fuzzycraft.botanichorizons.util.multiblock;

public class UnloadedBlockException extends RuntimeException {
    public final int xCoord;
    public final int yCoord;
    public final int zCoord;

    public UnloadedBlockException(int xCoord, int yCoord, int zCoord) {
        super(String.format("Can't form, block unloaded at (%d, %d, %d)", xCoord, yCoord, zCoord));

        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
    }
}
