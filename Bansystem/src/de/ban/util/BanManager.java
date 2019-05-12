package de.ban.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.ban.Main.BanSystem;
import net.md_5.bungee.api.ChatColor;

public class BanManager {

	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);

	public static void unbanPlayer(CommandSender p, UUID uuid, String wen) {
		File file = new File("plugins//BanSystem//gebannte Spieler//" + uuid + ".yml");
		File tempfile = new File("plugins//BanSystem//temporar gebannte Spieler//" + uuid + ".yml");
		if (file.exists()) {
			file.delete();
			Bukkit.getServer().getConsoleSender()
					.sendMessage(BanSystem.prefix + "Der Spieler " + p.getName() + " hat den Spieler " + wen + " entbannt.");
			for (Player all : Bukkit.getOnlinePlayers()) {

				if (all.hasPermission("bansystem.admin")) {
					String message = messages.getString("messages.unban");
					message = message.replace("%player%", p.getName());
					message = message.replace("%ungebannt%", wen);
					message = message.replace("%prefix%", BanSystem.prefix);
					all.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
					all.sendMessage(BanSystem.prefix + "§aDer Spieler §e" + p.getName() + " §ahat den Spieler §e" + wen
							+ " §aentbannt.");
				}
			}
		} else if (tempfile.exists()) {
			tempfile.delete();
			Bukkit.getServer().getConsoleSender().sendMessage(
					BanSystem.prefix + "Der Spieler " + p.getName() + " hat den Spieler e" + wen + " 7entbannt8.");
			for (Player all : Bukkit.getOnlinePlayers()) {

				if (all.hasPermission("bansystem.admin")) {
					String message = messages.getString("messages.unban");
					message = message.replace("%player%", p.getName());
					message = message.replace("%ungebannt%", wen);
					message = message.replace("%prefix%", BanSystem.prefix);
					all.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
				}
			}
		} else {
			String noBanned = messages.getString("messages.noBanned");
			noBanned = noBanned.replace("%prefix%", BanSystem.prefix);
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', noBanned));
		}
	}

	public static String getBanner(UUID uuid) {
		File file = new File("plugins//BanSystem//gebannte Spieler//" + uuid + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		File tempfile = new File("plugins//BanSystem//temporar gebannte Spieler//" + uuid + ".yml");
		FileConfiguration tempcfg = YamlConfiguration.loadConfiguration(tempfile);
		if (file.exists()) {
			return cfg.getString("Informationen.Von");
		}
		if (tempfile.exists()) {
			return tempcfg.getString("Informationen.Von");
		}
		return null;
	}

	public static String getTimeStamp(UUID uuid) {
		File tempfile = new File("plugins//BanSystem//temporar gebannte Spieler//" + uuid + ".yml");
		FileConfiguration tempcfg = YamlConfiguration.loadConfiguration(tempfile);
		if (tempfile.exists()) {
			return tempcfg.getString("Informationen.Datum");
		}
		return null;
	}

	public static String getReason(UUID uuid) {
		File file = new File("plugins//BanSystem//gebannte Spieler//" + uuid + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		File tempfile = new File("plugins//BanSystem//temporar gebannte Spieler//" + uuid + ".yml");
		FileConfiguration tempcfg = YamlConfiguration.loadConfiguration(tempfile);
		if (file.exists()) {
			return cfg.getString("Informationen.Grund");
		}
		if (tempfile.exists()) {
			return tempcfg.getString("Informationen.Grund");
		}
		return null;
	}

	public static void banPlayer(UUID uuid, String playername, String reason, CommandSender von) {
		File file = new File("plugins//BanSystem//gebannte Spieler//" + uuid + ".yml");
		File tempfile = new File("plugins//BanSystem//temporar gebannte Spieler//" + uuid + ".yml");
		File ordner = new File("plugins//BanSystem//");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		tempfile.delete();
		if (!ordner.exists()) {
			ordner.mkdir();
			Bukkit.getServer().getConsoleSender().sendMessage(BanSystem.prefix + "Ordner \"plugins/BanSystem/\" erstellt.");
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException localIOException) {
			}
		}
		cfg.set("Informationen.Von", von.getName());
		cfg.set("Informationen.Grund", reason);
		cfg.set("Informationen.Name", playername);
		try {
			cfg.save(file);
		} catch (IOException localIOException1) {
		}
		Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
		for (Player p : players) {
			if (p.getUniqueId().equals(uuid)) {
				String message = messages.getString("messages.ban");
				message = message.replace("%banner%", cfg.getString("Informationen.Von"));
				message = message.replace("%player%", p.getName());
				message = message.replace("%grund%", cfg.getString("Informationen.Grund"));
				message = message.replace("%prefix%", BanSystem.prefix);
				p.kickPlayer(ChatColor.translateAlternateColorCodes('&', message));

			}
		}
	}

	public static void tempbanPlayer(UUID uuid, String playername, String reason, CommandSender von, long time) {
		File file = new File("plugins//BanSystem//temporar gebannte Spieler//" + uuid + ".yml");
		File tempfile = new File("plugins//BanSystem//gebannte Spieler//" + uuid + ".yml");
		File ordner = new File("plugins//BanSystem//");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		tempfile.delete();
		if (!ordner.exists()) {
			ordner.mkdir();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException localIOException) {
			}
		}
		long end = time * 1000;
		cfg.set("Informationen.Von", von.getName());
		cfg.set("Informationen.Grund", reason);
		cfg.set("Informationen.Name", playername);
		cfg.set("Informationen.MilliSeconds", Long.valueOf(end + System.currentTimeMillis()));
		cfg.set("Informationen.Datum", new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss")
				.format(Long.valueOf(cfg.getLong("Informationen.MilliSeconds"))));
		try {
			cfg.save(file);
		} catch (IOException localIOException1) {
		}
		Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
		for (Player p : players) {
			if (p.getUniqueId().equals(uuid)) {
				String message = messages.getString("messages.tempban");
				message = message.replace("%banner%", cfg.getString("Informationen.Von"));
				message = message.replace("%player%", p.getName());
				message = message.replace("%datum%", cfg.getString("Informationen.Datum"));
				message = message.replace("%grund%", cfg.getString("Informationen.Grund"));
				message = message.replace("%prefix%", BanSystem.prefix);
				p.kickPlayer(ChatColor.translateAlternateColorCodes('&', message));

			}
		}
	}

	public static boolean isbanned(UUID uuid) {
		File file = new File("plugins//BanSystem//temporar gebannte Spieler//" + uuid + ".yml");
		File tempfile = new File("plugins//BanSystem//gebannte Spieler//" + uuid + ".yml");
		File ordner = new File("plugins//BanSystem//");
		if (!ordner.exists()) {
			ordner.mkdirs();
			Bukkit.getConsoleSender().sendMessage("§a§lBanSystem Ordner 'BanSystem' erstellt!");
			return false;
		} else if (!tempfile.exists()) {
			return false;
		} else if (!file.exists()) {
			return false;
		} else {
			return true;
		}

	}
}
