package de.ban.mysqlutil;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MySQLFile {

	public static File getMySQLFile() {
		return new File("plugins/BanSystem", "mysql.yml");
	}

	public static FileConfiguration getMySQLFileConfiguration() {
		return YamlConfiguration.loadConfiguration(getMySQLFile());
	}

	public static void setStandartMySQL() {
		FileConfiguration cfg = getMySQLFileConfiguration();
		cfg.options().copyDefaults(true);
		cfg.addDefault("username", "root");
		cfg.addDefault("password", "password");
		cfg.addDefault("database", "localhost");
		cfg.addDefault("host", "localhost");
		cfg.addDefault("port", 3306);

		try {
			cfg.save(getMySQLFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void readMySQL() {
		FileConfiguration cfg = getMySQLFileConfiguration();

		MySQL.username = cfg.getString("username");
		MySQL.password = cfg.getString("password");
		MySQL.database = cfg.getString("database");
		MySQL.host = cfg.getString("host");
		MySQL.port = cfg.getString("port");

	}
}

/*
 * cfg.addDefault("username", "root"); cfg.addDefault("password", "password");
 * cfg.addDefault("database", "localhost"); cfg.addDefault("host", "localhost");
 * cfg.addDefault("port", "3306");
 * 
 */
