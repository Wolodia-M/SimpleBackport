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
package my.wolodiam.simplebackport.api.biomes;
// Import minecraftforge classes
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.WorldWorkerManager;
// Import minecraft classes
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.entity.player.EntityPlayer;
// Import mod classes
import my.wolodiam.simplebackport.utils.config.ConfigHandler;

public class BiomeFinderWorker implements WorldWorkerManager.IWorker {
    // Starting values
    World search_world;
    BlockPos search_start;
    Biome search_biome;
    boolean worked;
    EntityPlayer caller;
    // Constants
    int step_size;
    double max_distance;
    int max_steps;
    // Temporary variables
    EnumFacing dir;
    int x;
    int z;
    int steps;
    int distance;

    /**
     * Construct new worker for biome finding
     * @param world => World -> world for searching
     * @param biome => Biome -> what need to be searched
     * @param start => BlockPos -> stating position
     * @param player => EntityPlayer -> player, that call command
     */
    public BiomeFinderWorker(World world, Biome biome, BlockPos start, EntityPlayer player) {
        worked = true;
        // Load arguments
        this.search_biome = biome;
        this.search_start = start;
        this.search_world = world;
        this.caller = player;
        // Init constants
        this.max_distance = ConfigHandler.commands.locatebiome_radius * BiomeList.getBiomeSize(this.search_world);
        this.step_size = 16 * BiomeList.getBiomeSize(this.search_world);
        this.max_steps = 50000;
        // Init temporary variables
        this.dir = EnumFacing.UP;
        this.steps = 0;
        this.distance = this.step_size;
        this.x = this.search_start.getX();
        this.z = this.search_start.getZ();
        // Register worker
        WorldWorkerManager.addWorker(this);
    }

    /**
     * Get distance from start to search point
     * @return -> int => distance
     */
    private int calculate_distance() {
        return (int) this.search_start.getDistance(this.x, this.search_start.getY(), this.z);
    }
    @Override
    public boolean hasWork() {
        return worked && calculate_distance() < this.max_distance && this.steps < 50000;
    }
    @Override
    public void work() {
        WorldWorkerManager.IWorker.super.work();
    }
    @Override
    public boolean doWork() {
        // If needed to do some work
        if (this.worked) {
            // Move search position
            if (this.dir == EnumFacing.NORTH) {
                z -= this.step_size;
            } else if (this.dir == EnumFacing.EAST) {
                x += this.step_size;
            } else if (this.dir == EnumFacing.SOUTH) {
                z += this.step_size;
            } else if (this.dir == EnumFacing.WEST) {
                x -= this.step_size;
            }
            // Get new position as BlockPos
            BlockPos pos = new BlockPos(x, this.search_world.getHeight(), z);
            // Get biome at this position
            Biome biome = this.search_world.getBiomeForCoordsBody(pos);
            // If biome math search biome
            if (biome == this.search_biome) {
                // Return coordinates of biome
                this.returned(true);
                // End work
                return false;
            }
            // Else increment step counter
            this.steps++;
            // Increment search distance
            this.distance += this.step_size;
            // If distance is greater than max distance
            if (this.distance >= this.max_distance) {
                if (this.dir != EnumFacing.UP) {
                    this.max_distance += this.step_size;
                    this.dir = this.dir.rotateY();
                } else {
                    this.dir = EnumFacing.NORTH;
                }
                this.distance = 0;
            }
        }
        if (hasWork()) {
            return true;
        }
        this.returned(false);
        return false;
    }

    /**
     * Return data
     * @param result -> boolean => result of work
     */
    public void returned(boolean result) {
        if (result) {
            String cords = this.x + " ~ " + this.z;
            this.caller.sendMessage(new TextComponentTranslation("commands.locatebiome.out", BiomeList.getBiomeName(this.search_biome), cords));
        } else {
            this.caller.sendMessage(new TextComponentTranslation("commands.locatebiome.error", BiomeList.getBiomeName(this.search_biome)));
        }
        this.worked = false;
    }
}
