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
package my.wolodiam.simplebackport.mc1_14.containers.crafters;
// Import minecraft classes
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerFletchingTable extends Container {

    // position of the workbench
    private BlockPos pos;
    // Player that use workbench
    private EntityPlayer player;
    public ContainerFletchingTable(InventoryPlayer inventory, World world, BlockPos blockPos) {
        this.player = inventory.player;
        this.pos = blockPos;
        // Player inventory
        for (int k = 0; k < 3; ++k) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlotToContainer(new Slot(inventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }
        // Player hotbar
        for (int l = 0; l < 9; ++l) {
            this.addSlotToContainer(new Slot(inventory, l, 8 + l * 18, 142));
        }
    }
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
