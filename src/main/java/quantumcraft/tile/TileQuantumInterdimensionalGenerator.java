package quantumcraft.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import quantumcraft.tile.abstracttiles.TileEnergySource;
import quantumcraft.util.BasicUtils;
import quantumcraft.util.Coords;
import quantumcraft.util.QuantumInterdimensionalGeneratorDataObject;
import quantumcraft.util.QuantumInterdimensionalGeneratorUtil;

public class TileQuantumInterdimensionalGenerator extends TileEnergySource {

    private int outputRate = 0;
    private int generatorsInChunk = 0;

    @Override
    public int getMaxEnergy() {
        return 300;
    }

    @Override
    public int guiID() {
        return -1;
    }

    @Override
    public void onBlockBreak() {
        System.out.println("onblockBreak");
        QuantumInterdimensionalGeneratorUtil.removeGeneratorFromNewCoords(
                new QuantumInterdimensionalGeneratorDataObject(new Coords(xCoord, yCoord, zCoord)), worldObj);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (redstonePower) return;
        if (generatorsInChunk == 0) return;
        if (worldObj.getWorldTime() % 20 == 0) {
            this.addEnergy((int) Math.floor(10 / generatorsInChunk));
            outputRate = (int) Math.floor(10 / generatorsInChunk);
        }
    }

    public void onQIGChange() {
        int xChunk = BasicUtils.getChunk(xCoord);
        int zChunk = BasicUtils.getChunk(zCoord);

        generatorsInChunk = 0;

        for (QuantumInterdimensionalGeneratorDataObject generator : QuantumInterdimensionalGeneratorUtil.getGenerators()) {
            if (generator == null) return;
            int localXChunk = BasicUtils.getChunk(generator.getCoords().x);
            int localZChunk = BasicUtils.getChunk(generator.getCoords().z);
            if (xChunk == localXChunk && zChunk == localZChunk) generatorsInChunk++;
        }
    }

    @Override
    public String getStatusText() {
        EnumChatFormatting color = outputRate > 5 ? EnumChatFormatting.GREEN : EnumChatFormatting.YELLOW;
        return color + "" + outputRate + " QEU/s";
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        QuantumInterdimensionalGeneratorUtil
                .addGenerator(new QuantumInterdimensionalGeneratorDataObject(new Coords(xCoord, yCoord, zCoord)));
        QuantumInterdimensionalGeneratorUtil.updateAllGenerators(worldObj);
    }
}
