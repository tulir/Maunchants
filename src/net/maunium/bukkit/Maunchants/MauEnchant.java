package net.maunium.bukkit.Maunchants;

import java.util.Locale;

public interface MauEnchant {
	public void load(Maunchants plugin);
	
	public void unload();
	
	public boolean isLoaded();
	
	public default String getName() {
		return getClass().getSimpleName().toLowerCase(Locale.ENGLISH);
	}
	
	public String getDisplayName();
}
