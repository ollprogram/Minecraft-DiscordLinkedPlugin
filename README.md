# Minecraft-DiscordLinkedPlugin
A simple minecraft plugin running on Spigot using a Discord bot to play with your friends.

# Author and licence :
Hi I'm ollprogram and I am the author of this project. Thanks for using it.
Please don't be afraid to report bugs or mistakes to me. I'll try to fix them. By the way, english isn't my main language, so I'm sorry for english mistakes but I'm open if you find some.
Find information about the licence used for this project <a href="https://github.com/ollprogram/Minecraft-DiscordLinkedPlugin/blob/main/LICENSE">here</a>. You need to know the licence before using my project. It gives information about how you can use it.
# Description :
This is a simple minecraft plugin using a Discord bot. It can be used as an example of a minecraft plugin linked with Discord, or to play with friends. This plugin shows the minecraft server status on Discord. You will also be able to send in-game locations to Discord.
# Download :
You can download it <a href="https://github.com/ollprogram/Minecraft-DiscordLinkedPlugin/releases/download/v1.0.0/DiscordLinkedPlugin-1.0.0.jar">here</a> on Github or on spigotmc.org soon. 
# Setup :
<p>First of all you will need to create a discord bot with <a href="https://discord.com/developers/docs/intro">The Discord Developper Portal</a> and add it into your server with at least those permissions : </p>
<img src="https://user-images.githubusercontent.com/39884051/151679694-15a874c3-b2ec-445e-a134-31a7253ec5f5.png"></a>
When this is done, follow these steps :
<ol>
  <li>Download the plugin</li>
  <li>Put the plugin jar file into your plugin directory in your Spigot server.</li>
  <li>Run your server</li>
  <li>You will have a warning because the plugin isn't yet configurated. Then stop your server.</li>
  <li><p>Open the config.yml file of this plugin (available in your plugin directory) with an editor. There are 4 variables : <br>
    <img src="https://github.com/ollprogram/Minecraft-DiscordLinkedPlugin/blob/main/config.yml_example.png"></img>
    <br>You will need add to at least your discord bot Token.
</p></li>
  <li> Now you should be good. Restart your server, and if there are no warnings/errors the plugin should be installed correctly.</li>
</ol>
  
# Fonctions and usage:
<p>When the plugins is enabled (when the server starts), the discord bot will rename his log channel like this :</p>
<img src="https://user-images.githubusercontent.com/39884051/151680042-a189c0f7-4cf1-45a8-b933-0a79dedaffd7.png"><img>
Or when disabled (server shutdown) :</p>
<img src="https://user-images.githubusercontent.com/39884051/151679925-9930996c-fdda-48b9-8b92-6a3048215227.png"><img>
<p>
  The command <code>savelocation</code> will send the location of the player who called this command to the discord channel log.
  <br> The sythax is <code>/savelocation &ltlocation_name&gt &ltdescription .... </code>
  <br> Example: <code>/savelocation portal this is my first portal</code>
</p>

# Dependencies for developpers :
You can directly use this <a href="https://github.com/ollprogram/Minecraft-DiscordLinkedPlugin/blob/main/pom.xml">pom.xml</a> if you want to use Maven.
You will probably want to change some fields. I'm using two different APIs:
<ul>
  <li> JDA (<a href = "https://github.com/DV8FromTheWorld/JDA">Java Discord API</a>)</li>
  <li> Spigot (<a href = "https://www.spigotmc.org">An API for Minecraft servers</a>)</li>
</ul>
