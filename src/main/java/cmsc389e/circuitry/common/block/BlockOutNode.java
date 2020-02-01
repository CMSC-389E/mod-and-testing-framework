package cmsc389e.circuitry.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockOutNode extends BlockNode {
    private static void update(World world, BlockPos pos, IBlockState state) {
	if (!world.isRemote)
	    setPowered(world, pos, state, world.isBlockPowered(pos));
    }

    public BlockOutNode() {
	super("out_node");
    }

    @Deprecated
    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
	update(world, pos, state);
    }

    @Override
    protected String nextTag() {
	return "outTest";
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
	update(world, pos, state);
    }
}