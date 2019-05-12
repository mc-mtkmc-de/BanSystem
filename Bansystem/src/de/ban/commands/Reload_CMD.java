package de.ban.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.ban.Main.BanSystem;

public class Reload_CMD implements CommandExecutor {

	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);

	private static BanSystem main;

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if (p.hasPermission("bansystem.admin")) {
			if (args.length == 1) {

				if (args[0].equalsIgnoreCase("reload")) {

					main.loadMessageConfig();
					main.loadSettingsConfig();

					String geladen = messages.getString("messages.neugeladen");
					geladen = geladen.replace("%prefix%", BanSystem.prefix);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', geladen));

				} else if (args[0].equalsIgnoreCase("help")) {
					sendHelp(p);

				} else {
					sendHelp(p);
				}

			} else {
				sendHelp(p);
			}

		} else {
			p.sendMessage(BanSystem.noPerms);
		}

		return false;
	}

	public void sendHelp(Player p) {
		p.sendMessage("§7------§aBanSystem-Help§7------");
		p.sendMessage("§a/bansystem reload");
		p.sendMessage("§a/bansystem help");
		p.sendMessage("§7------§aBanSystem-Help§7------");
	}

}
