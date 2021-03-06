package quantumcraft.core;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import quantumcraft.blocks.*;
import quantumcraft.items.*;
import quantumcraft.items.tools.ItemQuantumAxe;
import quantumcraft.items.tools.ItemQuantumPick;
import quantumcraft.items.tools.ItemQuantumShovel;
import quantumcraft.items.tools.ItemQuantumSword;
import quantumcraft.render.RenderFibreWire;
import quantumcraft.render.RenderOre;
import quantumcraft.tile.*;

public class Loader {

    /* ITEMS */
    public static ItemBase ItemIngotUnbioxenium;
    public static ItemBase ItemCrystalQuantonium;
    public static ItemBase ItemRawQuantonium;
    public static ItemMultiTool ItemMultiTool;
    public static ItemBase ItemDepletedCrystal;
    public static ItemLocationCard ItemLocationCard;
    public static ItemLocationCardBlank ItemLocationCardBlank;
    public static ItemPortableQuantumCapacitor ItemPortableQuantumCapacitor;
    public static ItemInfinitePower ItemInfinitePower;
    public static ItemUpgrade ItemUpgrade;
    public static ItemQuantumSword ItemQuantumSword;
    public static ItemQuantumAxe ItemQuantumAxe;
    public static ItemQuantumPick ItemQuantumPick;
    public static ItemQuantumShovel ItemQuantumShovel;
    public static ItemBase ItemHyperConductor;
    public static ItemBase ItemQuantumPlating;
    public static ItemBase ItemQuantumConverter;
    /* BLOCKS */
    public static BlockOre OreUnbioxenium;
    public static BlockOreQuantonium OreQuantonium;
    public static Block BlockMachineCasing;
    /* MACHINE BLOCKS */
    public static BlockQuantumDeenergizer BlockQuantumDeenergizer;
    public static BlockQuantumEnergyInjector BlockQuantumEnergyInjector;
    public static BlockQuantumEnergySucker BlockQuantumEnergySucker;
    public static BlockQuantumDematerializer BlockQuantumDematerializer;
    public static BlockQuantumEnergyExtractor BlockQuantumEnergyExtractor;
    public static BlockQuantumCapacitor BlockQuantumCapacitor;
    public static BlockIONForge BlockIONForge;
    public static BlockIONTunneler BlockIONTunneler;
    public static BlockIONScanner BlockIONScanner;
    public static BlockIONHarvester BlockIONHarvester;
    public static BlockQuantumInterdimensionalGenerator BlockQuantumInterdimensionalGenerator;
    public static BlockQuantumCapacitor[] capacitors = new BlockQuantumCapacitor[5];
    /* OTHER BLOCKS */
    public static BlockQuantumFibreWire BlockQuantumFibreWire;
    /* CREATIVE TABS */
    public static TabQuantumCraft tabQuantumCraft;

    public static void initAll() {
        initTabs();
        initItems();
        initBlocks();
        initRenderers();
        initWGen();
        initTEs();
        initOreDict();
        CraftingManager.addCrafting();
        CraftingManager.addSmelting();
        CraftingManager.addQDE();
    }

    public static void initOreDict() {
        OreDictionary.registerOre(Config.NameOreQuantonium, new ItemStack(OreQuantonium));
        OreDictionary.registerOre(Config.NameOreUnbioxenium, new ItemStack(OreUnbioxenium));
    }

    public static void initTabs() {
        tabQuantumCraft = new TabQuantumCraft();
    }

    public static void initItems() {
        ItemIngotUnbioxenium = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemUnbioxeniumIngot)
                .setTextureName(Config.getTextureName(Config.NameItemUnbioxeniumIngot));
        GameRegistry.registerItem(ItemIngotUnbioxenium, Config.NameItemUnbioxeniumIngot);

