package de.ban.commands;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.ban.Main.BanSystem;
import de.ban.util.BanManager;
import de.ban.util.BanManagerMYSQL;
import de.ban.util.StatsManager;
import de.ban.util.Utility;

public class ban_CMD implements CommandExecutor {
	static File file = new File("plugins//BanSystem//messages.yml");
	static YamlConfiguration messages = YamlConfiguration.loadConfiguration(file);
	static File filesett = new File("plugins//BanSystem//settings.yml");
	static YamlConfiguration settings = YamlConfiguration.loadConfiguration(filesett);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("ban")) {
			if (p.hasPermission("bansystem.ban")) {
				if (args.length == 2) {

					UUID uuid = Utility.getUUIDFromName(args[0]);
					@SuppressWarnings("deprecation")
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

					// Extrem-Hacking 5 Wochen.

					if (!p.getName().equals(args[0])) {
						if (args[1].equals("1")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.tempbanPlayer(uuid, args[0], "Hacking", p, 3024000);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Hacking", (long) (3024000), p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}
						}

						if (args[1].equals("2")) {

							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.banPlayer(uuid, args[0], "Extrem Hacking", p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3b");
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Extrem Hacking", (long) -1, p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3b");
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}
						}

						if (args[1].equals("3")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.tempbanPlayer(uuid, args[0], "Werbung", p, 604800);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Werbung", (long) (604800), p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}

						}

						if (args[1].equals("4")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.tempbanPlayer(uuid, args[0], "Rassismus", p, 2073600);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Rassismus", (long) (2073600), p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}
						}

						if (args[1].equals("5")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.tempbanPlayer(uuid, args[0], "Bugusing", p, 864000);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Bugusing", (long) (864000), p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}

						}

						if (args[1].equals("6")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.tempbanPlayer(uuid, args[0], "Duping", p, 864000);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Duping", (long) (864000), p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}

						}

						if (args[1].equals("7")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.tempbanPlayer(uuid, args[0], "Beleidigung", p, 1641600);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Beleidigung", (long) (1641600), p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}

						}

						if (args[1].equals("8")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.banPlayer(uuid, args[0], "Alt-Account", p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3b");
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Alt-Account", (long) -1, p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3b");
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}

						}

						if (args[1].equals("9")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.tempbanPlayer(uuid, args[0], "Faken einer bekannten Person", p, 1209600);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Faken einer bekannten Person", (long) (1209600),
										p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}

						}

						if (args[1].equals("10")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.tempbanPlayer(uuid, args[0], "Anstößiger Skin oder Name", p, 1555200);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Anstößiger Skin oder Name", (long) (1555200), p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}

						}

						if (args[1].equals("11")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.tempbanPlayer(uuid, args[0], "Rangausnutzung", p, 777600);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Rangausnutzung", (long) (777600), p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}

						}

						if (args[1].equals("12")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.tempbanPlayer(uuid, args[0], "Rechte Erfragen", p, 604800);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Rechte Erfragen", (long) (604800), p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}

						}

						if (args[1].equals("13")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.tempbanPlayer(uuid, args[0], "Allgemeiner Regelverstoß", p, 1209600);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Allgemeiner Regelverstoß", (long) (1209600), p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}

						}

						if (args[1].equals("14")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.tempbanPlayer(uuid, args[0], "Support-Ausnutzung", p, 1036800);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Support-Ausnutzung", (long) (1036800), p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}

						}
						if (args[1].equals("15")) {
							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.tempbanPlayer(uuid, args[0], "Ban-umgehung", p, 3456000);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManager.getTimeStamp(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Ban-umgehung", (long) (3456000), p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}

						}
						if (args[1].equals("99")) {

							String trueorfalse = settings.getString("settings.mysql");
							if (!trueorfalse.equalsIgnoreCase("true")) {
								BanManager.banPlayer(uuid, args[0], "Ban eines Admins", p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManager.getReason(uuid));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3b");
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.setBans(args[0], 1);
							} else if (!trueorfalse.equalsIgnoreCase("false")) {
								BanManagerMYSQL.ban(target.getName(), "Ban eines Admins", (long) -1, p);
								String ban1 = messages.getString("messages.ban1");
								ban1 = ban1.replace("%player%", args[0]);
								ban1 = ban1.replace("%prefix%", BanSystem.prefix);
								String ban2 = messages.getString("messages.ban2");
								ban2 = ban2.replace("%grund%", BanManagerMYSQL.getReason(uuid.toString()));
								ban2 = ban2.replace("%prefix%", BanSystem.prefix);
								String ban3 = messages.getString("messages.ban3");
								ban3 = ban3.replace("%datum%", BanManagerMYSQL.getDatum(uuid));
								ban3 = ban3.replace("%prefix%", BanSystem.prefix);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban2));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', ban3));
								StatsManager.updateBansMySQL(target, 1, false);
							}
						}
					} else {
						String sb = messages.getString("messages.selbstbann");
						sb = sb.replace("%prefix%", BanSystem.prefix);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', sb));
					}

				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							BanSystem.prefix + "§4Benutze: /ban <§cspieler§4> <§cid§4>."));
				}

			} else {
				String noPerm = messages.getString("messages.noPerm");
				noPerm = noPerm.replace("%prefix%", BanSystem.prefix);

				p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
			}
		}

		return false;
	}
}
