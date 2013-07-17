package mods.quantumcraft.core;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import mods.quantumcraft.blocks.BlockOreQuantonium;
import mods.quantumcraft.blocks.BlockQDeenergizer;
import mods.quantumcraft.blocks.BlockQEInjector;
import mods.quantumcraft.items.ItemBase;
import mods.quantumcraft.items.ItemLocationCard;
import mods.quantumcraft.items.ItemMultiTool;
import mods.quantumcraft.items.ItemResearchBook;
import mods.quantumcraft.machine.TileQDeenergizer;
import mods.quantumcraft.machine.TileQEInjector;
import net.minecraft.block.BlockOre;
import net.minecraft.item.Item;

public class Loader {

    /* ITEMS */
    public static ItemBase ItemIngotPlutonium;
    public static ItemBase ItemCrystalQuantonium;
    public static ItemBase ItemRawQuantonium;
    public static ItemMultiTool ItemMultiTool;
    public static ItemResearchBook ItemResearchBook;
    public static ItemBase ItemDepletedCrystal;
    public static ItemLocationCard ItemLocationCard;
    /* BLOCKS */
    public static BlockOre OrePlutonium;
    public static BlockOreQuantonium OreQuantonium;
    /* MACHINE BLOCKS */
    public static BlockQDeenergizer BlockQDeenergizer;
    public static BlockQEInjector BlockQEInjector;
    /* CREATIVE TABS */
    public static TabQuantumCraft tabQuantumCraft;

    public static void initAll() {
        initTabs();
        initItems();
        initBlocks();
        initRenderers();
        initWGen();
        initTEs();
        CraftingManager.addCrafting();
        CraftingManager.addSmelting();
        CraftingManager.addQDE();
    }

    public static void initTabs() {
        tabQuantumCraft = new TabQuantumCraft();
    }

        public static void initItems() {
        ItemIngotPlutonium = (ItemBase) new ItemBase(Config.ItemPlutoniumIngotID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemPlutoniumIngot)
                .func_111206_d(Config.NameItemPlutoniumIngot);
        LanguageRegistry.addName(ItemIngotPlutonium, "Plutonium Ingot");

        ItemCrystalQuantonium = (ItemBase) new ItemBase(Config.ItemCrystallizedQuantoniumID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemCrystalQuantonium)
                .func_111206_d(Config.NameItemCrystalQuantonium);
        LanguageRegistry.addName(ItemCrystalQuantonium, "Crystallized Quantonium");

        ItemRawQuantonium = (ItemBase) new ItemBase(Config.ItemRawQuantoniumID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemRawQuantonium)
                .func_111206_d(Config.NameItemRawQuantonium);
        LanguageRegistry.addName(ItemRawQuantonium, "Raw Quantonium");

        ItemMultiTool = (ItemMultiTool) new ItemMultiTool(Config.ItemMultiToolID).setUnlocalizedName(Config.NameItemMultiTool)
                .setCreativeTab(tabQuantumCraft)
                .func_111206_d(Config.NameItemMultiTool);
        LanguageRegistry.addName(ItemMultiTool, "Multi Tool");

        ItemDepletedCrystal = (ItemBase) new ItemBase(Config.ItemDepletedCrystalID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemDepletedCrystal)
                .func_111206_d(Config.NameItemDepletedCrystal);
        LanguageRegistry.addName(ItemDepletedCrystal, "Depleted Crystal");

        ItemResearchBook = (ItemResearchBook) new ItemResearchBook(Config.ItemResearchBookID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemResearchBook)
                .func_111206_d(Config.NameItemResearchBook);
        LanguageRegistry.addName(ItemResearchBook, "Research Book");

        ItemLocationCard = (ItemLocationCard) new ItemLocationCard(Config.ItemLocationCardID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemLocationCard)
                .func_111206_d(Config.NameItemLocationCard);
        LanguageRegistry.addName(ItemLocationCard, "Location Card");

    }

    public static void initBlocks() {
        OrePlutonium = (BlockOre) new BlockOre(Config.OrePlutoniumID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameOrePlutonium)
                .func_111022_d(Config.NameOrePlutonium).setHardness(2.5F);
        LanguageRegistry.addName(OrePlutonium, "Plutonium Ore");
        GameRegistry.registerBlock(OrePlutonium, Config.NameOrePlutonium);

        OreQuantonium = (BlockOreQuantonium) new BlockOreQuantonium(Config.OreQuantoniumID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameOreQuantonium)
                .func_111022_d(Config.NameOreQuantonium);
        LanguageRegistry.addName(OreQuantonium, "Quantonium Ore");
        GameRegistry.registerBlock(OreQuantonium, Config.NameOreQuantonium);

        BlockQDeenergizer = (BlockQDeenergizer) new BlockQDeenergizer(Config.BlockQDEID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameBlockQDE)
                .func_111022_d(Config.NameBlockQDE);
        LanguageRegistry.addName(BlockQDeenergizer, "Quantum De-Energizer");
        GameRegistry.registerBlock(BlockQDeenergizer, Config.NameBlockQDE);

        BlockQEInjector = (BlockQEInjector) new BlockQEInjector(Config.BlockQEIID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameBlockQEI)
                .func_111022_d(Config.NameBlockQEI);
        LanguageRegistry.addName(BlockQEInjector, "Quantum Energy Injector");
        GameRegistry.registerBlock(BlockQEInjector, Config.NameBlockQEI);
    }

    public static void initRenderers() {

    }

    public static void initWGen() {

    }

    public static void initTEs() {
        GameRegistry.registerTileEntity(TileQDeenergizer.class, "QDeenergizerTE");
        GameRegistry.registerTileEntity(TileQEInjector.class, "QEInjectorTE");
    }

}
