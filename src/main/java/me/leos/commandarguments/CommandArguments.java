package me.leos.commandarguments;

import me.leos.commandarguments.Commands.FartCommand;
import me.leos.commandarguments.Commands.MessageCommand;
import me.leos.commandarguments.Commands.RepeatCommand;
import me.leos.commandarguments.Commands.ReplyCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandArguments extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("message").setExecutor(new MessageCommand(this));
        getCommand("reply").setExecutor(new ReplyCommand(this));

    }


}
