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
package my.wolodiam.simplebackport;
// Import minecraft forge classes
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
// Import mod classes
import my.wolodiam.simplebackport.utils.*;
import my.wolodiam.simplebackport.utils.proxy.CommonProxy;
import my.wolodiam.simplebackport.utils.registry.*;
import my.wolodiam.simplebackport.mc1_16.commands.LocateBiome;

@Mod(modid = DATA.MODID, name = DATA.NAME, version = DATA.VERSION)
public class SimpleBackport
{
    @SidedProxy(modId=DATA.MODID,clientSide="my.wolodiam.simplebackport.utils.proxy.ClientProxy", serverSide="my.wolodiam.simplebackport.utils.proxy.CommonProxy")
    public static CommonProxy proxy;
    // Instance of mod
    @Mod.Instance
    public static SimpleBackport instance;
    // Preinit faze
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        DATA.logger = event.getModLog();
        DATA.logger.info("Preinit faze of Simple Backport");
        ItemRegister.init();
        BlockRegister.init();
    }
    // Init faze
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        DATA.logger.info("Init faze of SimpleBackport");
        TileEntityRegister.registerTE();
    }
    @Mod.EventHandler
    public void serverInit(FMLServerStartingEvent event)
    {
        DATA.logger.info("Server init faze of SimpleBackport");
        event.registerServerCommand(new LocateBiome());
    }
}

