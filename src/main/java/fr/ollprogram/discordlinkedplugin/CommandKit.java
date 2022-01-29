package fr.ollprogram.discordlinkedplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author ollprogram
 * Handles all Minecraft commands.
 */
public class CommandKit implements CommandExecutor {

	private final DiscordManager discordManager;

	/**
	 * Construct a CommandKit
	 * @param discordManager The manager which allow sending requests from minecraft to Discord.
	 */
	public CommandKit(DiscordManager discordManager){
		super();
		this.discordManager = discordManager;
	}

	/**
	 * Executes the given command, returning its success.
	 * <br>
	 * If false is returned, then the "usage" plugin.yml entry for this command
	 * (if defined) will be sent to the player.
	 *
	 * @param sender  Source of the command
	 * @param command Command which was executed
	 * @param label   Alias of the command which was used
	 * @param args    Passed command arguments
	 * @return true if a valid command, otherwise false
	 */
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if((sender instanceof Player) ){
			Player p = (Player)sender;
			if(label.equalsIgnoreCase("saveLocation")){
				if(!discordManager.isOperational()){
					p.sendMessage("§6The owner of this server or admins need to configure this plugin to be able to use this command.");
					return true;
				}
				if(args.length >= 2) {
					discordManager.sendLocation(p.getLocation(), args[0], args, p);
					return true;
				}
				p.sendMessage("§4 Need more arguments.");
				return false;
			}
		}
		return false;
	}
}
