package de.ban.Listeners;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.ban.util.MuteManager;

public class Mute_Listener implements Listener {
	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);

	@EventHandler
	public void onmute(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (MuteManager.ismuted(p.getUniqueId())) {
			e.setCancelled(true);

			p.sendMessage(messages.getString("messages.mute1"));
			p.sendMessage(messages.getString("messages.mute2"));
			p.sendMessage(messages.getString("messages.mute3"));
			p.sendMessage(messages.getString("messages.mute4"));
			p.sendMessage(messages.getString("messages.mute5"));

		}
	}

}
