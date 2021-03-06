package quantumcraft.tile.abstracttiles;

import net.minecraft.nbt.NBTTagCompound;
import quantumcraft.core.QuantumCraft;
import quantumcraft.net.EnergySourceList;
import quantumcraft.util.Coords;

public abstract class TileEnergySink extends TileMachineBase {
    private EnergySourceList sourceList;

    public void addSourceToList(Coords l, Coords source) {
        if (l.compareCoords(new Coords(this))) return;
        if (sourceList == null) sourceList = new EnergySourceList();
        sourceList.addSource(source);
    }

    public void replaceSourceList(Coords l, EnergySourceList sources) {
        sourceList = sources;
        QuantumCraft.logHandler.debugPrint(this, "SourceList size is: " + sourceList.size());
    }

    /**
     * Method to receive an energy packet from an energy source
     *
     * @param size size of the requested packet
     * @return actual amount of energy received
     */
    public int requestPacket(int size) {
        int useSize = size;
        int possibleEnergy = this.getMaxEnergy() - this.getCurrentEnergy();
        if (possibleEnergy < size) useSize = possibleEnergy;

        if (sourceList != null) return sourceList.requestQuantumEnergy(worldObj, useSize);
        System.out.println("[QuantumCraft] Sources list not initialized. Please report this.");
        return 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        sourceList = EnergySourceList.read(par1NBTTagCompound.getCompoundTag("ENET"));
    }

    /**
     * Writes a tile entity to NBT.
     */

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setTag("ENET", sourceList.write());
        super.writeToNBT(par1NBTTagCompound);
    }
}
