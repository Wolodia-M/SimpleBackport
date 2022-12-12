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
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.registry.*;
// Import mod classes
import my.wolodiam.simplebackport.utils.DATA;
import my.wolodiam.simplebackport.mc1_20.signTE.*;
import my.wolodiam.simplebackport.mc1_14.signTE.*;
// Import statics from mod classes
import static my.wolodiam.simplebackport.SimpleBackport.instance;

@Mod.EventBusSubscriber(modid = DATA.MODID)
public class TileEntityRegister {
    public static void registerTE() {
        DATA.logger.info("Registering TileEntityes");
        GameRegistry.registerTileEntity(OakHangingSignTE.class, DATA.MODID + ":oak_hanging_sign_tile_entity");
        GameRegistry.registerTileEntity(SpruceHangingSignTE.class, DATA.MODID + ":spruce_hanging_sign_tile_entity");
        GameRegistry.registerTileEntity(BirchHangingSignTE.class, DATA.MODID + ":birch_hanging_sign_tile_entity");
        GameRegistry.registerTileEntity(DarkOakHangingSignTE.class, DATA.MODID + ":dark_oak_hanging_sign_tile_entity");
        GameRegistry.registerTileEntity(AcaciaHangingSignTE.class, DATA.MODID + ":acacia_hanging_sign_tile_entity");
        GameRegistry.registerTileEntity(JungleHangingSignTE.class, DATA.MODID + ":jungle_hanging_sign_tile_entity");
        GameRegistry.registerTileEntity(OakSignTE.class, DATA.MODID + ":oak_sign_tile_entity");
        registerTEInternall(AcaciaSignTE.class, "acacia_sign_tile_entity");
        registerTEInternall(BirchSignTE.class, "birch_sign_tile_entity");
        registerTEInternall(DarkOakSignTE.class, "dark_oak_sign_tile_entity");
        registerTEInternall(JungleSignTE.class, "jungle_sign_tile_entity");
        registerTEInternall(SpruceSignTE.class, "spruce_sign_tile_entity");
        registerModels();
    }
    private static void registerTEInternall(Class<? extends net.minecraft.tileentity.TileEntity> te, String id) {
        GameRegistry.registerTileEntity(te, DATA.MODID + ":" + id);
    }
    private static void registerModels() {
        DATA.logger.info("Registering render for TileEntityes");
        instance.proxy.registerTEModels();
    }
}
