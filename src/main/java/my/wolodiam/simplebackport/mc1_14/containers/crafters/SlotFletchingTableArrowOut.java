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
// Java packages
package my.wolodiam.simplebackport.mc1_14.containers.crafters;
import net.minecraft.inventory.IInventory;
// Import Minecraft classes
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;

public class SlotFletchingTableArrowOut extends Slot {
    // Saved player
    private EntityPlayer player;
    public SlotFletchingTableArrowOut(EntityPlayer parPlayer, IInventory parIInventory, int parSlotIndex, int parXDisplayPosition, int parYDisplayPosition) 
    {
        super(parIInventory, parSlotIndex, parXDisplayPosition, parYDisplayPosition);
        this.player = parPlayer;
    }
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return true;
    }
}
