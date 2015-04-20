package net.maunium.bukkit.Maunchants;

import java.util.Locale;

import org.bukkit.entity.Player;

public interface MauEnchant {
	public void apply(Player p);
	
	public void remove(Player p);
	
	public default String getName() {
		return getClass().getSimpleName().toLowerCase(Locale.ENGLISH);
	}
	
	public String getLoreLine();
}
