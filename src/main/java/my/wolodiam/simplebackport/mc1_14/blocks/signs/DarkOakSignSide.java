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
package my.wolodiam.simplebackport.mc1_14.blocks.signs;
// Import minecraft classes
import my.wolodiam.simplebackport.utils.registry.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.block.BlockWallSign;
// Import mod classes
import my.wolodiam.simplebackport.api.signs.SignBlock;
import my.wolodiam.simplebackport.mc1_14.signTE.DarkOakSignTE;

public class DarkOakSignSide extends SignBlock {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    protected static final AxisAlignedBB SIGN_EAST_AABB = new AxisAlignedBB(0.0D, 0.28125D, 0.0D, 0.125D, 0.78125D, 1.0D);
    protected static final AxisAlignedBB SIGN_WEST_AABB = new AxisAlignedBB(0.875D, 0.28125D, 0.0D, 1.0D, 0.78125D, 1.0D);
    protected static final AxisAlignedBB SIGN_SOUTH_AABB = new AxisAlignedBB(0.0D, 0.28125D, 0.0D, 1.0D, 0.78125D, 0.125D);
    protected static final AxisAlignedBB SIGN_NORTH_AABB = new AxisAlignedBB(0.0D, 0.28125D, 0.875D, 1.0D, 0.78125D, 1.0D);
    public DarkOakSignSide(String name) {
        super();
        this.setHardness(1);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new DarkOakSignTE();
    }
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            return tileentity instanceof DarkOakSignTE ? ((DarkOakSignTE)tileentity).executeCommand(playerIn) : false;
        }
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(FACING))
        {
            case NORTH:
            default:
                return SIGN_NORTH_AABB;
            case SOUTH:
                return SIGN_SOUTH_AABB;
            case WEST:
                return SIGN_WEST_AABB;
            case EAST:
                return SIGN_EAST_AABB;
        }
    }
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        if (!worldIn.getBlockState(pos.offset(enumfacing.getOpposite())).getMaterial().isSolid())
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }
    public int getMetaFromState(IBlockState p_176201_1_) {
        return p_176201_1_.getValue(FACING).getIndex();
    }
    public IBlockState withRotation(IBlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.withProperty(FACING, p_185499_2_.rotate((EnumFacing)p_185499_1_.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState p_185471_1_, Mirror p_185471_2_) {
        return p_185471_1_.withRotation(p_185471_2_.toRotation((EnumFacing)p_185471_1_.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }
    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        drops.add(new ItemStack(ItemRegister.get("dark_oak_sign"), 1));
    }
}



