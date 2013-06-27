package mods.QuantumCraft.gui;

import org.lwjgl.opengl.GL11;

import mods.QuantumCraft.machine.TileQDeenergizer;
import mods.QuantumCraft.inventory.*;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

public class GuiQDeenergizer extends GuiContainer {

	private TileQDeenergizer tile;
	
	public GuiQDeenergizer(Container par1Container) {
		super(par1Container);
		tile = ((ContainerQDeenergizer)par1Container).tile;
	}
	
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.tile.getInvName();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/QuantumCraft/textures/gui/deenergizer.png");
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        
        int h = 16-(tile.QEnergyBuffer / tile.lastItemValue * 16);
        
        this.drawTexturedModalRect(69, 34, 176, 0+h, 16, 16);
        
	}

}
