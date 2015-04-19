package net.maunium.bukkit.Maunchants.Enchants;

import net.maunium.bukkit.Maunchants.Maunchants;

public interface MauEnchant {
	public void load(Maunchants plugin);
	
	public void unload();
	
	public boolean isLoaded();
}
