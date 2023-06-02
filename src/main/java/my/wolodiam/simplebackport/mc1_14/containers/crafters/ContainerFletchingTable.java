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
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
// Mod classes
import my.wolodiam.simplebackport.utils.registry.BlockRegister;

public class ContainerFletchingTable extends Container {

    // position of the workbench
    private BlockPos pos;
    // Player that use workbench
    private EntityPlayer player;
    // Inventory of player and crafting table
    private InventoryPlayer inventory;
    // World in what fletching table stays
    private World world;
    // Crafting inventories
    private InventoryCrafting out;
    // Arrow and potions
    public InventoryCrafting arrow_potion = new InventoryCrafting(this, 1, 3);
    // Arrow crafting
    public InventoryCrafting arrow = new InventoryCrafting(this, 1, 3);
    // Bows
    public InventoryCrafting bow = new InventoryCrafting(this, 2, 3);
    // All crafting inventories
    public ContainerFletchingTable(InventoryPlayer inventory, World world, BlockPos blockPos) {
        // Save locat variables
        this.player = inventory.player;
        this.inventory = inventory;
        this.pos = blockPos;
        this.world = world;
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
        // W: 18, H: 18
        // Bow slots
        // Standart bow recipe, can be taken from out
        // Top right    - x:53 y:15 id:46
        // this.addSlotToContainer(new SlotCrafting(this.player, this.bow, this.inventory, 46, 53, 15));
        // Top left     - x:35 y:15 id:47
        this.addSlotToContainer(new Slot(this.inventory, 47, 35, 15));
        // Middle right - x:53 y:33 id:48
        this.addSlotToContainer(new Slot(this.inventory, 48, 53, 33));
        // Middle left  - x:17 y:33 id:49
        this.addSlotToContainer(new Slot(this.inventory, 49, 17, 33));
        // Bottom right - x:53 y:51 id:50
        this.addSlotToContainer(new Slot(this.inventory, 50, 53, 51));
        // Bottom left  - x:35 y:51 id:51
        this.addSlotToContainer(new Slot(this.inventory, 51, 35, 51));
        // Out          - x:35 y:33 id:52
        this.addSlotToContainer(new Slot(this.inventory, 52, 35, 33));
        // Arrow & potion slots
        // Arrow in top, potion in bottom auto craft to arrow with potion in top
        // Top    - x:89 y:15 id:53
        this.addSlotToContainer(new Slot(this.inventory, 53, 89, 15));
        // Bottom - x:89 y:51 id:54
        this.addSlotToContainer(new Slot(this.inventory, 54, 89, 51));
        // Arrow slots
        // Standart arrow recipe rotated by 45 degree, can be taken from out
        // Top    - x:123 y:15 id:55
        this.addSlotToContainer(new Slot(this.inventory, 55, 123, 15));
        // Middle - x:123 y:33 id:56
        this.addSlotToContainer(new Slot(this.inventory, 56, 123, 33));
        // Bottom - x:123 y:51 id:57
        this.addSlotToContainer(new Slot(this.inventory, 57, 123, 51));
        // Out    - x:141 y:33 id:58
        this.addSlotToContainer(new SlotFletchingTableArrowOut(this.player, this.inventory, 58, 141, 33));
    }
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        if (this.world.getBlockState(this.pos).getBlock() != BlockRegister.get("fletching_table"))
        {
            return false;
        }
        else
        {
            return playerIn.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }
}
