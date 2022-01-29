package fr.ollprogram.discordlinkedplugin;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author ollprogram
 * This class allows sending requests from the minecraft server to Discord.
 */
public class DiscordManager {
	private TextChannel defaultChannel;
	private Guild guild;
	/**
	 * Construct the discordManager
	 * @param jda JDA instance (discord bot).
	 * @param guildID The guildId from the config.yml.
	 * @param channelID The channelID from the config.yml.
	 */
	public DiscordManager(@Nullable JDA jda, String guildID, String channelID){
		if(jda == null) {
			defaultChannel = null; return;
		}
		if(guildID == null || guildID.equalsIgnoreCase("NULL")){
			List<Guild> guilds = jda.getGuilds();
			if(guilds.size() == 0) guild = null;
			else guild = guilds.get(0);
		}
		else guild = jda.getGuildById(guildID);
		if(guild != null) {
			if(channelID == null || channelID.equalsIgnoreCase("NULL")){
				defaultChannel = guild.getDefaultChannel();
			}
			else defaultChannel = guild.getTextChannelById(channelID);
		}
	}

	/**
	 * If the bot is set up
	 * @return <p> If the bot is set up.
	 * The bot is set up if all The token is good and if the bot is connected to at least one server.</p>
	 */
	public boolean isOperational(){
		return guild != null && defaultChannel != null;
	}

	/**
	 * Send a message to discord (containing the location, a name and a description).
	 * @param location Location where the command has been executed.
	 * @param name The name for the location.
	 * @param description A description to describe this location. (all arguments from the command)
	 * @param p The player who sends the command.
	 */
	public void sendLocation(Location location, String name, String[] description, Player p){
		String strDescription = descriptionToString(description);
		defaultChannel.sendMessage("```yaml\n" +
				"Location: "+location.getX()+"X ; "+location.getY()+"Y ; "+location.getZ()+"Z\n" +
				"Name: "+name+"\n" +
				"Description:\n"+strDescription+"```").queue();
		p.sendMessage("§6New location added on Discord");
	}

	/**
	 * Convert a description array to a string.
	 * @param description The description array.
	 * @return The description converted into string.
	 */
	private String descriptionToString(String[] description){
		StringBuilder tmp = new StringBuilder();
		for(int i = 1; i < description.length; i++){
			tmp.append(description[i]);
			tmp.append(" ");
		}
		return tmp.toString();
	}

	/**
	 * Mark a channel with an emoji to show if the minecraft server is online.
	 * @param isOnline If the minecraft server is online.
	 */
	public void markServerStatus(boolean isOnline){
		String lastName = defaultChannel.getName().replaceFirst("\uD83D\uDFE2", "");
		lastName = lastName.replaceFirst("\uD83D\uDD34", "");
		defaultChannel.getManager().setName(((isOnline)?"\uD83D\uDFE2":"\uD83D\uDD34")+lastName).queue();
	}
}
