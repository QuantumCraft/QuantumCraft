package mods.quantumcraft.research;

import net.minecraft.item.ItemStack;

public class ResearchItem {
    /**
     * Is the column (related to center of achievement gui, in 24 pixels unit) that the achievement will be displayed.
     */
    public final int displayColumn;

    /**
     * Is the row (related to center of achievement gui, in 24 pixels unit) that the achievement will be displayed.
     */
    public final int displayRow;
    /**
     * Holds the description of the achievement, ready to be formatted and/or displayed.
     */
    public final String riDescription;
    public final int index;
    /**
     * Holds the ItemStack that will be used to draw the achievement into the GUI.
     */
    public final ItemStack theItemStack;

    public final String n;

    public final ResearchItem parentAchievement;

    private boolean special = false;

    public void setSpecial(boolean b) {
        special = b;
    }

    public String getName() {
        return n;
    }

    public boolean getSpecial() {
        return special;
    }

    public ResearchItem(String name, String desc, int i, int par3, int par4, ItemStack is, ResearchItem parent) {
        this.index = i;
        this.parentAchievement = parent;
        this.n = name;
        this.riDescription = desc;
        this.displayColumn = par3;
        this.displayRow = par4;
        this.theItemStack = is;
        if (par3 < ResearchItemList.minDisplayColumn) {
            ResearchItemList.minDisplayColumn = par3;
        }

        if (par4 < ResearchItemList.minDisplayRow) {
            ResearchItemList.minDisplayRow = par4;
        }

        if (par3 > ResearchItemList.maxDisplayColumn) {
            ResearchItemList.maxDisplayColumn = par3;
        }

        if (par4 > ResearchItemList.maxDisplayRow) {
            ResearchItemList.maxDisplayRow = par4;
        }
    }

}
