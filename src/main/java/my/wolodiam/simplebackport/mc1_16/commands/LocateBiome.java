// Java package
package my.wolodiam.simplebackport.mc1_16.commands;
// Tutorial imports
// import net.minecraft.command.CommandLocate;
// Import minecraft classes
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.biome.Biome;
// Import Java classes
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
// Import mod classes
import my.wolodiam.simplebackport.api.biomes.BiomeList;
import my.wolodiam.simplebackport.api.biomes.BiomeFinderWorker;

public class LocateBiome extends CommandBase {
    @Override
    public @NotNull String getName()
    {
        return "locatebiome";
    }
    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }
    @Override
    public @NotNull String getUsage(@NotNull ICommandSender sender)
    {
        return "commands.locatebiome.usage";
    }
    @Override
    public void execute(@NotNull MinecraftServer server, @NotNull ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            throw new WrongUsageException("commands.locatebiome.usage");
        }
        if (args[0].equals("list")) {
            List<Biome> biomes = BiomeList.getBiomes();
            for (Biome b : biomes) sender.sendMessage(new TextComponentString(BiomeList.getFormattedBiomeName(b)));
            return;
        }
        Map<String, Biome> biomes = BiomeList.getBiomesWithName();
        Biome b = biomes.get(args[0]);
        EntityPlayer p = getCommandSenderAsPlayer(sender);
        BiomeFinderWorker finder = new BiomeFinderWorker(sender.getEntityWorld(), b, sender.getPosition(), p);
        finder.hasWork();
    }
    @Override
    public @NotNull List<String> getTabCompletions(@NotNull MinecraftServer server, @NotNull ICommandSender sender, String @NotNull [] args, @Nullable BlockPos targetPos) {
        ArrayList<String> array = new ArrayList<String>();
        List<Biome> biomes = BiomeList.getBiomes();
        for (Biome b : biomes) {
            array.add(BiomeList.getFormattedBiomeName(b));
        }
        array.add("list");
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, array);
        }
        else {
            return Collections.emptyList();
        }
    }
}
