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
import my.wolodiam.simplebackport.mc1_20.signTE.*;
import net.minecraftforge.fml.client.registry.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.registry.*;
import net.minecraftforge.fml.relauncher.*;
// Import minecraft classes
// Import mod classes
import my.wolodiam.simplebackport.utils.DATA;
import my.wolodiam.simplebackport.api.signs.*;


@Mod.EventBusSubscriber(modid = DATA.MODID)
public class TileEntityRegister {
    public static void registerTE() {
        GameRegistry.registerTileEntity(OakHangingSignTE.class, DATA.MODID + ":oak_hanging_sign_tile_entity");
        GameRegistry.registerTileEntity(SpruceHangingSignTE.class, DATA.MODID + ":spruce_hanging_sign_tile_entity");
        GameRegistry.registerTileEntity(BirchHangingSignTE.class, DATA.MODID + ":birch_hanging_sign_tile_entity");
        GameRegistry.registerTileEntity(DarkOakHangingSignTE.class, DATA.MODID + ":dark_oak_hanging_sign_tile_entity");
        registerModels();
    }
    @SideOnly(Side.CLIENT)
    private static void registerModels()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(OakHangingSignTE.class, new OakHangingSignRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(SpruceHangingSignTE.class, new SpruceHangingSignRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(BirchHangingSignTE.class, new BirchHangingSignRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(DarkOakHangingSignTE.class, new DarkOakHangingSignRenderer());
    }
}
