package de.ban.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ban.Main.BanSystem;
import de.ban.util.MuteManager;

public class Mute_CMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if (p.hasPermission("BanSystem.mute")) {
			if (args.length == 2) {

				Player target = Bukkit.getPlayer(args[0]);
				if (args[1].equals("1")) {
					MuteManager.tempmutePlayer(target.getUniqueId(), target.getName(), "Test", sender, 60000);
					p.sendMessage(BanSystem.prefix + "§6" + target.getName() + " "
							+ MuteManager.getTimeStamp(target.getUniqueId()));
				} else if (args[1].equals("2")) {
					MuteManager.mutePlayer(target.getUniqueId(), target.getName(), "Test", sender);
					p.sendMessage(BanSystem.prefix + "§6" + target.getName() + " "
							+ MuteManager.getTimeStamp(target.getUniqueId()));
				} else if (args[1].equals("3")) {
					MuteManager.unbanMute(sender, target.getUniqueId(), target.getName());
					p.sendMessage("Entbann!");
				}

			}
		} else {
			p.sendMessage(BanSystem.noPerms);
		}
		return false;
	}

}
