package me.leos.commandarguments.Commands;

import me.leos.commandarguments.CommandArguments;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor {

    private final CommandArguments plugin;

    public ReplyCommand(CommandArguments plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {

            String sendingPlayer = plugin.getConfig().getString("sendingPlayer");

            Player player = (Player) sender;
            Player targetPlayer = Bukkit.getPlayerExact(sendingPlayer);

            if (args.length != 0 && targetPlayer != null) {

                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < args.length; i++) {
                    builder.append(args[i]);
                    builder.append(" ");
                }
                String finalMessage = builder.toString().stripTrailing();

                targetPlayer.sendMessage(ChatColor.LIGHT_PURPLE + "From " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY + ": " + finalMessage);
                targetPlayer.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 4.0f);

                player.sendMessage(ChatColor.LIGHT_PURPLE + "To " + ChatColor.YELLOW + targetPlayer.getDisplayName() + ChatColor.GRAY + ": " + finalMessage);
                targetPlayer.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 4.0f);

                plugin.getConfig().set("sendingPlayer", player.getDisplayName());
                plugin.saveConfig();

            } else if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Please specify a message!");
            } else {
                player.sendMessage(ChatColor.RED + "Is this even possible?");
            }

        }


        return true;
    }
}
