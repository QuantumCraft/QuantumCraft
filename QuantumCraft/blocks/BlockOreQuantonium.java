package mods.quantumcraft.blocks;

import java.util.Random;

import mods.quantumcraft.core.Loader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockOreQuantonium extends BlockOre {

	public BlockOreQuantonium(int par1) {
		super(par1);
		this.setHardness(2.5F);
	}
	
	@Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Loader.ItemRawQuantonium.itemID;
    }

	@Override
    public int quantityDropped(Random par1Random)
    {
        return 1 + par1Random.nextInt(3);
    }
	
}