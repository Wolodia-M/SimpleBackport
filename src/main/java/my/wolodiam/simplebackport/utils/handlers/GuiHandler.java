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
package my.wolodiam.simplebackport.utils.handlers;
// Import minecraft classes
import my.wolodiam.simplebackport.mc1_14.containers.crafters.ContainerFletchingTable;
import my.wolodiam.simplebackport.mc1_14.guis.crafters.GuiFletchingTable;
import my.wolodiam.simplebackport.utils.registry.BlockRegister;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
// Import mod classes
import my.wolodiam.simplebackport.utils.DATA;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        IBlockState iblockstate = world.getBlockState(new BlockPos(x,y,z));
        if(ID == DATA.GUIID.FLETCHING_TABLE.id && (iblockstate.getBlock() == BlockRegister.get("fletching_table")))
            return new ContainerFletchingTable(player.inventory, world, new BlockPos(x, y, z));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        IBlockState iblockstate = world.getBlockState(new BlockPos(x,y,z));
        if(ID == DATA.GUIID.FLETCHING_TABLE.id && (iblockstate.getBlock() == BlockRegister.get("fletching_table")))
            return new GuiFletchingTable(player.inventory, world, new BlockPos(x, y, z));
        return null;
    }
}
