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
package my.wolodiam.simplebackport.mc1_14.items.signs;
// Import minecraft classes
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
// Import mod classes
import my.wolodiam.simplebackport.utils.registry.BlockRegister;
import my.wolodiam.simplebackport.mc1_14.blocks.signs.SpruceSignSide;
import my.wolodiam.simplebackport.mc1_14.blocks.signs.SpruceSignTop;

public class SpruceSignItem extends Item {
    public SpruceSignItem(String name) {
        this.maxStackSize = 16;
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        setUnlocalizedName(name);
        setRegistryName(name);
    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, float float1, float float2, float float3) {
        IBlockState blockState = world.getBlockState(blockPos);
        boolean blockCanBePlaces = blockState.getBlock().isReplaceable(world, blockPos);
        if (true) {
            blockPos = blockPos.offset(facing);
            ItemStack itemStack = player.getHeldItem(hand);
            if (player.canPlayerEdit(blockPos, facing, itemStack) && BlockRegister.get("spruce_sign_top").canPlaceBlockAt(world, blockPos)) {
                if (world.isRemote) {
                    return EnumActionResult.SUCCESS;
                } else {
                    blockPos = blockCanBePlaces ? blockPos.down() : blockPos;
                    if (facing == EnumFacing.UP) {
                        int pos1 = MathHelper.floor((double)((player.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5) & 15;
                        world.setBlockState(blockPos, BlockRegister.get("spruce_sign_top").getDefaultState().withProperty(SpruceSignTop.ROTATION, pos1), 11);
                    } else {
                        world.setBlockState(blockPos,BlockRegister.get("spruce_sign_side").getDefaultState().withProperty(SpruceSignSide.FACING, facing), 11);
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
