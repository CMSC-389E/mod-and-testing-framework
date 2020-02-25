package cmsc389e.circuitry.common.command;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import cmsc389e.circuitry.ConfigCircuitry;
import cmsc389e.circuitry.common.block.BlockInNode;
import cmsc389e.circuitry.common.block.BlockNode;
import cmsc389e.circuitry.common.world.CircuitryWorldSavedData;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CommandSet extends CommandCircuitryBase {
    public static void execute(World world, ICommandSender sender, Boolean isPowered, @Optional String input)
	    throws CommandException {
	boolean valid = false;
	for (BlockPos pos : CircuitryWorldSavedData.get(world)) {
	    IBlockState state = world.getBlockState(pos);
	    if (state.getBlock() instanceof BlockInNode
		    && (input == null || input.equals(BlockNode.getTag(world, pos, state)))) {
		BlockNode.setPowered(world, pos, state, isPowered);
		valid = true;
	    }
	}
	if (!valid)
	    throw new CommandException(
		    "Cannot find any input blocks" + (input == null ? "" : " with the tag " + input) + '.');
	sendMessage(sender, "Input blocks set successfully!");
    }

    public CommandSet() {
	super("set");
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
	    @Nullable BlockPos targetPos) {
	return args.length == 2 ? Arrays.asList(ConfigCircuitry.inTags)
		: super.getTabCompletions(server, sender, args, targetPos);
    }
}
