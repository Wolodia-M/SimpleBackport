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
// Import minecraft classes
import net.minecraft.block.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.tileentity.TileEntity;
// import net.minecraft.block.BlockStandingSign;
// Import mod classes
import my.wolodiam.simplebackport.utils.registry.ItemRegister;
import my.wolodiam.simplebackport.api.signs.SignBlock;
import my.wolodiam.simplebackport.mc1_20.signTE.DarkOakHangingSignTE;
public class DarkOakHangingSignTopFullBlock extends SignBlock {
    public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 15);
    public DarkOakHangingSignTopFullBlock(String name) {
        super();
        this.setHardness(1);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
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
            return tileentity instanceof DarkOakHangingSignTE ? ((DarkOakHangingSignTE)tileentity).executeCommand(playerIn) : false;
        }
    }

    /**
     * @param worldIn world
     * @param meta metadata
     * @return OakHangingSignTE
     */
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new DarkOakHangingSignTE();
    }

    /**
     * Check neighbor blocks, does sign need to be broken, if need ->
     * destroy sign and drop item of this sign
     *
     * @param blockState blockstate (IBlockState)
     * @param world world (World)
     * @param blockPos pos of sign (BlockPos)
     * @param block some block (Block)
     * @param blockPos1 some another blockpos (BlockPos)
     */
    @Override
    public void neighborChanged(IBlockState blockState, World world, BlockPos blockPos, Block block, BlockPos blockPos1) {
        if (!world.getBlockState(blockPos.up()).getMaterial().isSolid()) {
            this.dropBlockAsItem(world, blockPos, blockState, 0);
            world.setBlockToAir(blockPos);
        }

        super.neighborChanged(blockState, world, blockPos, block, blockPos1);
    }

    /**
     * Get blockstate from metadata
     *
     * @param metadata metadata (int)
     * @return new blockstate (IBlockState)
     */
    public IBlockState getStateFromMeta(int metadata) {
        return this.getDefaultState().withProperty(ROTATION, metadata);
    }

    /**
     * Get metatdata from blockstate
     *
     * @param state Blockstate (IBlockState)
     * @return new metadata (int)
     */
    public int getMetaFromState(IBlockState state) {
        return (Integer)state.getValue(ROTATION);
    }

    /**
     * Get rotated sign
     *
     * @param blockState blockstate (IBlockState)
     * @param rotation rotation (Rotation)
     * @return new blockstate (IBlockState)
     */
    public IBlockState withRotation(IBlockState blockState, Rotation rotation) {
        return blockState.withProperty(ROTATION, rotation.rotate((Integer)blockState.getValue(ROTATION), 16));
    }

    /**
     * Get mirrored sign
     *
     * @param blockState block state (IBlockState)
     * @param mirror mirror (Mirror)
     * @return new block state (IBlockState)
     */
    public IBlockState withMirror(IBlockState blockState, Mirror mirror) {
        return blockState.withProperty(ROTATION, mirror.mirrorRotation((Integer)blockState.getValue(ROTATION), 16));
    }

    /**
     * Create new blockstate
     *
     * @return new blockstate (BlockStateContainer)
     */
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ROTATION});
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
        drops.add(new ItemStack(ItemRegister.DARK_OAK_HANGING_SIGN_ITEM, 1));
    }
}






