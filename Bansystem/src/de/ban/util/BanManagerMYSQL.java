package de.ban.util;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.ban.mysqlutil.MySQL;
import net.md_5.bungee.api.ChatColor;

public class BanManagerMYSQL {

	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);

	public static void ban(String p, String reason, Long seconds, Player banner) {
		String von = banner.getName();
		@SuppressWarnings("deprecation")
		OfflinePlayer target = Bukkit.getOfflinePlayer(p);
		String player = target.getName();
		UUID uuid = target.getUniqueId();
		long end = 0;

		if (BanManagerMYSQL.isBanned(p)) {
			unban(p);
		}
		if (seconds == -1) {
			end = -1;
		} else {
			long current = System.currentTimeMillis();
			long millis = seconds * 1000;
			end = current + millis;
		}

		String date;

		if (seconds == -1) {
			date = "Permanent!";
		} else {
			date = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss").format(end);
		}

		MySQL.update("INSERT INTO BannedPlayers (Spielername, UUID, Ende, Grund, Von, Datum) VALUES ('" + player + "','"
				+ uuid + "','" + end + "','" + reason + "','" + von + "','" + date + "')");
		if (Bukkit.getPlayer(player) != null) {
			String banned = messages.getString("messages.tempban");
			banned = banned.replace("%grund%", getReason(uuid.toString()));
			banned = banned.replace("%banner%", getBanner(uuid));
			banned = banned.replace("%datum%", getDatum(uuid));
			Bukkit.getPlayer(player).kickPlayer(ChatColor.translateAlternateColorCodes('&', banned));
		}

	}

	public static void unban(String uuid) {
		MySQL.update("DELETE FROM BannedPlayers WHERE UUID='" + uuid + "'");
	}

	public static boolean isBanned(String uuid) {
		ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");
		try {
			return rs.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static String getReason(String allBanned) {
		ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + allBanned + "'");

		try {
			while (rs.next()) {
				return rs.getString("Grund");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public static String getBanner(UUID uuid) {
		ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");

		try {
			while (rs.next()) {
				return rs.getString("Von");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public static Long getEnd(UUID uuid) {
		ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");

		try {
			while (rs.next()) {
				return rs.getLong("Ende");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String getDatum(UUID uuid) {
		ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");

		try {
			while (rs.next()) {
				return rs.getString("Datum");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static List<String> getBannedPlayers() {
		List<String> list = new ArrayList<>();
		ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers");
		try {
			while (rs.next()) {
				list.add(rs.getString("Spielername"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static String getRemainingTime(Player p) {
		UUID uuid = p.getUniqueId();
		long current = System.currentTimeMillis();
		long end = getEnd(uuid);
		long millis = end - current;

		long seconds = 0;
		long minutes = 0;
		long hours = 0;
		long days = 0;
		long weeks = 0;
		while (millis > 1000) {
			millis -= 1000;
			seconds++;
		}
		while (seconds > 60) {
			seconds -= 60;
			minutes++;
		}
		while (minutes > 60) {
			minutes -= 60;
			hours++;
		}
		while (hours > 24) {
			hours -= 24;
			days++;
		}
		while (days > 7) {
			days -= 7;
			weeks++;
		}
		return "§e" + weeks + "Woche(n)" + days + "Tag(e)" + hours + "Stunde(n)" + minutes + "Minute(n)" + seconds
				+ "Sekunde(n)";
	}
}
