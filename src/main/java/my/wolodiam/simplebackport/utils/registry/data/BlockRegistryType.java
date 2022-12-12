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
package my.wolodiam.simplebackport.utils.registry.data;
// Import minecraft classes
import net.minecraft.block.Block;

public class BlockRegistryType {
    /**
     * Construct new BlockRegistryType
     *
     * @param block => Block -> block
     * @param id => String -> block id
     */
    public BlockRegistryType(Block block, String id) {
        this.block = block;
        this.id = id;
        this.hasBlockModel = false;
    }
    /**
     * Construct new BlockRegistryType
     *
     * @param block => Block -> block
     * @param id => String -> block id
     * @param hasBlockModel => boolean -> true, if need to have block model, not tile entity custom renderer
     */
    public BlockRegistryType(Block block, String id, boolean hasBlockModel) {
        this.block = block;
        this.id = id;
        this.hasBlockModel = hasBlockModel;
    }
    public Block block;
    public String id;
    public boolean hasBlockModel;
}
