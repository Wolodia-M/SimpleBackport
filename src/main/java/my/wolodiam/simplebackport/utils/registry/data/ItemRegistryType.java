// Java package
package my.wolodiam.simplebackport.utils.registry.data;
// Import minecraft classes
import net.minecraft.item.Item;

public class ItemRegistryType {
    /**
     * Construct new ItemRegistryType
     *
     * @param item => Item -> item
     * @param id => String -> id of item
     */
    public ItemRegistryType(Item item, String id) {
        this.item = item;
        this.id = id;
    }
    public Item item;
    public String id;
}
