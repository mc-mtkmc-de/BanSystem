package de.ban.commands;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.ban.Main.BanSystem;
import de.ban.util.BanManager;
import de.ban.util.BanManagerMYSQL;
import de.ban.util.BanUnit;

public class Tempban_CMD implements CommandExecutor {

	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);
	static File filesett = new File("plugins//BanSystem//settings.yml");
	static YamlConfiguration settings = YamlConfiguration.loadConfiguration(filesett);

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		String trueorfalse = settings.getString("settings.mysql");
		if (!trueorfalse.equalsIgnoreCase("true")) {
			if (p.hasPermission("bansystem.tempban")) {
				if (args.length >= 4) {
					String playername = args[0];
					OfflinePlayer target = Bukkit.getOfflinePlayer(playername);

					long value;
					try {
						value = Integer.valueOf(args[1]).intValue();
					} catch (NumberFormatException ex) {

						String zahl = messages.getString("messages.Fehler.tempban.zahl");
						zahl = zahl.replace("%prefix%", BanSystem.prefix);
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', zahl));
						return true;
					}

					String unitString = args[2];
					String reason = "";
					for (int i = 3; i < args.length; i++) {
						reason = reason + args[i] + " ";
					}
					List<String> unitList = BanUnit.getUnitsAsString();
					if (unitList.contains(unitString.toLowerCase())) {
						BanUnit unit = BanUnit.getUnit(unitString);
						long seconds = value * unit.getToSecond();
						BanManager.tempbanPlayer(target.getUniqueId(), playername, reason, p, seconds);
						String ban1 = messages.getString("messages.ban1");
						ban1 = ban1.replace("%player%", args[0]);
						ban1 = ban1.replace("%prefix%", BanSystem.prefix);
						String ban2 = messages.getString("messages.ban2");
						ban2 = ban2.replace("%grund%", BanManager.getReason(target.getUniqueId()));
						ban2 = ban2.replace("%prefix%", BanSystem.prefix);
						String ban3 = messages.getString("messages.ban3");
						ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(target.getUniqueId()));
						ban3 = ban3.replace("%prefix%", BanSystem.prefix);
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
						;
						return true;
					}
					String einheit = messages.getString("messages.Fehler.tempban.einheit");
					einheit = einheit.replace("%prefix%", BanSystem.prefix);
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', einheit));
					return true;
				}
				String einheit = messages.getString("messages.Benutzen.tempban");
				einheit = einheit.replace("%prefix%", BanSystem.prefix);
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', einheit));
				return true;

			} else {
				String noPerm = messages.getString("messages.noPerm");
				noPerm = noPerm.replace("%prefix%", BanSystem.prefix);

				p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));

			}
		} else if (!trueorfalse.equalsIgnoreCase("false")) {
			if (sender.hasPermission("bansystem.tempban")) {
				if (args.length >= 4) {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

					String playername = args[0];

					long value;
					try {
						value = Integer.valueOf(args[1]).intValue();
					} catch (NumberFormatException ex) {
						String zahl = messages.getString("messages.Fehler.tempban.zahl");
						zahl = zahl.replace("%prefix%", BanSystem.prefix);
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', zahl));
						return true;
					}

					String unitString = args[2];
					String reason = "";
					for (int i = 3; i < args.length; i++) {
						reason = reason + args[i] + " ";
					}
					List<String> unitList = BanUnit.getUnitsAsString();
					if (unitList.contains(unitString.toLowerCase())) {
						BanUnit unit = BanUnit.getUnit(unitString);
						long seconds = value * unit.getToSecond();
						BanManagerMYSQL.ban(playername, reason, seconds, p);
						String ban1 = messages.getString("messages.ban1");
						ban1 = ban1.replace("%player%", args[0]);
						ban1 = ban1.replace("%prefix%", BanSystem.prefix);
						String ban2 = messages.getString("messages.ban2");
						ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(target.getUniqueId().toString()));
						ban2 = ban2.replace("%prefix%", BanSystem.prefix);
						String ban3 = messages.getString("messages.ban3");
						ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(target.getUniqueId()));
						ban3 = ban3.replace("%prefix%", BanSystem.prefix);
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
						return true;
					}
					String einheit = messages.getString("messages.Fehler.tempban.einheit");
					einheit = einheit.replace("%prefix%", BanSystem.prefix);
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', einheit));
					return true;
				}
				String einheit = messages.getString("messages.Benutzen.tempban");
				einheit = einheit.replace("%prefix%", BanSystem.prefix);
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', einheit));
				return true;
			} else {
				String noPerm = messages.getString("messages.noPerm");
				noPerm = noPerm.replace("%prefix%", BanSystem.prefix);

				p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
			}

		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public static UUID getUUID(Player p) {
		return Bukkit.getOfflinePlayer(p.getName()).getUniqueId();
	}

}
