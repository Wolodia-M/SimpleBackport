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

public class ChainBlock extends Block {
    public static final PropertyEnum<ChainBlock.EnumAxis> CHAIN_AXIS = PropertyEnum.<ChainBlock.EnumAxis>create("axis", ChainBlock.EnumAxis.class);
    protected static final AxisAlignedBB CHAIN_AABB_Y = new AxisAlignedBB(0.6D, 0.0D, 0.6D, 0.4D, 1.0D, 0.4D);
    protected static final AxisAlignedBB CHAIN_AABB_X = new AxisAlignedBB(0.0D, 0.4D, 0.6D, 1.0D, 0.6D, 0.4D);
    protected static final AxisAlignedBB CHAIN_AABB_Z = new AxisAlignedBB(0.6D, 0.4D, 0.0D, 0.4D, 0.6D, 1.0D);

    // TODO => bugfix, craft
    public ChainBlock(String name) {
        super(Material.IRON);
        this.setHardness(5.0F);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setHarvestLevel("pickaxe", 0);
        this.setSoundType(SoundType.METAL);
        this.setDefaultState(blockState.getBaseState().withProperty(CHAIN_AXIS, EnumAxis.Y));
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
        switch (state.getValue(CHAIN_AXIS)) {
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
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getStateFromMeta(meta).withProperty(CHAIN_AXIS, ChainBlock.EnumAxis.fromFacingAxis(facing.getAxis()));
    }
    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState();
        switch (meta) {
           case 0:
               state = state.withProperty(CHAIN_AXIS, EnumAxis.Y);
               break;
           case 1:
               state = state.withProperty(CHAIN_AXIS, EnumAxis.X);
               break;
           case 2:
               state = state.withProperty(CHAIN_AXIS, EnumAxis.Z);
               break;
           default:
               state = state.withProperty(CHAIN_AXIS, EnumAxis.NONE);
        }
        return state;
    }
    @Override
    public int getMetaFromState(IBlockState state) {
        switch (state.getValue(CHAIN_AXIS)) {
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
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        switch (rot) {
            case COUNTERCLOCKWISE_90:;
            case CLOCKWISE_90:
                switch (state.getValue(CHAIN_AXIS)) {
                    case X:
                        return state.withProperty(CHAIN_AXIS, ChainBlock.EnumAxis.Y);
                    case Z:
                        return state.withProperty(CHAIN_AXIS, ChainBlock.EnumAxis.X);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {CHAIN_AXIS});
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
    public static enum EnumAxis implements IStringSerializable {
        X("x"),
        Y("y"),
        Z("z"),
        NONE("none");
        private final String name;

        EnumAxis(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static ChainBlock.EnumAxis fromFacingAxis(EnumFacing.Axis axis) {
            switch (axis)
            {
                case X:
                    return X;
                case Y:
                    return Y;
                case Z:
                    return Z;
                default:
                    return NONE;
            }
        }
        @Override
        public String getName() {
            return this.name;
        }
    }
}
