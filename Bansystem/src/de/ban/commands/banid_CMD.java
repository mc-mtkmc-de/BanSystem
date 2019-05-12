package de.ban.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.ban.Main.BanSystem;

public class banid_CMD implements CommandExecutor {
	public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("banid")) {
			if (p.hasPermission("bansystem.admin")) {
				p.sendMessage("§8§m========§e§lBanIds§8§m========");
				p.sendMessage("§a1-§eHacking");
				p.sendMessage("§a2-§eExtrem Hacking");
				p.sendMessage("§a3-§eWerbung");
				p.sendMessage("§a4-§eRassismus");
				p.sendMessage("§a5-§eBugusing");
				p.sendMessage("§a6-§eDuping");
				p.sendMessage("§a7-§eBeleidigung");
				p.sendMessage("§a8-§eAlt-Account");
				p.sendMessage("§a9-§eFaken einer bekannten Person");
				p.sendMessage("§a10-§eAnstößiger Skin oder Name");
				p.sendMessage("§a11-§eRangausnutzung");
				p.sendMessage("§a12-§eRechte Erfragen");
				p.sendMessage("§a13-§eAllgemeiner Regelverstoß");
				p.sendMessage("§a14-§eSupport-Ausnutzung");
				p.sendMessage("§a15-§eBan-umgehung");
				p.sendMessage("§a99-§eBan eines Admins");
				p.sendMessage("§8§m========§e§lBanIds§8§m========");
			} else if (p.hasPermission("bansystem.ban")) {
				p.sendMessage("§8§m========§e§lBanIds§8§m========");
				p.sendMessage("§a1-§eHacking");
				p.sendMessage("§a2-§eExtrem Hacking");
				p.sendMessage("§a3-§eWerbung");
				p.sendMessage("§a4-§eRassismus");
				p.sendMessage("§a5-§eBugusing");
				p.sendMessage("§a6-§eDuping");
				p.sendMessage("§a7-§eBeleidigung");
				p.sendMessage("§a8-§eAlt-Account");
				p.sendMessage("§a9-§eFaken einer bekannten Person.");
				p.sendMessage("§a10-§eAnstößiger Skin oder Name");
				p.sendMessage("§a11-§eRangausnutzung");
				p.sendMessage("§a12-§eRechte Erfragen");
				p.sendMessage("§a13-§eAllgemeiner Regelverstoß");
				p.sendMessage("§a14-§eSupport-Ausnutzung");
				p.sendMessage("§a15-§eBan-umgehung");
				p.sendMessage("§8§m========§e§lBanIds§8§m========");
			} else {
				p.sendMessage(BanSystem.noPerms);
			}
		}
		return true;
	}
}
