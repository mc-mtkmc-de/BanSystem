package de.ban.util;

import java.util.ArrayList;
import java.util.List;

public enum BanUnit {

	SECOND("Sekunde(n)", 1, "sec"), MINUTE("Minute(n)", 60, "min"), HOUR("Stunde(n)", 3600, "hour"), DAY("Tag(e)",
			86400, "day"), WEEK("Woche(n)", 604800, "week");

	private String name;
	private int toSecond;
	private String shortcut;

	private BanUnit(String name, int toSecond, String shortcut) {
		this.name = name;
		this.toSecond = toSecond;
		this.shortcut = shortcut;
	}

	public String getName() {
		return this.name;
	}

	public int getToSecond() {
		return this.toSecond;
	}

	public String getShortcut() {
		return this.shortcut;
	}

	public static List<String> getUnitsAsString() {
		List<String> units = new ArrayList<String>();
		BanUnit[] arrayOfBanUnit;
		int j = (arrayOfBanUnit = values()).length;
		for (int i = 0; i < j; i++) {
			BanUnit unit = arrayOfBanUnit[i];
			units.add(unit.getShortcut().toLowerCase());
		}
		return units;
	}

	public static BanUnit getUnit(String unit) {
		BanUnit[] arrayOfBanUnit;
		int j = (arrayOfBanUnit = values()).length;
		for (int i = 0; i < j; i++) {
			BanUnit units = arrayOfBanUnit[i];
			if (units.getShortcut().toLowerCase().equals(unit.toLowerCase())) {
				return units;
			}
		}
		return null;
	}
}
