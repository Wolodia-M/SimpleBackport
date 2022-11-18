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
// Import java classes
import java.util.ArrayList;
// Import minecraft classes
import net.minecraft.item.Item;
// Import forge classes
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
// Import mod classes
import my.wolodiam.simplebackport.mc1_20.items.signs.*;
import my.wolodiam.simplebackport.mc1_16.items.*;
import my.wolodiam.simplebackport.utils.*;
import my.wolodiam.simplebackport.utils.registry.data.ItemRegistryType;
import static my.wolodiam.simplebackport.SimpleBackport.instance;

// Class for forge registry
@Mod.EventBusSubscriber(modid = DATA.MODID)
public class ItemRegister {
    /*
     *      1.20
     * oak_hanging_sign
     * spruce_hanging_sign
     * dark_oak_hanging_sign
     * birch_hanging_sign
     * acacia_hanging_sign
     * jungle_hanging_sign
     *
     *      1.16
     * chain
     */
    public static ArrayList<ItemRegistryType> ITEMS = new ArrayList<ItemRegistryType>();
    public static Item OAK_HANGING_SIGN_ITEM;
    public static Item SPRUCE_HANGING_SIGN_ITEM;
    public static Item BIRCH_HANGING_SIGN_ITEM;
    public static Item DARK_OAK_HANGING_SIGN_ITEM;

    /**
     * Get item from ITEMS using it's id
     *
     * @param id => String -> id of item
     * @return Item -> Item with this id
     * @error: return => null
     */
    public static Item get(String id) {
        for (ItemRegistryType data : ITEMS) {
            if (data.id.equals(id)) {
                return data.item;
            }
        }
        return null;
    }

    /**
     * Init of all items of mod, first faze
     */
    public static void init()
    {
        DATA.logger.info("Preinit of items");
        ITEMS.add(new ItemRegistryType(new OakHangingSignItem("oak_hanging_sign"), "oak_hanging_sign"));
        ITEMS.add(new ItemRegistryType(new SpruceHangingSignItem("spruce_hanging_sign"), "spruce_hanging_sign"));
        ITEMS.add(new ItemRegistryType(new BirchHangingSignItem("birch_hanging_sign"), "birch_hanging_sign"));
        ITEMS.add(new ItemRegistryType(new DarkOakHangingSignItem("dark_oak_hanging_sign"), "dark_oak_hanging_sign"));
        ITEMS.add(new ItemRegistryType(new AcaciaHangingSignItem("acacia_hanging_sign"), "acacia_hanging_sign"));
        ITEMS.add(new ItemRegistryType(new JungleHangingSignItem("jungle_hanging_sign"), "jungle_hanging_sign"));
        ITEMS.add(new ItemRegistryType(new ChainItem("chain"), "chain"));
        OAK_HANGING_SIGN_ITEM      = get("oak_hanging_sign");
        SPRUCE_HANGING_SIGN_ITEM   = get("spruce_hanging_sign");
        BIRCH_HANGING_SIGN_ITEM    = get("birch_hanging_sign");
        DARK_OAK_HANGING_SIGN_ITEM = get("dark_oak_hanging_sign");
    }
    /**
     * Register items in Forge registry
     *
     * @param event Internal Forge event
     */
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        DATA.logger.info("Registering items");
        for (ItemRegistryType data : ITEMS) {
            event.getRegistry().registerAll(data.item);
        }
    }

    /**
     * Register item models
     *
     * @param event Internal Forge event
     */
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerItemsRenders(ModelRegistryEvent event) {
        DATA.logger.info("Registering item models");
        instance.proxy.registerItemModels();
    }
}








