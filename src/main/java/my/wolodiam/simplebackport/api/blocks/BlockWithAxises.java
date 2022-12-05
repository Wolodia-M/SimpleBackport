// Java package
package my.wolodiam.simplebackport.api.blocks;
// Import minecraft classes
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class BlockWithAxises extends Block {
    public static final PropertyEnum<EnumAxis> BLOCK_AXIS = PropertyEnum.create("axis", BlockWithAxises.EnumAxis.class);

    public BlockWithAxises(Material material, MapColor map) {
        super(material, map);
        this.setDefaultState(blockState.getBaseState().withProperty(BLOCK_AXIS, BlockWithAxises.EnumAxis.Y));

    }
    public BlockWithAxises(Material material) {
        super(material);
        this.setDefaultState(blockState.getBaseState().withProperty(BLOCK_AXIS, BlockWithAxises.EnumAxis.Y));

    }
    @Override
    public @NotNull IBlockState withRotation(@NotNull IBlockState state, @NotNull Rotation rot) {
        switch (rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (state.getValue(BLOCK_AXIS)) {
                    case X:
                        return state.withProperty(BLOCK_AXIS, EnumAxis.Y);
                    case Z:
                        return state.withProperty(BLOCK_AXIS, EnumAxis.X);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }
    @Override
    protected @NotNull BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, BLOCK_AXIS);
    }
    @Override
    public @NotNull IBlockState getStateForPlacement(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @NotNull EntityLivingBase placer) {
        return this.getStateFromMeta(meta).withProperty(BLOCK_AXIS, EnumAxis.fromFacingAxis(facing.getAxis()));
    }
    public enum EnumAxis implements IStringSerializable {
        X("x"),
        Y("y"),
        Z("z"),
        NONE("none");
        private final String name;

        EnumAxis(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Contract(pure = true)
        public static BlockWithAxises.EnumAxis fromFacingAxis(EnumFacing.@NotNull Axis axis) {
            switch (axis)
            {
                case X:
                    return X;
                case Y:
                    return Y;
                case Z:
                    return Z;
                default:
                    return NONE;
            }
        }
        @Override
        public @NotNull String getName() {
            return this.name;
        }
    }
}
