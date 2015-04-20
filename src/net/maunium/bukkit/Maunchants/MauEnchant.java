package net.maunium.bukkit.Maunchants;

import java.util.Locale;

public interface MauEnchant {
	public default String getName() {
		return getClass().getSimpleName().toLowerCase(Locale.ENGLISH);
	}
	
	public String getDisplayName();
}
