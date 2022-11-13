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
    }
    public Block block;
    public String id;
}
