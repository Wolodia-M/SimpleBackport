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
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;

public class BlockWithRotation extends Block {
    public static final PropertyInteger BLOCK_ROTATION = PropertyInteger.create("rotation", 0, 3);
    public BlockWithRotation(Material mat) {
        super(mat);
        this.setDefaultState(blockState.getBaseState().withProperty(BLOCK_ROTATION, 0));
    }
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(BLOCK_ROTATION, Integer.valueOf(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(BLOCK_ROTATION)).intValue();
    }

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(BLOCK_ROTATION, Integer.valueOf(rot.rotate(state.getValue(BLOCK_ROTATION), 4)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withProperty(BLOCK_ROTATION, Integer.valueOf(mirrorIn.mirrorRotation(state.getValue(BLOCK_ROTATION), 2)));
    }
}







