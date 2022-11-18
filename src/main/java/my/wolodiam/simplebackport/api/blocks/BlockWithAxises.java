// Java package
package my.wolodiam.simplebackport.api.blocks;
// Import minecraft classes
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;

public class BlockWithAxises extends Block {
    public static final PropertyEnum<BlockWithAxises.EnumAxis> BLOCK_AXIS = PropertyEnum.<BlockWithAxises.EnumAxis>create("axis", BlockWithAxises.EnumAxis.class);

    BlockWithAxises(Material material, MapColor map) {
        super(material, map);
        this.setDefaultState(blockState.getBaseState().withProperty(BLOCK_AXIS, BlockWithAxises.EnumAxis.Y));

    }
    BlockWithAxises(Material material) {
        super(material);
        this.setDefaultState(blockState.getBaseState().withProperty(BLOCK_AXIS, BlockWithAxises.EnumAxis.Y));

    }

    public static enum EnumAxis implements IStringSerializable {
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

        public static BlockWithAxises.EnumAxis fromFacingAxis(EnumFacing.Axis axis) {
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
        public String getName() {
            return this.name;
        }
    }
}
