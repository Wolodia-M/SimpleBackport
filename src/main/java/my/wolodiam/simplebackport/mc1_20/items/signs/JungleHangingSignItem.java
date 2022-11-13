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
package my.wolodiam.simplebackport.mc1_20.items.signs;
// import net.minecraft.item.ItemSign;
// Import minecraft classes
import net.minecraft.advancements.*;
import net.minecraft.block.state.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
// Import mod classes
import my.wolodiam.simplebackport.utils.registry.BlockRegister;
import my.wolodiam.simplebackport.mc1_20.blocks.signs.JungleHangingSignTopFullBlock;
import my.wolodiam.simplebackport.mc1_20.blocks.signs.JungleHangingSignSideBlock;

public class JungleHangingSignItem extends Item {
    public JungleHangingSignItem(String name) {
        this.maxStackSize = 16;
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, float float1, float float2, float float3) {
        IBlockState blockState = world.getBlockState(blockPos);
        boolean blockCanBePlaces = blockState.getBlock().isReplaceable(world, blockPos);
        if (true) {
            blockPos = blockPos.offset(facing);
            ItemStack itemStack = player.getHeldItem(hand);
            if (player.canPlayerEdit(blockPos, facing, itemStack) && BlockRegister.get("jungle_hanging_sign_top_full").canPlaceBlockAt(world, blockPos)) {
                if (world.isRemote) {
                    return EnumActionResult.SUCCESS;
                } else {
                    blockPos = blockCanBePlaces ? blockPos.down() : blockPos;
                    if (facing == EnumFacing.DOWN) {
                        int pos1 = MathHelper.floor((double)((player.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5) & 15;
                        world.setBlockState(blockPos, BlockRegister.get("jungle_hanging_sign_top_full").getDefaultState().withProperty(JungleHangingSignTopFullBlock.ROTATION, pos1), 11);
                    } else {
                        world.setBlockState(blockPos,BlockRegister.get("jungle_hanging_sign_side").getDefaultState().withProperty(JungleHangingSignSideBlock.FACING, facing), 11);
                    }

                    TileEntity tileEntitySign = world.getTileEntity(blockPos);
                    if (tileEntitySign instanceof TileEntitySign && !ItemBlock.setTileEntityNBT(world, player, blockPos, itemStack)) {
                        player.openEditSign((TileEntitySign)tileEntitySign);
                    }

                    if (player instanceof EntityPlayerMP) {
                        CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, blockPos, itemStack);
                    }

                    itemStack.shrink(1);
                    return EnumActionResult.SUCCESS;
                }
            } else {
                return EnumActionResult.FAIL;
            }
        } else {
            return EnumActionResult.FAIL;
        }
    }
}
