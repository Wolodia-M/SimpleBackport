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
package my.wolodiam.simplebackport.mc1_16.blocks;
// Import minecraft classes
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.block.BlockSlab;
// Import Java classes
import java.util.Random;
// Import mod classes
import my.wolodiam.simplebackport.utils.registry.ItemRegister;
import my.wolodiam.simplebackport.api.blocks.BlockWithAxises;

public class ChainBlock extends BlockWithAxises {
    protected static final AxisAlignedBB CHAIN_AABB_Y = new AxisAlignedBB(0.6D, 0.0D, 0.6D, 0.4D, 1.0D, 0.4D);
    protected static final AxisAlignedBB CHAIN_AABB_X = new AxisAlignedBB(0.0D, 0.4D, 0.6D, 1.0D, 0.6D, 0.4D);
    protected static final AxisAlignedBB CHAIN_AABB_Z = new AxisAlignedBB(0.6D, 0.4D, 0.0D, 0.4D, 0.6D, 1.0D);

    public ChainBlock(String name) {
        super(Material.IRON);
        this.setHardness(5.0F);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setHarvestLevel("pickaxe", 0);
        this.setSoundType(SoundType.METAL);
        this.setLightOpacity(1);
        this.setResistance(6.0F);
        this.fullBlock = false;
    }
    @Override
    public boolean isTopSolid(IBlockState state) {
       return false;
    }
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        switch (state.getValue(BLOCK_AXIS)) {
            case X:
                return CHAIN_AABB_X;
            case Y:
                return CHAIN_AABB_Y;
            case Z:
                return CHAIN_AABB_Z;
            default:
                return CHAIN_AABB_Y;
        }
    }
    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState();
        switch (meta) {
           case 0:
               state = state.withProperty(BLOCK_AXIS, EnumAxis.Y);
               break;
           case 1:
               state = state.withProperty(BLOCK_AXIS, EnumAxis.X);
               break;
           case 2:
               state = state.withProperty(BLOCK_AXIS, EnumAxis.Z);
               break;
           default:
               state = state.withProperty(BLOCK_AXIS, EnumAxis.NONE);
        }
        return state;
    }
    @Override
    public int getMetaFromState(IBlockState state) {
        switch (state.getValue(BLOCK_AXIS)) {
            case X:
                return 1;
            case Y:
                return 0;
            case Z:
                return 2;
            default:
                return 3;
        }
    }
    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
       drops.add(new ItemStack(ItemRegister.get("chain")));
    }
    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }
    @Override
    public boolean isFullCube(IBlockState state) {
       return false;
    }
}
