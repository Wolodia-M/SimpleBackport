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
package my.wolodiam.simplebackport.mc1_14.blocks.crafters;
// Import minecraft classes
import my.wolodiam.simplebackport.SimpleBackport;
import my.wolodiam.simplebackport.utils.DATA;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
// Import mod classes
import my.wolodiam.simplebackport.utils.registry.ItemRegister;
import my.wolodiam.simplebackport.api.blocks.BlockWithRotation;

public class FletchingTable extends BlockWithRotation {
    // |---------------------------------------------------------------| //
    // |                                                               | //
    // | [bow pic] sl1            sl6         sl3         [arrow pic]  | //
    // | [bow pic]          [arrow to bottle]     sl4     [arrow pic]  | //
    // | [bow pic] sl2            sl7                 sl5 [arrow pic]  | //
    // |                                                               | //
    // |---------------------------------------------------------------| //

    public FletchingTable(String name) {
        super(Material.WOOD);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(2.5F);
        this.setResistance(2.5F);
    }
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {BLOCK_ROTATION});
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
            playerIn.openGui(SimpleBackport.instance, DATA.GUIID.FLETCHING_TABLE.id, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
    }
    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        drops.add(new ItemStack(ItemRegister.get("fletching_table"), 1));
    }
}
