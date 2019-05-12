package de.ban.Main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.ban.Listeners.Mute_Listener;
import de.ban.Listeners.PlayerLogin_Listener;
import de.ban.commands.Einheit_CMD;
import de.ban.commands.GetBans_CMD;
import de.ban.commands.Mute_CMD;
import de.ban.commands.Reload_CMD;
import de.ban.commands.Tempban_CMD;
import de.ban.commands.ban_CMD;
import de.ban.commands.banid_CMD;
import de.ban.commands.checkban_CMD;
import de.ban.commands.kick_CMD;
import de.ban.commands.kickall_CMD;
import de.ban.commands.remBans_CMD;
import de.ban.commands.unban_CMD;
import de.ban.mysqlutil.MySQL;
import de.ban.mysqlutil.MySQLFile;
import de.ban.util.StatsManager;


public class BanSystem extends JavaPlugin {
	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	static File filesett = new File("plugins//BanSystem//settings.yml");
	static YamlConfiguration settings = YamlConfiguration.loadConfiguration(filesett);

	public static BanSystem plugin;

	public static String prefix = cfg.getString("messages.prefix");
	public static String noPerms = cfg.getString("messages.noPerm");
	public static String notOn = cfg.getString("messages.noOn");
	public static String justP = cfg.getString("messages.OnlyPlayer");

	public void onEnable() {
		Bukkit.getServer().getConsoleSender()
				.sendMessage(ChatColor.translateAlternateColorCodes('&', "§aBanSystem Das Plugin wird aktiviert!"));
		Bukkit.getServer().getConsoleSender().sendMessage(
				ChatColor.translateAlternateColorCodes('&', "§aBanSystem Lade Command u. Events. Progress 0%"));
		onRegister();
		plugin = this;
		Bukkit.getServer().getConsoleSender().sendMessage(
				ChatColor.translateAlternateColorCodes('&', "§aBanSystem Commands u. Events Geladen. Progress 35%"));
		Bukkit.getServer().getConsoleSender().sendMessage(
				ChatColor.translateAlternateColorCodes('&', "§aBanSystem Lade Settings Config u MySQL. Progress 35%"));
		loadSettingsConfig();
		MySQLFile.setStandartMySQL();
		MySQLFile.readMySQL();

		String trueorfalse = settings.getString("settings.mysql");
		if (!trueorfalse.equalsIgnoreCase("true")) {

		} else if (!trueorfalse.equalsIgnoreCase("false")) {
			MySQL.connect();
			MySQL.createTable();
			Bukkit.getServer().getConsoleSender().sendMessage("§aBanSystem MySQL verbunden");
		}
		Bukkit.getServer().getConsoleSender().sendMessage(
				ChatColor.translateAlternateColorCodes('&', "§aBanSystem Settings Config geladen. Progress 75%"));
		Bukkit.getServer().getConsoleSender().sendMessage(
				ChatColor.translateAlternateColorCodes('&', "§aBanSystem Lade Messages Config. Progress 75%"));
		loadMessageConfig();
		Bukkit.getServer().getConsoleSender().sendMessage(
				ChatColor.translateAlternateColorCodes('&', "§aBanSystem Messages Config geladen. Progress 100%"));
		Bukkit.getServer().getConsoleSender()
				.sendMessage(ChatColor.translateAlternateColorCodes('&', "§aBanSystem Plugin ErrorFrei Geladen!"));

	}

