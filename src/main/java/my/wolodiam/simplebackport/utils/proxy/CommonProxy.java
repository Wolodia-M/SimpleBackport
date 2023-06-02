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
package my.wolodiam.simplebackport.utils.proxy;
// Import minecraft classes
import net.minecraft.block.Block;
// Import minecraftforge classes
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
// Import Java classes
import java.io.File;
// Import mod classes
import my.wolodiam.simplebackport.utils.config.ConfigHandler;

public class CommonProxy {
    public static ConfigHandler config;
    public void preInit(FMLPreInitializationEvent event) {

    }
    public void Init() {

    }
    public void postInit() {

    }
    public void registerTEModels(){}
    public void registerItemModel(Item item, int meta, String variant){}
    public void registerItemModel(Item item) {}
    public void registerBlockModel(Block block, int meta){}
}
