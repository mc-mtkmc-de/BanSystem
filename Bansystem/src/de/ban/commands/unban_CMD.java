package de.ban.commands;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import de.ban.Main.BanSystem;
import de.ban.util.BanManager;
import de.ban.util.BanManagerMYSQL;
import de.ban.util.Utility;

public class unban_CMD implements CommandExecutor {
	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);
	static File filesett = new File("plugins//BanSystem//settings.yml");
	static YamlConfiguration settings = YamlConfiguration.loadConfiguration(filesett);

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {
		if ((cmd.getName().equalsIgnoreCase("unban")) || (cmd.getName().equalsIgnoreCase("pardon"))) {
			String trueorfalse = settings.getString("settings.mysql");
			if (!trueorfalse.equalsIgnoreCase("true")) {

				if (p.hasPermission("bansystem.unban")) {
					if (args.length == 1) {

						UUID uuid = Utility.getUUIDFromName(args[0]);
						BanManager.unbanPlayer(p, uuid, args[0]);
					} else {
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', BanSystem.prefix + "�c/unban <spieler>�8."));
					}
				} else {
					String noPerm = messages.getString("messages.noPerm");
					noPerm = noPerm.replace("%prefix%", BanSystem.prefix);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
				}
			} else if (!trueorfalse.equalsIgnoreCase("false")) {
				if (p.hasPermission("bansystem.unban")) {
					if (args.length == 1) {
						String playername = args[0];
						if (!BanManagerMYSQL.isBanned(Bukkit.getOfflinePlayer(playername).getUniqueId().toString())) {
							p.sendMessage(BanSystem.prefix + "§cDieser Spieler ist nicht gebannt");
							return true;
						}
						BanManagerMYSQL.unban(Bukkit.getOfflinePlayer(playername).getUniqueId().toString());
						p.sendMessage(BanSystem.prefix + "§2Du hast §e" + playername + " §2entbannt");
						return true;
					}
					p.sendMessage(BanSystem.prefix + "§cFalscher Syntax: /unban <Spieler>");
					return true;
				}
			}
		}
		return true;

	}
}
