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
package my.wolodiam.simplebackport.utils.config;
// Import minecraftforge classes
import net.minecraftforge.common.config.Config;
// Import mod classes
import my.wolodiam.simplebackport.utils.DATA;


@SuppressWarnings("WeakerAccess")
@Config(modid = DATA.MODID, name = "SimpleBackport", category = "")
public class ConfigHandler {
    @Config.Comment("All related to minecraft commands")
    public static Commands commands = new Commands();
    @Config.Comment("All related to blocks")
    public static Blocks blocks = new Blocks();
    public static class Commands {
        @Config.Comment({
                "Search radius for locatebiome, after will be multiplayed by biome size index",
                "Default - 2048"})
        @Config.Name("Locatebiome search radius")
        @Config.RangeInt(min = 0)
        public int locatebiome_radius = 2048;
    }
    public static class Blocks {
        @Config.Comment({
                "Does fletching table has custom crafts or act as in vanila",
                "Default = true"
        })
        @Config.Name("Custom fletching table")
        @Config.RequiresMcRestart()
        public boolean flethcing_table_crafts = true;
    }
}






