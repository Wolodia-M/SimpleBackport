// Java package
package my.wolodiam.simplebackport.utils.proxy;
// Import minecraft classes
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
// Import forge classes
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
// Import mod classes
import my.wolodiam.simplebackport.utils.registry.BlockRegister;
import my.wolodiam.simplebackport.mc1_20.signTE.*;
import my.wolodiam.simplebackport.utils.DATA;
import my.wolodiam.simplebackport.utils.registry.data.BlockRegistryType;
import my.wolodiam.simplebackport.utils.registry.data.ItemRegistryType;
// Import statics from mod classes
import static my.wolodiam.simplebackport.utils.registry.BlockRegister.BLOCKS;
import static my.wolodiam.simplebackport.utils.registry.ItemRegister.ITEMS;

public class ClientProxy extends CommonProxy {
    @SideOnly(Side.CLIENT)
    @Override
    public void registerTEModels() {
        ClientRegistry.bindTileEntitySpecialRenderer(OakHangingSignTE.class, new HangingSignRenderer("textures/sign/oak_hanging_sign.png", BlockRegister.OAK_HANGING_SIGN_TOP_FULL, BlockRegister.OAK_HANGING_SIGN_SIDE));
        ClientRegistry.bindTileEntitySpecialRenderer(SpruceHangingSignTE.class, new HangingSignRenderer("textures/sign/spruce_hanging_sign.png", BlockRegister.SPRUCE_HANGING_SIGN_TOP_FULL, BlockRegister.SPRUCE_HANGING_SIGN_SIDE));
        ClientRegistry.bindTileEntitySpecialRenderer(BirchHangingSignTE.class, new HangingSignRenderer("textures/sign/birch_hanging_sign.png", BlockRegister.BIRCH_HANGING_SIGN_TOP_FULL, BlockRegister.BIRCH_HANGING_SIGN_SIDE));
        ClientRegistry.bindTileEntitySpecialRenderer(DarkOakHangingSignTE.class, new HangingSignRenderer("textures/sign/dark_oak_hanging_sign.png", BlockRegister.DARK_OAK_HANGING_SIGN_TOP_FULL, BlockRegister.DARK_OAK_HANGING_SIGN_SIDE));
        ClientRegistry.bindTileEntitySpecialRenderer(AcaciaHangingSignTE.class, new HangingSignRenderer("textures/sign/acacia_hanging_sign.png", BlockRegister.get("acacia_hanging_sign_top_full"), BlockRegister.get("acacia_hanging_sign_side")));
        ClientRegistry.bindTileEntitySpecialRenderer(JungleHangingSignTE.class, new HangingSignRenderer("textures/sign/jungle_hanging_sign.png", BlockRegister.get("jungle_hanging_sign_top_full"), BlockRegister.get("jungle_hanging_sign_side")));
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void registerItemModels(){
        for (ItemRegistryType data : ITEMS) {
            ModelLoader.setCustomModelResourceLocation(data.item, 0, new ModelResourceLocation(data.item.getRegistryName(), "inventory"));
        }
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockModel(Block block, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta,
                new ModelResourceLocation(DATA.MODID + ":" + block.getUnlocalizedName(), "inventory"));
    }
}