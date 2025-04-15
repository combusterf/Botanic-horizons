package net.fuzzycraft.botanichorizons.util.multiblock;

public class WrongBlockException extends Exception {
    public final int xCoord;
    public final int yCoord;
    public final int zCoord;
    public final String blockName;

    public WrongBlockException(int xCoord, int yCoord, int zCoord, String blockName) {
        super(String.format("Did not find \"%s\" at (%d, %d, %d)", blockName, xCoord, yCoord, zCoord));
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
        this.blockName = blockName;
    }
}