	public void onDisable() {
		Bukkit.getServer().getConsoleSender()
				.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "§bDas Plugin wurde Deaktiviert!"));
	}

	public void onRegister() {
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerLogin_Listener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new StatsManager(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Mute_Listener(), this);

		getCommand("rembans").setExecutor(new remBans_CMD());
		getCommand("getbans").setExecutor(new GetBans_CMD());
		getCommand("ban").setExecutor(new ban_CMD());
		getCommand("banid").setExecutor(new banid_CMD());
		getCommand("checkban").setExecutor(new checkban_CMD());
		getCommand("kick").setExecutor(new kick_CMD());
		getCommand("kickall").setExecutor(new kickall_CMD());
		getCommand("unban").setExecutor(new unban_CMD());
		getCommand("tempban").setExecutor(new Tempban_CMD());
		getCommand("bansystem").setExecutor(new Reload_CMD());
		getCommand("einheiten").setExecutor(new Einheit_CMD());
		getCommand("mute").setExecutor(new Mute_CMD());
	}

	public static void loadSettingsConfig() {
		File file = new File("plugins//BanSystem//settings.yml");
		File ordner = new File("plugins//BanSystem");
		if (!ordner.exists()) {
			ordner.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			cfg.options().header("Hier kannst du das Plugin einstellen.");
			cfg.addDefault("settings.mysql", "false");
			cfg.options().copyDefaults(true);
			try {
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Bukkit.reload();
		}
	}

	public static void loadMessageConfig() {
		File file = new File("plugins//BanSystem//messages.yml");
		File ordner = new File("plugins//BanSystem");
		if (!ordner.exists()) {
			ordner.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

			cfg.options().header("#Hier kannst du die Narichten einstellen.");
			cfg.options().header("");
			cfg.addDefault("messages.prefix", "&a&lBanSystem&r ");
			cfg.addDefault("messages.noPerm", "%prefix% &cDazu hast du keine Rechte!");
			cfg.addDefault("messages.noOn", "%prefix% &cDieser Spieler ist nicht Online!");
			cfg.addDefault("messages.OnlyPlayer", "%prefix% &cDazu musst du ein Spieler sein!");
			cfg.addDefault("messages.getbans", "%prefix% &aDer Spieler &e%player% &ahat &e%bans% &abans.");
			cfg.addDefault("messages.rembans", "%prefix% &aDu hast den Spieler &e%player% %bans% &aentfernt.");
			cfg.addDefault("messages.kickall", "&a&lAlle Spieler wurden vom Netzwerk gekickt \n Grund: &e%grund%");
			cfg.addDefault("messages.kicktarget",
					"%prefix% &a&lDu wurdest vom Netzwerk gekickt \n &r&aGrund: &e%grund%");
			cfg.addDefault("messages.kick", "%prefix% &aDu hast den Spieler &e%player% &afür &e%grund% gekickt.");
			cfg.addDefault("messages.dontkick", "%prefix% &aDer Spieler %player% wollte dich Kicken!");
			cfg.addDefault("messages.unban", "%prefix% &aDer Spieler %player% hat den Spieler %ungebannt% entbannt!");
			cfg.addDefault("messages.noBanned", "%prefix% &cDer Spieler ist nicht Gebannt!");
			cfg.addDefault("messages.noBannedPlayers", "%prefix% &cEs sind aktuell keine Spieler gebannt!!");
			cfg.addDefault("messages.neugeladen", "%prefix% &aDas Plugin wurde neugeladen!");
			cfg.addDefault("messages.selbstbann", "%prefix% &aDu kannst dich nicht selber Bannen!");
			cfg.addDefault("messages.checkban.banliste", "&7&m------&r&a&lBanListe&r&7&m------");
			cfg.addDefault("messages.checkban.bannedPlayer", "&a§l%player%");
			cfg.addDefault("messages.mute.noBanned", "%prefix% &cDer Spieler ist nicht Gemutet!");
			cfg.addDefault("messages.mute.unban",
					"%prefix% &aDer Spieler %player% hat den Spieler %ungebannt% entmutet!");

			cfg.addDefault("messages.mute.unmute",
					"%prefix% &aDer Spieler &e%banner% &ahat den Spieler &e%player% &aentmutet.");
			cfg.addDefault("messages.Benutzen.checkban", "%prefix% &cBenutze /checkban <list/<Spieler>>");
			cfg.addDefault("messages.Benutzen.einheit", "%prefix% &cBenutze /einheit");
			cfg.addDefault("messages.Benutzen.tempban",
					"%prefix% &cBenutze /tempban <Spieler> <Zalenwert> <Einheit> <Grund>!");
			cfg.addDefault("messages.Benutzen.rembans", "%prefix% &cBenutze /rembans <Spieler> <Zahl>!");

			cfg.addDefault("messages.Fehler.tempban.einheit", "%prefix% &cDiese <Einheit> existiert nicht!");
			cfg.addDefault("messages.Fehler.tempban.zahl", "%prefix% &c<Zahlenwert> muss eine Zahl sein!");

			cfg.addDefault("messages.mute1", "&l&8######%prefix%&l&8###### ");
			cfg.addDefault("messages.mute2", "&8Du hast den Spieler %player% Gemutet ");
			cfg.addDefault("messages.mute3", "&8Grund: %grund%");
			cfg.addDefault("messages.mute4", "&8Bis: %datum%");
			cfg.addDefault("messages.mute4", "&8Bis: &6&lPERMANENT");
			cfg.addDefault("messages.mute5", "&l&8######%prefix%&l&8###### ");
			cfg.addDefault("messages.ban",
					"&aDu wurdest vom Netzwerk gebannt!\n &a Grund&7: &e \n %grund% \n \n &aVon&7: &e %banner% \n &aAblauf&7: &e PERMANENT \n &aEntbannungsantra&7: &eDeine.webside.de");
			cfg.addDefault("messages.tempban",
					"&aDu wurdest vom Netzwerk gebannt!\n &a Grund&7: &e \n %grund% \n &aVon&7: &e %banner% \n &aAblauf&7: &e %datum% &aUhr \n &aEntbannungsantrag&7: &eDeine.webside.de");
			cfg.addDefault("messages.ban1", "%prefix% &aDu hast den Spieler &e%player% &agebannt. ");
			cfg.addDefault("messages.ban2", "%prefix% &aGrund: &e%grund%&a.");
			cfg.addDefault("messages.ban3", "%prefix% &aBis: &e%datum%&a.");
			cfg.addDefault("messages.ban3b", "%prefix% &aFür: &4PERMANENT.");

			cfg.options().copyDefaults(true);
			try {
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Bukkit.reload();
		}
	}

	public static BanSystem getPlugin() {
		return plugin;
	}

}
