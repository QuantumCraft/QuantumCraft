package mods.QuantumCraft.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import mods.QuantumCraft.core.Loader;
import mods.QuantumCraft.research.ResearchItem;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSmallButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.AchievementPage;

public class GuiResearch extends GuiScreen {

    /** The top x coordinate of the achievement map */
    private static final int guiMapTop = AchievementList.minDisplayColumn * 24 - 112;

    /** The left y coordinate of the achievement map */
    private static final int guiMapLeft = AchievementList.minDisplayRow * 24 - 112;

    /** The bottom x coordinate of the achievement map */
    private static final int guiMapBottom = AchievementList.maxDisplayColumn * 24 - 77;

    /** The right y coordinate of the achievement map */
    private static final int guiMapRight = AchievementList.maxDisplayRow * 24 - 77;
    protected int achievementsPaneWidth = 256;
    protected int achievementsPaneHeight = 202;

    /** The current mouse x coordinate */
    protected int mouseX = 0;

    /** The current mouse y coordinate */
    protected int mouseY = 0;
    protected double field_74117_m;
    protected double field_74115_n;

    /** The x position of the achievement map */
    protected double guiMapX;

    /** The y position of the achievement map */
    protected double guiMapY;
    protected double field_74124_q;
    protected double field_74123_r;

    /** Whether the Mouse Button is down or not */
    private int isMouseButtonDown = 0;
    private StatFileWriter statFileWriter;

    private int currentPage = -1;
    private GuiSmallButton button;
    private LinkedList<ResearchItem> researchItems = new LinkedList<ResearchItem>();

