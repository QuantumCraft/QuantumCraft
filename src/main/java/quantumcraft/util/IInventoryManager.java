package quantumcraft.util;

import net.minecraft.item.ItemStack;

import java.util.Map;

public interface IInventoryManager
{
	public ItemStack addItem(ItemStack stack);
	public ItemStack removeItem(int maxRemove);
	public ItemStack removeItem(int maxRemove, ItemStack type);

	public ItemStack getSlotContents(int slot);

	public int hasItem(ItemStack type);

	public int findItem(ItemStack type);

	public int[] getSlots();
	public Map<Integer, ItemStack> getContents();
}
