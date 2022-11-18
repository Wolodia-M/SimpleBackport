// Java package
package my.wolodiam.simplebackport.utils.registry.data;
// Import minecraft classes
import net.minecraft.block.Block;

public class BlockRegistryType {
    /**
     * Construct new BlockRegistryType
     *
     * @param block => Block -> block
     * @param id => String -> block id
     */
    public BlockRegistryType(Block block, String id) {
        this.block = block;
        this.id = id;
        this.hasBlockModel = false;
    }
    /**
     * Construct new BlockRegistryType
     *
     * @param block => Block -> block
     * @param id => String -> block id
     * @param hasBlockModel => boolean -> true, if need to have block model, not tile entity custom renderer
     */
    public BlockRegistryType(Block block, String id, boolean hasBlockModel) {
        this.block = block;
        this.id = id;
        this.hasBlockModel = hasBlockModel;
    }
    public Block block;
    public String id;
    public boolean hasBlockModel;
}
