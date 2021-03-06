package quantumcraft.tile;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.util.UtilInventory;

import java.util.List;

public class TileIONHarvester extends TileEnergySink {

    @Override
    public int getMaxEnergy() {
        return 100;
    }

    @Override
    public int guiID() {
        return -1;
    }

    @Override
    public void onBlockBreak() {

    }

    public boolean breakBlock(int x, int y, int z) {
        Block block = worldObj.getBlock(x, y, z);
        int blockMeta = worldObj.getBlockMetadata(x, y, z);
        if (this.getCurrentEnergy() >= 5) {
            if (!block.isAir(worldObj, x, y, z)) {
                List<ItemStack> drops = block.getDrops(worldObj, x, y, z, blockMeta, 0);
                worldObj.setBlockToAir(x, y, z);
                subtractEnergy(64);
                for (int i = drops.size(); i-- > 0; ) {
                    ItemStack dropStack = drops.get(i);
                    UtilInventory.dropStack(this, dropStack, this.getDropDirection());
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (redstonePower) return;
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            this.addEnergy(this.requestPacket(100));
        }
    }
}
