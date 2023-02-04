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
package my.wolodiam.simplebackport.mc1_14.items.crafters;
// Import minecraft classes
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.item.Item;
// Import mod classes
import my.wolodiam.simplebackport.utils.registry.BlockRegister;
import my.wolodiam.simplebackport.mc1_14.blocks.crafters.FletchingTable;

public class FlethingTableItem extends Item {
    public FlethingTableItem(String name) {
        super();
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState block = worldIn.getBlockState(pos);
        boolean blockCanBePlaced = block.getBlock().isReplaceable(worldIn, pos);
        if (true) {
            BlockPos blockPos = pos.offset(facing);
            ItemStack item = player.getHeldItem(hand);
            if (player.canPlayerEdit(pos, facing, item)) {
                if (worldIn.isRemote) {
                    return EnumActionResult.SUCCESS;
                } else {
                    item.setCount(item.getCount() - 1);
                    // worldIn.setBlockState(blockPos, BlockRegister.get("fletching_table").getDefaultState());
                    int pos1 = MathHelper.floor((double)((player.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5) & 15;
                    switch (facing) {
                        case DOWN:
                        case UP:
                        case SOUTH:
                            worldIn.setBlockState(blockPos, BlockRegister.get("fletching_table").getDefaultState().withProperty(FletchingTable.BLOCK_ROTATION, 0), pos1);
                            return EnumActionResult.SUCCESS;
                        case EAST:
                            worldIn.setBlockState(blockPos, BlockRegister.get("fletching_table").getDefaultState().withProperty(FletchingTable.BLOCK_ROTATION, 3), pos1);
                            return EnumActionResult.SUCCESS;
                        case WEST:
                            worldIn.setBlockState(blockPos, BlockRegister.get("fletching_table").getDefaultState().withProperty(FletchingTable.BLOCK_ROTATION, 1), pos1);
                            return EnumActionResult.SUCCESS;
                        case NORTH:
                            worldIn.setBlockState(blockPos, BlockRegister.get("fletching_table").getDefaultState().withProperty(FletchingTable.BLOCK_ROTATION, 2), pos1);
                            return EnumActionResult.SUCCESS;
                    }
                }
            }
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
