package me.leos.commandarguments.Commands;

import me.leos.commandarguments.CommandArguments;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;


public class MessageCommand implements CommandExecutor {

    //key = uuid of the player
    //long = epoch time of when they ran the command
    private final HashMap<UUID, Long> cooldown;
    private final CommandArguments plugin;

    public MessageCommand(CommandArguments plugin) {

        this.plugin = plugin;
        this.cooldown = new HashMap<>();

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {

                player.sendMessage(ChatColor.RED + "Please specify and player and message!");

            } else if (args.length == 1) {

                player.sendMessage(ChatColor.RED + "Please specify a message!");

            } else {

                StringBuilder builder = new StringBuilder();
                String playerName = args[0];
                Player targetPlayer = Bukkit.getServer().getPlayerExact(playerName);

                if (targetPlayer != null) {
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]);
                        builder.append(" ");
                    }

                    String finalMessage = builder.toString();
                    String finalMsg = finalMessage.stripTrailing();

                    // Copy message sent to player
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "To " + ChatColor.YELLOW + targetPlayer.getDisplayName() + ChatColor.GRAY + ": " + finalMsg);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 4.0f); // player.playSound(soundLocation, soundArg, (float) volume, (float) pitch);
                    // Message sent to target player
                    targetPlayer.sendMessage(ChatColor.LIGHT_PURPLE + "From " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY + ": " + finalMsg);
                    targetPlayer.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 4.0f);

                    plugin.getConfig().set("sendingPlayer", player.getDisplayName());
                    //plugin.getConfig().set("receivingPlayer", targetPlayer.getDisplayName());
                    plugin.saveConfig();

                } else {
                    player.sendMessage(ChatColor.RED + "Player is offline!");
                }

            }

        }

        return true;
    }
}
