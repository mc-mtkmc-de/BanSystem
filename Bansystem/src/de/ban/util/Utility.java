package de.ban.util;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Utility {
	public static UUID getUUIDFromName(String name) {
		Player p = Bukkit.getPlayer(name);
		if (p != null) {
			return p.getUniqueId();
		}
		@SuppressWarnings("deprecation")
		OfflinePlayer op = Bukkit.getOfflinePlayer(name);
		if (op != null) {
			return op.getUniqueId();
		}
		return null;
	}

	public static String millisToTimeString(long arg0) {
		long days = TimeUnit.MILLISECONDS.toDays(arg0);
		long hours = TimeUnit.MILLISECONDS.toHours(arg0) - TimeUnit.MILLISECONDS.toDays(arg0) * 24L;
		long min = TimeUnit.MILLISECONDS.toMinutes(arg0) - TimeUnit.MILLISECONDS.toHours(arg0) * 60L;
		long sec = TimeUnit.MILLISECONDS.toSeconds(arg0) - TimeUnit.MILLISECONDS.toMinutes(arg0) * 60L;
		if (((long) days == 0) && ((long) hours == 0) && ((long) min == 0)) {
			if (sec != 1L) {
				return "" + sec + "Sekunden";
			}
			return "eine Sekunde";
		}
		if (((long) days == 0) && ((long) hours == 0)) {
			if (min != 1L) {
				return "" + min + " Minuten";
			}
			return "eine Minute";
		}
		if ((long) days == 0) {
			if (hours != 1L) {
				return "" + hours + "Stunden";
			}
			return "eine Stunde";
		}
		if (days != 1L) {
			return days + " Tage";
		}
		return "einen Tag";
	}
}
