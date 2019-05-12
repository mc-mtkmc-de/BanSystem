package de.ban.Listeners;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.ban.util.BanManagerMYSQL;

public class PlayerLogin_ListenerMySQL implements Listener {
	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (BanManagerMYSQL.isBanned(p.getUniqueId().toString())) {
			BanManagerMYSQL.unban(p.getUniqueId().toString());
		}
	}

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		if (BanManagerMYSQL.isBanned(p.getUniqueId().toString())) {
			long current = System.currentTimeMillis();
			long end = BanManagerMYSQL.getEnd(p.getUniqueId());
			if (current < end | end == -1) {
				String banned = messages.getString("messages.tempban");
				banned = banned.replace("%grund%", BanManagerMYSQL.getReason(p.getUniqueId().toString()));
				banned = banned.replace("%banner%", BanManagerMYSQL.getBanner(p.getUniqueId()));
				banned = banned.replace("%datum%", BanManagerMYSQL.getDatum(p.getUniqueId()));
				e.disallow(Result.KICK_BANNED, banned);

			}

		}
	}

}
