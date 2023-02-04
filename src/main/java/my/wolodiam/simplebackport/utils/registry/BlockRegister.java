/*
    Simple backport of new minecraft features to mc 1.12.2
    Copyright (C) 2022 WolodiaM

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

// Java package
package my.wolodiam.simplebackport.utils.registry;
// Import Java classes
import java.util.ArrayList;
// Import minecraft forge classes
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
// Import minecraft classes
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
// Import mod classes
import my.wolodiam.simplebackport.utils.*;
import my.wolodiam.simplebackport.utils.registry.data.BlockRegistryType;
import my.wolodiam.simplebackport.mc1_16.blocks.*;
import my.wolodiam.simplebackport.mc1_20.blocks.signs.*;
import my.wolodiam.simplebackport.mc1_14.blocks.signs.*;
import my.wolodiam.simplebackport.mc1_14.blocks.crafters.*;
// Import statics from mod classes
import static  my.wolodiam.simplebackport.SimpleBackport.instance;

@Mod.EventBusSubscriber(modid = DATA.MODID)
public class BlockRegister {
    /*
     *      1.20
     * // Signs
     * oak_hanging_sign_top_full
     * oak_hanging_sign_side
     * dark_oak_hanging_sign_top_full
     * dark_oak_hanging_sign_side
     * spruce_hanging_sign_top_full
     * spruce_hanging_sign_side
     * birch_hanging_sign_top_full
     * birch_hanging_sign_side
     * acacia_hanging_sign_top_full
     * acacia_hanging_sign_side
     * jungle_hanging_sign_top_full
     * jungle_hanging_sign_side
     *
     *      1.16
     * // Misc
     * chain
     *
     *      1.14
     * // Signs
     * acacia_sign_side
     * acacia_sign_top
     * birch_sign_side
     * birch_sign_top
     * dark_oak_sign_side
     * dark_oak_sign_top
     * jungle_sign_side
     * jungle_sign_top
     * oak_sign_side
     * oak_sign_top
     * spruce_sign_side
     * spruce_sign_top
     * // Crafters
     * fletching_table
     */
    public static ArrayList<BlockRegistryType> BLOCKS = new ArrayList<BlockRegistryType>();
    public static Block OAK_HANGING_SIGN_TOP_FULL;
    public static Block OAK_HANGING_SIGN_SIDE;
    public static Block SPRUCE_HANGING_SIGN_TOP_FULL;
    public static Block SPRUCE_HANGING_SIGN_SIDE;
    public static Block BIRCH_HANGING_SIGN_TOP_FULL;
    public static Block BIRCH_HANGING_SIGN_SIDE;
    public static Block DARK_OAK_HANGING_SIGN_TOP_FULL;
    public static Block DARK_OAK_HANGING_SIGN_SIDE;

    public static Block get(String id) {
        for (BlockRegistryType data : BLOCKS) {
            if (data.id.equals(id)) {
                return data.block;
            }
        }
        return null;
    }

    public static void init() {
        DATA.logger.info("Preinit of blocks");
        BLOCKS.add(new BlockRegistryType(new OakHangingSignTopFullBlock("oak_hanging_sign_top_full"), "oak_hanging_sign_top_full"));
        BLOCKS.add(new BlockRegistryType(new OakHangingSignSideBlock("oak_hanging_sign_side"), "oak_hanging_sign_side"));
        BLOCKS.add(new BlockRegistryType(new SpruceHangingSignTopFullBlock("spruce_hanging_sign_top_full"), "spruce_hanging_sign_top_full"));
        BLOCKS.add(new BlockRegistryType(new SpruceHangingSignSideBlock("spruce_hanging_sign_side"), "spruce_hanging_sign_side"));
        BLOCKS.add(new BlockRegistryType(new BirchHangingSignTopFullBlock("birch_hanging_sign_top_full"), "birch_hanging_sign_top_full"));
        BLOCKS.add(new BlockRegistryType(new BirchHangingSignSideBlock("birch_hanging_sign_side"), "birch_hanging_sign_side"));
        BLOCKS.add(new BlockRegistryType(new DarkOakHangingSignTopFullBlock("dark_oak__hanging_sign_top_full"), "dark_oak__hanging_sign_top_full"));
        BLOCKS.add(new BlockRegistryType(new DarkOakHangingSignSideBlock("dark_oak_hanging_sign_side"), "dark_oak_hanging_sign_side"));
        BLOCKS.add(new BlockRegistryType(new AcaciaHangingSignTopFullBlock("acacia_hanging_sign_top_full"), "acacia_hanging_sign_top_full"));
        BLOCKS.add(new BlockRegistryType(new AcaciaHangingSignSideBlock("acacia_hanging_sign_side"), "acacia_hanging_sign_side"));
        BLOCKS.add(new BlockRegistryType(new JungleHangingSignTopFullBlock("jungle_hanging_sign_top_full"), "jungle_hanging_sign_top_full"));
        BLOCKS.add(new BlockRegistryType(new JungleHangingSignSideBlock("jungle_hanging_sign_side"), "jungle_hanging_sign_side"));
        BLOCKS.add(new BlockRegistryType(new ChainBlock("chain_block"), "chain_block", true));
        BLOCKS.add(new BlockRegistryType(new OakSignSide("oak_sign_side"), "oak_sign_side"));
        BLOCKS.add(new BlockRegistryType(new OakSignTop("oak_sign_top"), "oak_sign_top"));
        BLOCKS.add(new BlockRegistryType(new SpruceSignSide("spruce_sign_side"), "spruce_sign_side"));
        BLOCKS.add(new BlockRegistryType(new SpruceSignTop("spruce_sign_top"), "spruce_sign_top"));
        BLOCKS.add(new BlockRegistryType(new AcaciaSignSide("acacia_sign_side"), "acacia_sign_side"));
        BLOCKS.add(new BlockRegistryType(new AcaciaSignTop("acacia_sign_top"), "acacia_sign_top"));
        BLOCKS.add(new BlockRegistryType(new BirchSignSide("birch_sign_side"), "birch_sign_side"));
        BLOCKS.add(new BlockRegistryType(new BirchSignTop("birch_sign_top"), "birch_sign_top"));
        BLOCKS.add(new BlockRegistryType(new DarkOakSignSide("dark_oak_sign_side"), "dark_oak_sign_side"));
        BLOCKS.add(new BlockRegistryType(new DarkOakSignTop("dark_oak_sign_top"), "dark_oak_sign_top"));
        BLOCKS.add(new BlockRegistryType(new JungleSignSide("jungle_sign_side"), "jungle_sign_side"));
        BLOCKS.add(new BlockRegistryType(new JungleSignTop("jungle_sign_top"), "jungle_sign_top"));
        BLOCKS.add(new BlockRegistryType(new FletchingTable("fletching_table"), "fletching_table"));
        OAK_HANGING_SIGN_TOP_FULL      = get("oak_hanging_sign_top_full");
        OAK_HANGING_SIGN_SIDE          = get("oak_hanging_sign_side");
        SPRUCE_HANGING_SIGN_TOP_FULL   = get("spruce_hanging_sign_top_full");
        SPRUCE_HANGING_SIGN_SIDE       = get("spruce_hanging_sign_side");
        BIRCH_HANGING_SIGN_TOP_FULL    = get("birch_hanging_sign_top_full");
        BIRCH_HANGING_SIGN_SIDE        = get("birch_hanging_sign_side");
        DARK_OAK_HANGING_SIGN_TOP_FULL = get("dark_oak__hanging_sign_top_full");
        DARK_OAK_HANGING_SIGN_SIDE     = get("dark_oak_hanging_sign_side");
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        DATA.logger.info("Registering blocks");
        for (BlockRegistryType data : BLOCKS) {
            event.getRegistry().registerAll(data.block);
        }
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {

    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {

    }
    @SideOnly(Side.CLIENT)
    private static void clientRegister(int action) {
        switch (action) {
            case 1:
                DATA.logger.info("Registering blocks models");
                for (BlockRegistryType data : BLOCKS) {
                    if (data.hasBlockModel == true) {
                        if (data.id.equals("chain_block")) {
                            instance.proxy.registerBlockModel(data.block, 0);
                        }
                    }
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
    }
}
