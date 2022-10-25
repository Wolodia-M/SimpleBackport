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
package my.wolodiam.simplebackport.mc1_20.blocks.signs;
import net.minecraft.block.BlockWallSign;
// Import minecraft classes
import net.minecraft.block.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.tileentity.TileEntity;
// Import mod classes
import my.wolodiam.simplebackport.utils.registry.*;
import my.wolodiam.simplebackport.api.signs.SignBlock;
import my.wolodiam.simplebackport.mc1_20.signTE.OakHangingSignTE;

public class OakHangingSignSideBlock extends SignBlock {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    protected static final AxisAlignedBB SIGN_EAST_AABB = new AxisAlignedBB(0.0D, 0.2D, 0.4D, 1.0D, 0.8D, 0.6D);
    protected static final AxisAlignedBB SIGN_WEST_AABB = new AxisAlignedBB(0.0D, 0.2D, 0.4D, 1.0D, 0.8D, 0.6D);
    protected static final AxisAlignedBB SIGN_SOUTH_AABB = new AxisAlignedBB(0.4D, 0.2D, 0.0D, 0.6D, 0.8D, 1.0D);
    protected static final AxisAlignedBB SIGN_NORTH_AABB = new AxisAlignedBB(0.4D, 0.2D, 0.0D, 0.6D, 0.8D, 1.0D);


    public OakHangingSignSideBlock(String name) {
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    /**
     * @param worldIn world
     * @param meta metadata
     * @return OakHangingSignTE
     */
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
       return new OakHangingSignTE();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            return tileentity instanceof OakHangingSignTE ? ((OakHangingSignTE)tileentity).executeCommand(playerIn) : false;
        }
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch ((EnumFacing)state.getValue(FACING))
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
    public void neighborChanged(IBlockState p_189540_1_, World p_189540_2_, BlockPos p_189540_3_, Block p_189540_4_, BlockPos p_189540_5_) {
        super.neighborChanged(p_189540_1_, p_189540_2_, p_189540_3_, p_189540_4_, p_189540_5_);
    }

    public IBlockState getStateFromMeta(int p_176203_1_) {
        EnumFacing lvt_2_1_ = EnumFacing.getFront(p_176203_1_);
        if (lvt_2_1_.getAxis() == EnumFacing.Axis.Y) {
            lvt_2_1_ = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, lvt_2_1_);
    }

    public int getMetaFromState(IBlockState p_176201_1_) {
        return ((EnumFacing)p_176201_1_.getValue(FACING)).getIndex();
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

    /**
     * This gets a complete list of items dropped from this block.
     *
     * @param drops add all items this block drops to this drops list (NonNullList<ItemStack>, returned value)
     * @param world The current world (IBlockAccess)
     * @param pos Block position in world (BlockPos)
     * @param state Current state (IBlockState)
     * @param fortune Breakers fortune level (int)
     */
    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        drops.add(new ItemStack(ItemRegister.OAK_HANGING_SIGN_ITEM, 1));
    }
}

