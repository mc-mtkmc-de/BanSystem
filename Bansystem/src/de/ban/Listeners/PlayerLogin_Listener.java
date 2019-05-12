package de.ban.Listeners;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class PlayerLogin_Listener implements Listener {
	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);
	static File filesett = new File("plugins//BanSystem//settings.yml");
	static YamlConfiguration settings = YamlConfiguration.loadConfiguration(filesett);

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();

		File file = new File("plugins//BanSystem//gebannte Spieler//" + p.getUniqueId() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		File tempfile = new File("plugins//BanSystem//temporar gebannte Spieler//" + p.getUniqueId() + ".yml");
		FileConfiguration tempcfg = YamlConfiguration.loadConfiguration(tempfile);
		if (file.exists()) {
			try {
				String message = messages.getString("messages.ban");
				message = message.replace("%banner%", cfg.getString("Informationen.Von"));
				message = message.replace("%grund%", cfg.getString("Informationen.Grund"));
				e.disallow(Result.KICK_BANNED, ChatColor.translateAlternateColorCodes('&', message));
			} catch (NullPointerException ex) {

			}
		}
		if (tempcfg.getLong("Informationen.MilliSeconds") >= System.currentTimeMillis()) {
			try {

				String message = messages.getString("messages.tempban");

				message = message.replace("%grund%", tempcfg.getString("Informationen.Grund"));
				message = message.replace("%banner%", tempcfg.getString("Informationen.Von"));
				message = message.replace("%datum%", tempcfg.getString("Informationen.Datum"));

				e.disallow(Result.KICK_BANNED, ChatColor.translateAlternateColorCodes('&', message));
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}

		} else {
			tempfile.delete();
		}
	}
}
