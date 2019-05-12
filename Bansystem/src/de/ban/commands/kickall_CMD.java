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

public class kickall_CMD implements CommandExecutor {

	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);

	public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kickall")) {
			if (p.hasPermission("bansystem.kickall")) {
				if (args.length >= 1) {
					for (Player target : Bukkit.getOnlinePlayers()) {
						String reason = "";
						for (int i = 0; i < args.length; i++) {
							reason = reason + args[i] + " ";
						}
						reason = reason.trim();
						String kickall = messages.getString("messages.kickall");
						kickall = kickall.replace("%grund%", reason);
						if (target != p) {
							target.kickPlayer(ChatColor.translateAlternateColorCodes('&', kickall));
						}

					}
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', BanSystem.prefix + "§c/kickall <grund>§7."));
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
