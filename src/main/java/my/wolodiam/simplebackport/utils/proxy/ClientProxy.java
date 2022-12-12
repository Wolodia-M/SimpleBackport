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
import my.wolodiam.simplebackport.utils.registry.data.ItemRegistryType;
import my.wolodiam.simplebackport.mc1_14.signTE.*;
// Import statics from mod classes;
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
        ClientRegistry.bindTileEntitySpecialRenderer(AcaciaSignTE.class, new SignRenderer("textures/sign/acacia_sign.png", BlockRegister.get("acacia_sign_top"), BlockRegister.get("acacia_sign_side")));
        ClientRegistry.bindTileEntitySpecialRenderer(BirchSignTE.class, new SignRenderer("textures/sign/birch_sign.png", BlockRegister.get("birch_sign_top"), BlockRegister.get("birch_sign_side")));
        ClientRegistry.bindTileEntitySpecialRenderer(DarkOakSignTE.class, new SignRenderer("textures/sign/dark_oak_sign.png", BlockRegister.get("dark_oak_sign_top"), BlockRegister.get("dark_oak_sign_side")));
        ClientRegistry.bindTileEntitySpecialRenderer(JungleSignTE.class, new SignRenderer("textures/sign/jungle_sign.png", BlockRegister.get("jungle_sign_top"), BlockRegister.get("jungle_sign_side")));
        ClientRegistry.bindTileEntitySpecialRenderer(OakSignTE.class, new SignRenderer("textures/sign/oak_sign.png", BlockRegister.get("oak_sign_top"), BlockRegister.get("oak_sign_side")));
        ClientRegistry.bindTileEntitySpecialRenderer(SpruceSignTE.class, new SignRenderer("textures/sign/spruce_sign.png", BlockRegister.get("spruce_sign_top"), BlockRegister.get("spruce_sign_side")));
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void registerItemModel(Item item, int meta, String variant){
            ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), variant));
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void registerItemModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockModel(Block block, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta,
                new ModelResourceLocation(DATA.MODID + ":" + block.getUnlocalizedName(), "inventory"));
    }
}