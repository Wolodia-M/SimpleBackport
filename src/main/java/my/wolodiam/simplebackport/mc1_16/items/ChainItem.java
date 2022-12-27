// Java package
package my.wolodiam.simplebackport.mc1_16.items;
// Import minecraft classes
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
// Import mod classes
import my.wolodiam.simplebackport.utils.registry.BlockRegister;
import my.wolodiam.simplebackport.mc1_16.blocks.ChainBlock;

public class ChainItem extends Item {
    public ChainItem(String name) {
        super();
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState block = worldIn.getBlockState(pos);
        boolean blockCanBePlaced = block.getBlock().isReplaceable(worldIn, pos);
        if (true) {
            BlockPos blockPos = pos.offset(facing);
            ItemStack item = player.getHeldItem(hand);
            item.setCount(item.getCount() - 1);
            if (player.canPlayerEdit(pos, facing, item)) {
                if (worldIn.isRemote) {
                    return EnumActionResult.SUCCESS;
                } else {
                    int pos1 = MathHelper.floor((double)((player.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5) & 15;
                    switch (facing) {
                        case DOWN:
                        case UP:
                            worldIn.setBlockState(blockPos, BlockRegister.get("chain_block").getDefaultState().withProperty(ChainBlock.BLOCK_AXIS, ChainBlock.EnumAxis.Y), pos1);
                            return EnumActionResult.SUCCESS;
                        case EAST:
                        case WEST:
                            worldIn.setBlockState(blockPos, BlockRegister.get("chain_block").getDefaultState().withProperty(ChainBlock.BLOCK_AXIS, ChainBlock.EnumAxis.X), pos1);
                            return EnumActionResult.SUCCESS;
                        case SOUTH:
                        case NORTH:
                            worldIn.setBlockState(blockPos, BlockRegister.get("chain_block").getDefaultState().withProperty(ChainBlock.BLOCK_AXIS, ChainBlock.EnumAxis.Z), pos1);
                            return EnumActionResult.SUCCESS;
                    }
                }
            }
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