    public GuiResearch()
    {
        short short1 = 141;
        short short2 = 141;
        this.field_74117_m = this.guiMapX = this.field_74124_q = (double)(AchievementList.openInventory.displayColumn * 24 - short1 / 2 - 12);
        this.field_74115_n = this.guiMapY = this.field_74123_r = (double)(AchievementList.openInventory.displayRow * 24 - short2 / 2);
        researchItems.clear();
        researchItems.add(new ResearchItem("test","Test", 1, 1, new ItemStack(Loader.ItemRawQuantonium)));
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.buttonList.clear();
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.id == 1)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
        }

        super.actionPerformed(par1GuiButton);
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == this.mc.gameSettings.keyBindInventory.keyCode)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
        }
        else
        {
            super.keyTyped(par1, par2);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        if (Mouse.isButtonDown(0))
        {
            int k = (this.width - this.achievementsPaneWidth) / 2;
            int l = (this.height - this.achievementsPaneHeight) / 2;
            int i1 = k + 8;
            int j1 = l + 17;

            if ((this.isMouseButtonDown == 0 || this.isMouseButtonDown == 1) && par1 >= i1 && par1 < i1 + 224 && par2 >= j1 && par2 < j1 + 155)
            {
                if (this.isMouseButtonDown == 0)
                {
                    this.isMouseButtonDown = 1;
                }
                else
                {
                    this.guiMapX -= (double)(par1 - this.mouseX);
                    this.guiMapY -= (double)(par2 - this.mouseY);
                    this.field_74124_q = this.field_74117_m = this.guiMapX;
                    this.field_74123_r = this.field_74115_n = this.guiMapY;
                }

                this.mouseX = par1;
                this.mouseY = par2;
            }

            if (this.field_74124_q < (double)guiMapTop)
            {
                this.field_74124_q = (double)guiMapTop;
            }

            if (this.field_74123_r < (double)guiMapLeft)
            {
                this.field_74123_r = (double)guiMapLeft;
            }

            if (this.field_74124_q >= (double)guiMapBottom)
            {
                this.field_74124_q = (double)(guiMapBottom - 1);
            }

            if (this.field_74123_r >= (double)guiMapRight)
            {
                this.field_74123_r = (double)(guiMapRight - 1);
            }
        }
        else
        {
            this.isMouseButtonDown = 0;
        }

        this.drawDefaultBackground();
        this.genAchievementBackground(par1, par2, par3);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        this.drawTitle();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.field_74117_m = this.guiMapX;
        this.field_74115_n = this.guiMapY;
        double d0 = this.field_74124_q - this.guiMapX;
        double d1 = this.field_74123_r - this.guiMapY;

        if (d0 * d0 + d1 * d1 < 4.0D)
        {
            this.guiMapX += d0;
            this.guiMapY += d1;
        }
        else
        {
            this.guiMapX += d0 * 0.85D;
            this.guiMapY += d1 * 0.85D;
        }
    }

    /**
     * Draws the "Achievements" title at the top of the GUI.
     */
    protected void drawTitle()
    {
        int i = (this.width - this.achievementsPaneWidth) / 2;
        int j = (this.height - this.achievementsPaneHeight) / 2;
        this.fontRenderer.drawString("Quantum Craft Research", i + 15, j + 5, 4210752);
    }

    protected void genAchievementBackground(int par1, int par2, float par3)
    {
        int k = MathHelper.floor_double(this.field_74117_m + (this.guiMapX - this.field_74117_m) * (double)par3);
        int l = MathHelper.floor_double(this.field_74115_n + (this.guiMapY - this.field_74115_n) * (double)par3);

        if (k < guiMapTop)
        {
            k = guiMapTop;
        }

        if (l < guiMapLeft)
        {
            l = guiMapLeft;
        }

        if (k >= guiMapBottom)
        {
            k = guiMapBottom - 1;
        }

        if (l >= guiMapRight)
        {
            l = guiMapRight - 1;
        }

        int i1 = (this.width - this.achievementsPaneWidth) / 2;
        int j1 = (this.height - this.achievementsPaneHeight) / 2;
        int k1 = i1 + 16;
        int l1 = j1 + 17;
        this.zLevel = 0.0F;
        GL11.glDepthFunc(GL11.GL_GEQUAL);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, -200.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        this.mc.renderEngine.bindTexture("/terrain.png");
        int i2 = k + 288 >> 4;
        int j2 = l + 288 >> 4;
        int k2 = (k + 288) % 16;
        int l2 = (l + 288) % 16;
        Random random = new Random();
        int i3;
        int j3;
        int k3;

        for (i3 = 0; i3 * 16 - l2 < 155; ++i3)
        {
            float f1 = 0.6F - (float)(j2 + i3) / 25.0F * 0.3F;
            GL11.glColor4f(f1, f1, f1, 1.0F);

            for (k3 = 0; k3 * 16 - k2 < 224; ++k3)
            {
                random.setSeed((long)(1234 + i2 + k3));
                random.nextInt();
                j3 = random.nextInt(1 + j2 + i3) + (j2 + i3) / 2;
                Icon icon = Block.sand.getIcon(0, 0);

                if (j3 <= 37 && j2 + i3 != 35)
                {
                    if (j3 == 22)
                    {
                        if (random.nextInt(2) == 0)
                        {
                            icon = Block.oreDiamond.getIcon(0, 0);
                        }
                        else
                        {
                            icon = Block.oreRedstone.getIcon(0, 0);
                        }
                    }
                    else if (j3 == 10)
                    {
                        icon = Block.oreIron.getIcon(0, 0);
                    }
                    else if (j3 == 8)
                    {
                        icon = Block.oreCoal.getIcon(0, 0);
                    }
                    else if (j3 > 4)
                    {
                        icon = Block.stone.getIcon(0, 0);
                    }
                    else if (j3 > 0)
                    {
                        icon = Block.dirt.getIcon(0, 0);
                    }
                }
                else
                {
                    icon = Block.bedrock.getIcon(0, 0);
                }

                this.drawTexturedModelRectFromIcon(k1 + k3 * 16 - k2, l1 + i3 * 16 - l2, icon, 16, 16);
            }
        }
        
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        int l3;
        int i4;
        int j4;

        ResearchItem achievement1 = null;
        RenderItem renderitem = new RenderItem();
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        int l4;
        int i5;

        System.out.println(researchItems.size());
        
        for (k3 = 0; k3 < researchItems.size(); k3++)
        {
            ResearchItem ri = researchItems.get(k3);
            System.out.println("RI: " + ri.riDescription);
            j4 = ri.displayColumn * 24 - k;
            l3 = ri.displayRow * 24 - l;

            if (j4 >= -24 && l3 >= -24 && j4 <= 224 && l3 <= 155)
            {
                float f2;

                f2 = 1.0F;
                GL11.glColor4f(f2, f2, f2, 1.0F);

                this.mc.renderEngine.bindTexture("/achievement/bg.png");
                i5 = k1 + j4;
                l4 = l1 + l3;
                
                this.drawTexturedModalRect(i5 - 2, l4 - 2, 0, 202, 26, 26);

                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_CULL_FACE);
                renderitem.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, ri.theItemStack, i5 + 3, l4 + 3);
                GL11.glDisable(GL11.GL_LIGHTING);

                renderitem.renderWithColor = true;

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                
                if (par1 >= k1 && par2 >= l1 && par1 < k1 + 224 && par2 < l1 + 155 && par1 >= i5 && par1 <= i5 + 22 && par2 >= l4 && par2 <= l4 + 22)
                {
                    achievement1 = ri;
                }
            }
        }

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/achievement/bg.png");
        this.drawTexturedModalRect(i1, j1, 0, 0, this.achievementsPaneWidth, this.achievementsPaneHeight);
        GL11.glPopMatrix();
        this.zLevel = 0.0F;
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        super.drawScreen(par1, par2, par3);
        if (achievement1 != null)
        {
            String s = StatCollector.translateToLocal(achievement1.getName());
            String s1 = achievement1.riDescription;
            j4 = par1 + 12;
            l3 = par2 - 4;

            i5 = Math.max(this.fontRenderer.getStringWidth(s), 120);
            l4 = this.fontRenderer.splitStringWidth(s1, i5);
            l4 += 12;
            this.drawGradientRect(j4 - 3, l3 - 3, j4 + i5 + 3, l3 + l4 + 3 + 12, -1073741824, -1073741824);
            this.fontRenderer.drawSplitString(s1, j4, l3 + 12, i5, -6250336);

            this.fontRenderer.drawStringWithShadow(StatCollector.translateToLocal("achievement.taken"), j4, l3 + l4 + 4, -7302913);

            this.fontRenderer.drawStringWithShadow(s, j4, l3, -1);
        }
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
        RenderHelper.disableStandardItemLighting();
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return true;
    }

}
