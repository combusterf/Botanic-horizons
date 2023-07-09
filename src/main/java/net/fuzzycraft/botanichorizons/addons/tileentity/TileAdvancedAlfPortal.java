package net.fuzzycraft.botanichorizons.addons.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.common.block.tile.TileAlfPortal;

import java.util.List;

public class TileAdvancedAlfPortal extends TileAlfPortal implements IInventory {

    static final int INPUT_SIZE = 2;
    static final int OUTPUT_SIZE = 2;

    public final InventoryBasic inventoryHandler;

    public TileAdvancedAlfPortal() {
        inventoryHandler = new InventoryBasic("name", false, INPUT_SIZE + OUTPUT_SIZE);
    }

    // IInventory

    @Override
    public int getSizeInventory() {
        return inventoryHandler.getSizeInventory();
    }
    @Override
    public ItemStack getStackInSlot(int slotIn) {
        return inventoryHandler.getStackInSlot(slotIn);
    }
    @Override
    public ItemStack decrStackSize(int index, int count) {
        return inventoryHandler.decrStackSize(index, count);
    }
    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        return inventoryHandler.getStackInSlotOnClosing(index);
    }
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inventoryHandler.setInventorySlotContents(index, stack);
    }
    @Override
    public String getInventoryName() {
        return inventoryHandler.getInventoryName();
    }
    @Override
    public boolean hasCustomInventoryName() {
        return inventoryHandler.hasCustomInventoryName();
    }
    @Override
    public int getInventoryStackLimit() {
        return inventoryHandler.getInventoryStackLimit();
    }
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return inventoryHandler.isUseableByPlayer(player);
    }
    @Override
    public void openInventory() {
        inventoryHandler.openInventory(); // no-op
    }
    @Override
    public void closeInventory() {
        inventoryHandler.closeInventory(); // no-op
    }
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index >= INPUT_SIZE || index < 0) return false; // do not allow inserts into output slots
        if (stack == null || stack.getItem() == null) return false;

        Item itemToCheck = stack.getItem();
        for(RecipeElvenTrade recipe : BotaniaAPI.elvenTradeRecipes) {
            final List<Object> inputs = recipe.getInputs();
            for (Object input: inputs) {
                if (input instanceof String) {

                }
            }
        }

        return false; // item not found
    }
}
