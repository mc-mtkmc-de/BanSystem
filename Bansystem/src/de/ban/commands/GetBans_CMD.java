package de.ban.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.ban.Main.BanSystem;
import de.ban.util.StatsManager;

public class GetBans_CMD implements CommandExecutor {

	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);
	static File filesett = new File("plugins//BanSystem//settings.yml");
	static YamlConfiguration settings = YamlConfiguration.loadConfiguration(filesett);

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if (p.hasPermission("bansystem.getbans")) {
			if (args.length == 1) {

				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				String trueorfalse = settings.getString("settings.mysql");
				if (!trueorfalse.equalsIgnoreCase("true")) {
					String getbans = messages.getString("messages.getbans");
					getbans = getbans.replace("%prefix%", BanSystem.prefix);
					getbans = getbans.replace("%player%", target.getName());
					getbans = getbans.replace("%bans%", StatsManager.getBans(target.getName()));

					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getbans));
				} else if (!trueorfalse.equalsIgnoreCase("false")) {
					String getbans = messages.getString("messages.getbans");
					getbans = getbans.replace("%prefix%", BanSystem.prefix);
					getbans = getbans.replace("%player%", target.getName());

					String banns = "" + StatsManager.getBansMySQL(target.getName());

					getbans = getbans.replace("%bans%", banns);

					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getbans));
				}

			}

		} else {
			String noPerm = messages.getString("messages.noPerm");
			noPerm = noPerm.replace("%prefix%", BanSystem.prefix);

			p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
		}

		return false;
	}

}
