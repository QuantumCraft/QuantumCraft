package quantumcraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import quantumcraft.core.Config;
import quantumcraft.inventory.SimpleInventory;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.util.TileUtil;

public class TileIONForge extends TileEnergySink implements ISidedInventory {

    public ItemStack[] inventory = new ItemStack[4];
    public int progress = 0;
    private int processTime = 0;
    public boolean isProcessing = false;

    @Override
    public int getMaxEnergy() {
        return 1000;
    }

    @Override
    public int guiID() {
        return 6;
    }

    @Override
    public void onBlockBreak() {
        SimpleInventory tmp = new SimpleInventory(inventory, "tmp", 64);
        tmp.dropContents(worldObj, xCoord, yCoord, zCoord);
    }

    @Override public String getStatusText() {
        if (isProcessing) return EnumChatFormatting.GREEN + "Working";
        return EnumChatFormatting.YELLOW + "Idle";
    }

    /**
     * removeProcess is just a boolean for whether or not to remove something from the counter or to reset it back to
     * 10.
     */
    @Override
    public void updateEntity() {
        super.updateEntity();
        if (redstonePower) return;
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            this.addEnergy(this.requestPacket(100));
        }
        if (getCurrentEnergy() > Config.IONForgeEnergyCost) {

            boolean removeProcess = false;
            for (int i = 0; i < 2; i++) {
                if (this.canProcess(i)) {
                    removeProcess = true;
                    updateProgress();
                    if (processTime == 0) {
                        process(i);
                        subtractEnergy(Config.IONForgeEnergyCost);
                    }
                }
            }

            if (removeProcess) {
                isProcessing = true;
                if (processTime >= 0) {
                    processTime--;
                }
            } else {
                isProcessing = false;
            }
            if (processTime < 0) {
                progress = 0;
                processTime = 10;
            }

            if (updateNextTick) {
                // All nearby players need to be updated if the status of work
                // changes, or if heat runs out / starts up, in order to change
                // texture.
                updateNextTick = false;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        }
    }

    private void updateProgress() {
        if (progress >= 10) {
            progress = 0;
        }
        if (progress < 10) {
            progress++;
        }
    }

    /**
     * @param i  which iteration
     * @param io input or output; output = true
     * @return value of input or output
     */
    private int iteratorSwitch(int i, boolean io) {
        int input = 0;
        int output = 0;
        switch (i) {
            case 0:
                input = 0;
                output = 1;
                break;
            case 1:
                input = 2;
                output = 3;
                break;
        }
        return io ? output : input;
    }

    /**
     * Processes the item in the input slot and puts the output in the output slot
     *
     * @param i which iteration
     */
    private void process(int i) {
        int input = iteratorSwitch(i, false);
        int output = iteratorSwitch(i, true);
        boolean outputCheck = false;
        if (inventory[output] != null) {
            outputCheck = true;
        }
        if (inventory[output] == null) {
            inventory[output] = FurnaceRecipes.smelting().getSmeltingResult(inventory[input]).copy();
        } else if (outputCheck && !(inventory[output].stackSize >= 64)) {
            inventory[output].stackSize++;
        }
        this.decrStackSize(input, 1);
    }

    /**
     * @param i Which iteration of slots. Example: For slots 0 and 1 where 0 is input you pass it 1 and for 2 and 3 you
     *          pass it 2
     * @return Is it able to process or not.
     */
    private boolean canProcess(int i) {
        boolean process = true;
        int input = iteratorSwitch(i, false);
        int output = iteratorSwitch(i, true);
        ItemStack r = FurnaceRecipes.smelting().getSmeltingResult(inventory[input]);
        if (r == null) return false;

        if (inventory[input] == null) {
            process = false;
        }
        if (inventory[input] != null) {
            if (inventory[output] != null) {
                if (inventory[output] != r) {
                    process = false;
                }
            }
        }
        return process;
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory[i];
    }

    /**
     * @param i input slot
     * @param j amount to remove
     * @return the itemstack that has been modified
     */
    @Override
    public ItemStack decrStackSize(int i, int j) {
        return TileUtil.decrStackSize(i, j, inventory);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.inventory[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "ION Forge";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityplayer
                .getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <=
                64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        if (var1 == 0) {
            return new int[]{1, 3};
        } else return new int[]{0, 2};
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return j != 0;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        return i != 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        TileUtil.readInventory(par1NBTTagCompound, this.inventory);
        updateNextTick = true;
    }

    /**
     * Writes a tile entity to NBT.
     */

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        TileUtil.saveInventory(par1NBTTagCompound, this.inventory);
        super.writeToNBT(par1NBTTagCompound);
    }
}