        ItemCrystalQuantonium = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemCrystalQuantonium)
                .setTextureName(Config.getTextureName(Config.NameItemCrystalQuantonium));
        GameRegistry.registerItem(ItemCrystalQuantonium, Config.NameItemCrystalQuantonium);

        ItemRawQuantonium = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemRawQuantonium)
                .setTextureName(Config.getTextureName(Config.NameItemRawQuantonium));
        GameRegistry.registerItem(ItemRawQuantonium, Config.NameItemRawQuantonium);

        ItemMultiTool = (ItemMultiTool) new ItemMultiTool().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemMultiTool)
                .setTextureName(Config.getTextureName(Config.NameItemMultiTool));
        GameRegistry.registerItem(ItemMultiTool, Config.NameItemMultiTool);

        ItemDepletedCrystal = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemDepletedCrystal)
                .setTextureName(Config.getTextureName(Config.NameItemDepletedCrystal));
        GameRegistry.registerItem(ItemDepletedCrystal, Config.NameItemDepletedCrystal);

        ItemUpgrade = (ItemUpgrade) new ItemUpgrade().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemUpgrade_).setTextureName("USELESS");
        GameRegistry.registerItem(ItemUpgrade, Config.NameItemUpgrade_);

        if (Config.beta.getBoolean(false)) {
            ItemLocationCard = (ItemLocationCard) new ItemLocationCard().setUnlocalizedName(Config.NameItemLocationCard)
                    .setTextureName(Config.getTextureName(Config.NameItemLocationCard));
            GameRegistry.registerItem(ItemLocationCard, Config.NameItemLocationCard);

            ItemLocationCardBlank = (ItemLocationCardBlank) new ItemLocationCardBlank()
                    .setUnlocalizedName(Config.NameItemLocationCardBlank).setCreativeTab(tabQuantumCraft)
                    .setTextureName(Config.getTextureName(Config.NameItemLocationCardBlank));
            GameRegistry.registerItem(ItemLocationCardBlank, Config.NameItemLocationCardBlank);
        }

        ItemPortableQuantumCapacitor =
                (ItemPortableQuantumCapacitor) new ItemPortableQuantumCapacitor().setCreativeTab(tabQuantumCraft)
                        .setUnlocalizedName(Config.NameItemPortableQCapacitor)
                        .setTextureName(Config.getTextureName(Config.NameItemPortableQCapacitor));
        GameRegistry.registerItem(ItemPortableQuantumCapacitor, Config.NameItemPortableQCapacitor);

        ItemInfinitePower = (ItemInfinitePower) new ItemInfinitePower().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemInfinitePower);
        GameRegistry.registerItem(ItemInfinitePower, Config.NameItemInfinitePower);

        ItemQuantumSword = (ItemQuantumSword) new ItemQuantumSword().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQuantumSword)
                .setTextureName(Config.getTextureName(Config.NameItemQuantumSword));
        GameRegistry.registerItem(ItemQuantumSword, Config.NameItemQuantumSword);

        ItemQuantumAxe = (ItemQuantumAxe) new ItemQuantumAxe().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQuantumAxe)
                .setTextureName(Config.getTextureName(Config.NameItemQuantumAxe));
        GameRegistry.registerItem(ItemQuantumAxe, Config.NameItemQuantumAxe);

        ItemQuantumPick = (ItemQuantumPick) new ItemQuantumPick().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQuantumPick)
                .setTextureName(Config.getTextureName(Config.NameItemQuantumPick));
        GameRegistry.registerItem(ItemQuantumPick, Config.NameItemQuantumPick);

        ItemQuantumShovel = (ItemQuantumShovel) new ItemQuantumShovel().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQuantumShovel)
                .setTextureName(Config.getTextureName(Config.NameItemQuantumShovel));
        GameRegistry.registerItem(ItemQuantumShovel, Config.NameItemQuantumShovel);

        ItemHyperConductor = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemHyperConductor)
                .setTextureName(Config.getTextureName(Config.NameItemHyperConductor));
        GameRegistry.registerItem(ItemHyperConductor, Config.NameItemHyperConductor);

        ItemQuantumPlating = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQuantumPlating)
                .setTextureName(Config.getTextureName(Config.NameItemQuantumPlating));
        GameRegistry.registerItem(ItemQuantumPlating, Config.NameItemQuantumPlating);

        ItemQuantumConverter = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQuantumConverter)
                .setTextureName(Config.getTextureName(Config.NameItemQuantumConverter));
        GameRegistry.registerItem(ItemQuantumConverter, Config.NameItemQuantumConverter);
    }

    public static void initBlocks() {
        OreUnbioxenium =
                (BlockOre) new BlockOre().setCreativeTab(tabQuantumCraft).setBlockName(Config.NameOreUnbioxenium)
                        .setBlockTextureName(Config.getTextureName(Config.NameOreUnbioxenium)).setHardness(2.5F);
        GameRegistry.registerBlock(OreUnbioxenium, Config.NameOreUnbioxenium);

        OreQuantonium = (BlockOreQuantonium) new BlockOreQuantonium().setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameOreQuantonium)
                .setBlockTextureName(Config.getTextureName(Config.NameOreQuantonium));
        GameRegistry.registerBlock(OreQuantonium, Config.NameOreQuantonium);

        BlockQuantumFibreWire = (BlockQuantumFibreWire) new BlockQuantumFibreWire().setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockQuantumFibreWire);
        GameRegistry.registerBlock(BlockQuantumFibreWire, Config.NameBlockQuantumFibreWire);

        BlockQuantumDeenergizer =
                (BlockQuantumDeenergizer) new BlockQuantumDeenergizer().setCreativeTab(tabQuantumCraft)
                        .setBlockName(Config.NameBlockQuantumDeenergizer);
        GameRegistry.registerBlock(BlockQuantumDeenergizer, Config.NameBlockQuantumDeenergizer);

        BlockQuantumDematerializer =
                (BlockQuantumDematerializer) new BlockQuantumDematerializer().setCreativeTab(tabQuantumCraft)
                        .setBlockName(Config.NameBlockQuantumDematerializer);
        GameRegistry.registerBlock(BlockQuantumDematerializer, Config.NameBlockQuantumDematerializer);

        BlockQuantumEnergyInjector =
                (BlockQuantumEnergyInjector) new BlockQuantumEnergyInjector().setCreativeTab(tabQuantumCraft)
                        .setBlockName(Config.NameBlockQuantumEnergyInjector);
        GameRegistry.registerBlock(BlockQuantumEnergyInjector, Config.NameBlockQuantumEnergyInjector);

        BlockIONForge = (BlockIONForge) new BlockIONForge().setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockIONForge);
        GameRegistry.registerBlock(BlockIONForge, Config.NameBlockIONForge);

        BlockIONTunneler = (BlockIONTunneler) new BlockIONTunneler().setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockIONTunneler);
        GameRegistry.registerBlock(BlockIONTunneler, Config.NameBlockIONTunneler);

        BlockIONScanner = (BlockIONScanner) new BlockIONScanner().setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockIONScanner);
        GameRegistry.registerBlock(BlockIONScanner, Config.NameBlockIONScanner);


        BlockIONHarvester = (BlockIONHarvester) new BlockIONHarvester().setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockIONHarvester);
        GameRegistry.registerBlock(BlockIONHarvester, Config.NameBlockIONHarvester);

        BlockQuantumInterdimensionalGenerator =
                (BlockQuantumInterdimensionalGenerator) new BlockQuantumInterdimensionalGenerator()
                        .setCreativeTab(tabQuantumCraft).setBlockName(Config.NameBlockQuantumInterdimensionalGenerator);
        GameRegistry
                .registerBlock(BlockQuantumInterdimensionalGenerator, Config.NameBlockQuantumInterdimensionalGenerator);

        for (int i = 1; i <= 5; i++) {
            BlockQuantumCapacitor = (BlockQuantumCapacitor) new BlockQuantumCapacitor().setCreativeTab(tabQuantumCraft)
                    .setBlockName(Config.NameBlockQuantumCapacitor + i);
            BlockQuantumCapacitor.setMaxEnergyMultiplier(i);
            capacitors[i - 1] = BlockQuantumCapacitor;
            GameRegistry.registerBlock(BlockQuantumCapacitor, Config.NameBlockQuantumCapacitor + i);
        }
        if (Config.beta.getBoolean(false)) {
            BlockQuantumEnergySucker =
                    (BlockQuantumEnergySucker) new BlockQuantumEnergySucker().setCreativeTab(tabQuantumCraft)
                            .setBlockName(Config.NameBlockQuantumEnergySucker);
            GameRegistry.registerBlock(BlockQuantumEnergySucker, Config.NameBlockQuantumEnergySucker);
        }

        BlockQuantumEnergyExtractor =
                (BlockQuantumEnergyExtractor) new BlockQuantumEnergyExtractor().setCreativeTab(tabQuantumCraft)
                        .setBlockName(Config.NameBlockQuantumEnergyExtractor);
        GameRegistry.registerBlock(BlockQuantumEnergyExtractor, Config.NameBlockQuantumEnergyExtractor);

        BlockMachineCasing =
                new BasicBlock().setCreativeTab(tabQuantumCraft).setBlockName(Config.NameBlockMachineCasing)
                        .setBlockTextureName(Config.getTextureName(Config.NameBlockMachineCasing));
        GameRegistry.registerBlock(BlockMachineCasing, Config.NameBlockMachineCasing);

    }

    public static void initRenderers() {
        RenderingRegistry.registerBlockHandler(RenderOre.instance().getRenderId(), RenderOre.instance());
        RenderingRegistry.registerBlockHandler(RenderFibreWire.instance().getRenderId(), RenderFibreWire.instance());
    }

    public static void initWGen() {
        GameRegistry.registerWorldGenerator(new WorldGen(), 1);
    }

    public static void initTEs() {
        String te = "TileEntity";
        GameRegistry.registerTileEntity(TileQuantumDeenergizer.class, Config.NameBlockQuantumDeenergizer + te);
        GameRegistry.registerTileEntity(TileQuantumEnergyInjector.class, Config.NameBlockQuantumEnergyInjector + te);
        GameRegistry.registerTileEntity(TileQuantumEnergySucker.class, Config.NameBlockQuantumEnergySucker + te);
        GameRegistry.registerTileEntity(TileQuantumDematerializer.class, Config.NameBlockQuantumDematerializer + te);
        GameRegistry.registerTileEntity(TileQuantumEnergyExtractor.class, Config.NameBlockQuantumEnergyExtractor + te);
        GameRegistry.registerTileEntity(TileIONForge.class, Config.NameBlockIONForge + te);
        GameRegistry.registerTileEntity(TileIONHarvester.class, Config.NameBlockIONHarvester + te);
        GameRegistry.registerTileEntity(TileIONScanner.class, Config.NameBlockIONScanner + te);
        GameRegistry.registerTileEntity(TileIONTunneler.class, Config.NameBlockIONTunneler + te);
        GameRegistry.registerTileEntity(TileQuantumCapacitor.class, Config.NameBlockQuantumCapacitor + te);
        GameRegistry.registerTileEntity(TileQuantumInterdimensionalGenerator.class,
                Config.NameBlockQuantumInterdimensionalGenerator + te);
    }

    public static class ToolMaterials {

        //The following values are subject to change.
        public static Item.ToolMaterial QUANTUMTOOL = EnumHelper.addToolMaterial("QUANTUMTOOL", 3, 0, 7.0F, 2, 30);
        //the 3rd argument (0) is not used...
    }

    public static class IconLoader {

        public static IIcon quantonium_ore;
        public static IIcon quantonium_ore_base;
        public static IIcon quantonium_ore_full;

        public static void loadAll(IIconRegister i) {
            QuantumCraft.logHandler.debugPrint(Config.getTextureName(Config.NameTextureQuantumOre));
            quantonium_ore = i.registerIcon(Config.getTextureName(Config.NameTextureQuantumOre));
            QuantumCraft.logHandler.debugPrint(Config.getTextureName(Config.NameTextureQuantumOreBase));
            quantonium_ore_base = i.registerIcon(Config.getTextureName(Config.NameTextureQuantumOreBase));
            QuantumCraft.logHandler.debugPrint(Config.getTextureName(Config.NameTextureQuantumOreFull));
            quantonium_ore_full = i.registerIcon(Config.getTextureName(Config.NameTextureQuantumOreFull));
        }
    }

}
