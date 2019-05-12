package de.ban.commands;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import de.ban.Main.BanSystem;
import de.ban.util.BanManager;
import de.ban.util.BanManagerMYSQL;

public class checkban_CMD implements CommandExecutor {
	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);
	static File filesett = new File("plugins//BanSystem//settings.yml");
	static YamlConfiguration settings = YamlConfiguration.loadConfiguration(filesett);

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {
		String trueorfalse = settings.getString("settings.mysql");
		if (!trueorfalse.equalsIgnoreCase("false")) {
			if (p.hasPermission("bansystem.checkban")) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("list")) {
						List<String> list = BanManagerMYSQL.getBannedPlayers();
						if (list.size() == 0) {
							String nbp = messages.getString("messages.noBannedPlayers");
							nbp = nbp.replace("%prefix", BanSystem.prefix);
							p.sendMessage(nbp);
							return true;
						}
						p.sendMessage(messages.getString("messages.checkban.bannedPlayer"));
						for (String allBanned : BanManagerMYSQL.getBannedPlayers()) {
							p.sendMessage(BanSystem.prefix + "§e" + allBanned + " §8(§7Grund§8: §r"
									+ BanManagerMYSQL.getReason(allBanned) + "§8)");
						}
						return true;
					}
					String playername = args[0];
					OfflinePlayer target = Bukkit.getOfflinePlayer(playername);
					p.sendMessage(BanSystem.prefix + "§7######### §6§lBan-Infos §7#########");
					p.sendMessage(BanSystem.prefix + "§eName§8: §r" + playername);
					p.sendMessage(BanSystem.prefix + "§eGebannt§8: §r"
							+ (BanManagerMYSQL.isBanned(target.getUniqueId().toString()) ? "§4Ja" : "§2Nein"));
					if (BanManagerMYSQL.isBanned(target.getUniqueId().toString())) {
						p.sendMessage(BanSystem.prefix + "§eGrund§8: §r" + BanManagerMYSQL.getReason(target.getName()));
						p.sendMessage(BanSystem.prefix + "Bis§8: §r" + BanManagerMYSQL.getDatum(target.getUniqueId()));
					}
				}
				return true;
			}
			p.sendMessage(BanSystem.prefix + "§cFalscher Syntax: /check <list/<Player>>");
			return true;
		} else if (!trueorfalse.equalsIgnoreCase("true")) {
			if (p.hasPermission("bansystem.checkban")) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("list")) {
						p.sendMessage(BanSystem.prefix + "§cDieses Feature ist nur für MySQL.");
						return true;
					}
					String playername = args[0];
					OfflinePlayer target = Bukkit.getOfflinePlayer(playername);
					p.sendMessage(BanSystem.prefix + "§7######### §6§lBan-Infos §7#########");
					p.sendMessage(BanSystem.prefix + "§eName§8: §r" + playername);
					p.sendMessage(BanSystem.prefix + "§eGebannt§8: §r"
							+ (BanManager.isbanned(target.getUniqueId()) ? "§4Ja" : "§2Nein"));
					if (BanManager.isbanned(target.getUniqueId())) {
						p.sendMessage(BanSystem.prefix + "§eGrund§8: §r" + BanManager.getReason(target.getUniqueId()));
						p.sendMessage(BanSystem.prefix + "Bis§8: §r" + BanManager.getTimeStamp(target.getUniqueId()));
					}
				}
				return true;
			}
			p.sendMessage(BanSystem.prefix + "§cFalscher Syntax: /check <list/<Player>>");
			return true;
		}
		return false;
	}
}
