package net.maunium.bukkit.Maunchants;


public interface MauEnchant {
	public void load(Maunchants plugin);
	
	public void unload();
	
	public boolean isLoaded();
}
