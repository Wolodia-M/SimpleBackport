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
package my.wolodiam.simplebackport.api.blocks;
// Import minecraft classes
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
// Import Java classes
import org.jetbrains.annotations.NotNull;

public class BlockWithRotation extends Block {
    public static final PropertyEnum<EnumState> BLOCK_ROTATION = PropertyEnum.create("rotation", EnumState.class);
    public BlockWithRotation(Material mat) {
        super(mat);
        this.setDefaultState(blockState.getBaseState().withProperty(BLOCK_ROTATION, EnumState.NORTH));
    }
    public enum EnumState implements IStringSerializable {
        NORTH("north"),
        SOUTH("south"),
        EAST("east"),
        WEST("west");
        private String name;
        EnumState(String name) {
            this.name = name;
        }
        public @NotNull BlockWithRotation.EnumState fromFacing(@NotNull EnumFacing facing) {
            EnumState st = null;
            switch (facing) {
                case SOUTH:
                    st = NORTH;
                    break;
                case NORTH:
                    st = SOUTH;
                    break;
                case EAST:
                    st = WEST;
                    break;
                case WEST:
                    st = EAST;
                    break;
                case UP:
                    st = NORTH;
                    break;
                case DOWN:
                    st = NORTH;
                    break;
                default:
                    st = NORTH;
                    break;
            }
            return st;
        }
        @Override
        public String getName() {
            return this.name;
        }
        @Override
        public String toString() {
            return this.name;
        }
    }
}







