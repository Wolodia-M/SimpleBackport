/*
    Simple backport of new minecraft feathures to mc 1.12.2
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
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.*;
// Import mod classes
import my.wolodiam.simplebackport.utils.*;
import my.wolodiam.simplebackport.utils.registry.*;

@Mod(modid = DATA.MODID, name = DATA.NAME, version = DATA.VERSION)
public class SimpleBackport
{
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
        DATA.logger.info("Init faze of Simple Backport");
        TileEntityRegister.registerTE();
    }
}

