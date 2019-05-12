package de.ban.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.ban.Main.BanSystem;
import de.ban.util.StatsManager;

public class remBans_CMD implements CommandExecutor {

	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);
	static File filesett = new File("plugins//BanSystem//settings.yml");
	static YamlConfiguration settings = YamlConfiguration.loadConfiguration(filesett);

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if (p.hasPermission("system.rembans")) {
			if (args.length == 2) {

				Player target = Bukkit.getPlayer(args[0]);
				int bans = Integer.parseInt(args[1]);

				String remall = messages.getString("messages.rembans");
				remall = remall.replace("%prefix%", BanSystem.prefix);
				remall = remall.replace("%bans%", args[1]);
				remall = remall.replace("%player%", target.getName());
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', remall));
				String trueorfalse = settings.getString("settings.mysql");
				if (!trueorfalse.equalsIgnoreCase("true")) {
					StatsManager.removeBans(target, bans);
				} else if (!trueorfalse.equalsIgnoreCase("false")) {
					StatsManager.updateBansMySQL(target, bans, true);
				}
			} else {
				String use = messages.getString("messages.Benutzen.rembans");
				use = use.replace("%prefix%", BanSystem.prefix);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', use));
			}

		} else {
			String noPerm = messages.getString("messages.noPerm");
			noPerm = noPerm.replace("%prefix%", BanSystem.prefix);
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
		}

		return false;
	}

}
