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
// Import minecraft forge classes
import my.wolodiam.simplebackport.utils.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
// Import minecraft classes
import net.minecraft.block.Block;
import net.minecraft.item.Item;
// Import mod classes
import my.wolodiam.simplebackport.mc1_20.blocks.signs.*;

@Mod.EventBusSubscriber(modid = DATA.MODID)
public class BlockRegister {
    public static Block OAK_HANGING_SIGN_TOP_FULL;
    public static Block OAK_HANGING_SIGN_SIDE;
    public static Block SPRUCE_HANGING_SIGN_TOP_FULL;
    public static Block SPRUCE_HANGING_SIGN_SIDE;
    public static Block BIRCH_HANGING_SIGN_TOP_FULL;
    public static Block BIRCH_HANGING_SIGN_SIDE;

    public static void init() {
        OAK_HANGING_SIGN_TOP_FULL    = new OakHangingSignTopFullBlock("oak_hanging_sign_top_full");
        OAK_HANGING_SIGN_SIDE        = new OakHangingSignSideBlock("oak_hanging_sign_side");
        SPRUCE_HANGING_SIGN_TOP_FULL = new SpruceHangingSignTopFullBlock("spruce_hanging_sign_top_full");
        SPRUCE_HANGING_SIGN_SIDE     = new SpruceHangingSignSideBlock("spruce_hanging_sign_side");
        BIRCH_HANGING_SIGN_TOP_FULL  = new BirchHangingSignTopFullBlock("birch_hanging_sign_top_full");
        BIRCH_HANGING_SIGN_SIDE      = new BirchHangingSignSideBlock("birch_hanging_sign_side");
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(OAK_HANGING_SIGN_TOP_FULL);
        event.getRegistry().registerAll(OAK_HANGING_SIGN_SIDE);
        event.getRegistry().registerAll(SPRUCE_HANGING_SIGN_TOP_FULL);
        event.getRegistry().registerAll(SPRUCE_HANGING_SIGN_SIDE);
        event.getRegistry().registerAll(BIRCH_HANGING_SIGN_TOP_FULL);
        event.getRegistry().registerAll(BIRCH_HANGING_SIGN_SIDE);
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {

    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {

    }
}
