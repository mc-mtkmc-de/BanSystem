package de.ban.mysqlutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import de.ban.Main.BanSystem;

public class MySQL {

	public static String username;
	public static String password;
	public static String database;
	public static String host;
	public static String port;

	public static Connection con;

	public static void connect() {
		if (!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username,
						password);
				Bukkit.getConsoleSender().sendMessage(BanSystem.prefix + "MySQL verbunden");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void close() {
		if (isConnected()) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Bukkit.getConsoleSender().sendMessage(BanSystem.prefix + "MySQL geschlossen");
		}
	}

	public static boolean isConnected() {
		return con != null;
	}

	public static void createTable() {
		if (isConnected()) {
			try {
				con.createStatement().executeUpdate(
						"CREATE TABLE IF NOT EXISTS BannStats (UUID VARCHAR(100), Name VARCHAR(16), Banns VARCHAR(1000))");
				con.createStatement().executeUpdate(
						"CREATE TABLE IF NOT EXISTS BannedPlayers (Spielername VARCHAR(16), UUID VARCHAR(100), Ende VARCHAR(1000), Grund VARCHAR(100), Von VARCHAR(100), Datum VARCHAR(1000))");
			} catch (SQLException ex) { // Spielername
				ex.printStackTrace();
			}
		}
	}

	public static void update(String qry) {
		if (isConnected()) {
			try {
				con.createStatement().executeUpdate(qry);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static ResultSet getResult(String qry) {
		if (isConnected()) {
			try {
				return con.createStatement().executeQuery(qry);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Connection getCon() {
		return con;
	}
}