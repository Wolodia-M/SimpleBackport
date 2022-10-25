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
// Import minecraft classes
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.item.Item;
// Import forge classes
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.model.*;
import net.minecraftforge.event.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.*;
// Import mod classes
import my.wolodiam.simplebackport.utils.*;
import my.wolodiam.simplebackport.mc1_20.items.*;

// Class for forge registry
@Mod.EventBusSubscriber(modid = DATA.MODID)
public class ItemRegister {
    public static Item OAK_HANGING_SIGN_ITEM;
    public static Item SPRUCE_HANGING_SIGN_ITEM;

    /**
     * Init of all items of mod, first faze
     */
    public static void init()
    {
        OAK_HANGING_SIGN_ITEM = new OakHangingSignItem("oak_hanging_sign");
        SPRUCE_HANGING_SIGN_ITEM = new SpruceHangingSignItem("spruce_hanging_sign");
    }

    /**
     * Register model of item with metadata
     *
     * @param item item (Item)
     * @param meta metadata (int)
     */
    private static void registerModel(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    /**
     * Register items in Forge registry
     *
     * @param event Internal Forge event
     */
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(OAK_HANGING_SIGN_ITEM);
        event.getRegistry().registerAll(SPRUCE_HANGING_SIGN_ITEM);
    }

    /**
     * Register item models
     *
     * @param event Internal Forge event
     */
    @SubscribeEvent
    public static void registerItemsRenders(ModelRegistryEvent event)
    {
        registerModel(OAK_HANGING_SIGN_ITEM, 0);
        registerModel(SPRUCE_HANGING_SIGN_ITEM, 0);
    }
}








