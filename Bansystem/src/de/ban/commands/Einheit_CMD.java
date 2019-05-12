package de.ban.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.ban.Main.BanSystem;

public class Einheit_CMD implements CommandExecutor {
	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if (p.hasPermission("bansystem.einheiten")) {
			if (args.length == 0) {

				p.sendMessage("§a§m------§r§eEinheiten§r§a§m------");
				p.sendMessage("§asec §8- §eSekunden");
				p.sendMessage("§amin §8- §eMinuten");
				p.sendMessage("§ahour §8- §eStunden");
				p.sendMessage("§aday §8- §eTage");
				p.sendMessage("§aweek §8- §eWochen");
				p.sendMessage("§a§m------§r§eEinheiten§r§a§m------");

			} else {
				String benutzen = messages.getString("messages.Benutzen.einheit");
				benutzen = benutzen.replace("%prefix%", BanSystem.prefix);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', benutzen));
			}
		} else {
			String noPerm = messages.getString("messages.noPerm");
			noPerm = noPerm.replace("%prefix%", BanSystem.prefix);

			p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
		}
		return false;
	}

}
