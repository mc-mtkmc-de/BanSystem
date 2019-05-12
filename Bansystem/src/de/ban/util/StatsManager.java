package de.ban.util;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.ban.mysqlutil.MySQL;

public class StatsManager implements Listener {

	static File file = new File("plugins//BanSystem//stats.yml");
	static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	public static void removeBans(Player p, int zahl) {
		int Bans = cfg.getInt(p.getUniqueId() + ".Bans");
		int now = Bans - zahl;
		cfg.set(p.getUniqueId() + ".Bans", Integer.valueOf(now));
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void setBans(String p, int anzahl) {

		int bans = cfg.getInt(Bukkit.getOfflinePlayer(p).getUniqueId() + ".Bans");
		int now = bans + anzahl;
		cfg.set(Bukkit.getOfflinePlayer(p).getUniqueId() + ".Bans", Integer.valueOf(now));
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static String getBans(String p) {
		String Bans = cfg.getString(Bukkit.getOfflinePlayer(p).getUniqueId() + ".Bans").toString();

		return Bans;
	}

	@EventHandler
	public void onJoin1(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (cfg.get(p.getUniqueId() + ".Bans") == null) {
			cfg.set(p.getUniqueId() + ".Bans", Integer.valueOf(0));
			try {
				cfg.save(file);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static int getBansMySQL(String p) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(p);
		UUID uuid = player.getUniqueId();
		try {
			PreparedStatement ps = MySQL.getCon().prepareStatement("SELECT Banns FROM Stats WHERE UUID = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				return rs.getInt("Lose");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return 0;

	}

	public static void updateBansMySQL(OfflinePlayer p, int anzahl, boolean remove) {
		UUID uuid = p.getUniqueId();
		int points = getBansMySQL(p.getName());
		int currentstones;
		if (remove) {
			currentstones = points - anzahl;
		} else {
			currentstones = points + anzahl;
		}

		if (isUserExists(uuid)) {
			try {
				PreparedStatement ps = MySQL.getCon().prepareStatement("UPDATE Stats SET Banns = ? WHERE UUID = ?");
				ps.setInt(1, currentstones);
				ps.setString(2, uuid.toString());
				ps.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				PreparedStatement ps = MySQL.getCon()
						.prepareStatement("INSERT INTO Stats (UUID,Name,Banns) VALUES (?,?,?)");
				ps.setString(1, uuid.toString());
				ps.setString(2, p.getName());
				ps.setInt(3, currentstones);
				ps.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static boolean isUserExists(UUID uuid) {
		try {
			PreparedStatement ps = MySQL.getCon().prepareStatement("SELECT Banns FROM Stats WHERE UUID = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
