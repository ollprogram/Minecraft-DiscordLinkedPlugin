package fr.ollprogram.discordlinkedplugin;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

/**
 * @author ollprogram
 * This class will be used by your Minecraft server.
 */
public class Main extends JavaPlugin {
	private JDA jda;
	private DiscordManager discManager;
	@Override
	public void onEnable(){
		this.saveDefaultConfig();
		FileConfiguration config = this.getConfig();
		String token = config.getString("Discord_bot_token");
		if(token != null && !token.equalsIgnoreCase("NULL") && !token.equalsIgnoreCase("") ){
			try {
				jda = buildJDA(token, config.getString("Bot_activity_description"));
				jda.awaitReady();
			} catch (InterruptedException | LoginException e) {
				if(e instanceof  LoginException) Bukkit.getLogger().warning("Probably wrong token given in config.yml file.");
				else {
					e.printStackTrace();
				}
			}
		}
		else {
			Bukkit.getLogger().warning("Discord bot not already set in config.yml.");
		}
		discManager = new DiscordManager(jda, config.getString("Discord_guild"), config.getString("Minecraft_channel"));
		CommandExecutor exec = new CommandKit(discManager);
		registerCommands(exec);
		if(discManager.isOperational()) discManager.markServerStatus(true);
		enabledMessage();
	}

	/**
	 * Register all commands.
	 * @param exec The Command executor for all commands.
	 */
	private void registerCommands(CommandExecutor exec){
		PluginCommand c1 = this.getCommand("saveLocation");
		if(c1 == null)return;
		c1.setExecutor(exec);
	}

	/**
	 * Build the discord bot with specified configurations.
	 * @param token The token of the Discord bot.
	 * @param activity The activity text for the Discord bot.
	 * @return The built JDA bot with the specified token and activity.
	 * @throws LoginException If the login failed.
	 */
	private static JDA buildJDA(String token, String activity) throws LoginException {
		JDABuilder builder = JDABuilder.createDefault(token);
		builder.setActivity(Activity.playing(activity));
		return builder.build();
	}

	/**
	 * Message for logs when the plugin is enabled.
	 */
	private void enabledMessage(){
		Bukkit.getLogger().info(ChatColor.BOLD+""+ChatColor.DARK_PURPLE+"Enabled "+this.getName()+".");
	}

	@Override
	public void onDisable() {
		if (discManager != null && discManager.isOperational()) {
			discManager.markServerStatus(false);
		}
		if(jda != null) jda.shutdown();
		disabledMessage();
	}

	/**
	 * Message for logs when the plugin is disabled.
	 */
	private void disabledMessage(){
		Bukkit.getLogger().info(ChatColor.BOLD+""+ChatColor.DARK_PURPLE+"Disabled "+this.getName()+".");
	}
}
