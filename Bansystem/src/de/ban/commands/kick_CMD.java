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

public class kick_CMD implements CommandExecutor

{

	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);

	public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kick")) {
			if (p.hasPermission("bansystem.kick")) {
				if (args.length >= 2) {
					Player target = Bukkit.getPlayerExact(args[0]);
					if (target != null) {
						if (!target.hasPermission("bansystem.admin")) {
							String reason = "";
							for (int i = 1; i < args.length; i++) {
								reason = reason + args[i] + " ";
							}
							reason = reason.trim();
							String grund = messages.getString("messages.kicktarget");
							grund = grund.replace("%grund%", reason);
							grund = grund.replace("%prefix%", BanSystem.prefix);
							target.kickPlayer(ChatColor.translateAlternateColorCodes('&', grund));

							String message = messages.getString("messages.kick");
							message = message.replace("%grund%", reason);
							message = message.replace("%prefix%", BanSystem.prefix);
							message = message.replace("%player%", target.getName());
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
						} else {
							String cantkick = messages.getString("messages.dontkick");
							cantkick = cantkick.replace("%prefix%", BanSystem.prefix);
							cantkick = cantkick.replace("%player%", p.getName());
							target.sendMessage(ChatColor.translateAlternateColorCodes('&', cantkick));

						}
					} else {
						String noOn = messages.getString("messages.noOn");

						noOn = noOn.replace("%prefix%", BanSystem.prefix);

						p.sendMessage(ChatColor.translateAlternateColorCodes('&', noOn));
					}
				} else {
					p.sendMessage(
							ChatColor.translateAlternateColorCodes('&', BanSystem.prefix + "&c/kick <spieler> <grund>."));
				}
			} else {
				String noPerm = messages.getString("messages.noPerm");
				noPerm = noPerm.replace("%prefix%", BanSystem.prefix);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
			}
		}
		return true;
	}
}
