package quantumcraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import quantumcraft.core.interfaces.IQuantumEnergizable;
import quantumcraft.inventory.SimpleInventory;
import quantumcraft.items.ItemInfinitePower;
import quantumcraft.tile.abstracttiles.TileEnergySource;
import quantumcraft.tile.abstracttiles.TileMachineBase;
import quantumcraft.util.TileUtil;

public class TileQuantumEnergyExtractor extends TileEnergySource implements ISidedInventory {

    public ItemStack[] inventory = new ItemStack[2];

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        if (var1 == 0) {
            return new int[]{1};
        } else return new int[]{0};
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
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        return TileUtil.decrStackSize(i, j, inventory);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if (this.inventory[i] != null) {
            ItemStack itemstack = this.inventory[i];
            this.inventory[i] = null;
            return itemstack;
        } else {
            return null;
        }
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
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {

        return 1;
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
    public int guiID() {
        return 5;
    }

    @Override
    public void onBlockBreak() {
        SimpleInventory tmp = new SimpleInventory(inventory, "tmp", 64);
        tmp.dropContents(worldObj, xCoord, yCoord, zCoord);
    }

    //I think this method would like a refactor, but meh. if you have the nerves to do it, go ahead. AND DO NOT BREAK IT
    @Override
    public void updateEntity() {
        super.updateEntity();
        if (redstonePower) return;
        extractPower(inventory, this, this, true, 0);
        if (updateNextTick) {
            // All nearby players need to be updated if the status of work
            // changes, or if heat runs out / starts up, in order to change
            // texture.
            updateNextTick = false;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    public static void extractPower(ItemStack[] inventoryLocal, TileMachineBase tile, ISidedInventory inv,
                                    boolean runProcess, int inputSlot) {
        if (inventoryLocal[inputSlot] != null) {
            if (inventoryLocal[inputSlot].getItem() instanceof IQuantumEnergizable) {
                IQuantumEnergizable e = ((IQuantumEnergizable) inventoryLocal[inputSlot].getItem());
                int cycle;
                if (e instanceof ItemInfinitePower) {
                    cycle = 100000;

                } else {
                    cycle = 50;
                }
                if (!(tile.getCurrentEnergy() + cycle <= tile.getMaxEnergy())) {
                    cycle = tile.getMaxEnergy() - tile.getCurrentEnergy();
                }
                if (e.getCurrentQEnergyBuffer(inventoryLocal[inputSlot]) < cycle) {
                    cycle = e.getCurrentQEnergyBuffer(inventoryLocal[inputSlot]);
                }
                e.setCurrentQEnergyBuffer(inventoryLocal[inputSlot],
                        e.getCurrentQEnergyBuffer(inventoryLocal[inputSlot]) - cycle);
                inventoryLocal[inputSlot].getItem().setDamage(inventoryLocal[inputSlot],
                        e.getMaxQEnergyValue() - e.getCurrentQEnergyBuffer(inventoryLocal[inputSlot]));
                tile.addEnergy(cycle);

                if (e.getCurrentQEnergyBuffer(inventoryLocal[inputSlot]) == 0 && runProcess &&
                        inventoryLocal[1] == null) {
                    inventoryLocal[1] = inventoryLocal[inputSlot].copy();
                    inv.decrStackSize(0, 1);
                }
            }

        }
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

    @Override
    public int getMaxEnergy() {
        return 100; //this is supposed to be a very small buffer
    }

}
