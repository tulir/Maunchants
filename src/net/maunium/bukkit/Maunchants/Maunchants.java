package net.maunium.bukkit.Maunchants;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import net.maunium.bukkit.Maunchants.Enchants.HeatSeekingMissile;
import net.maunium.bukkit.Maussentials.Utils.I18n;

public class Maunchants extends JavaPlugin {
	public final String stag = ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Maunchants" + ChatColor.DARK_GREEN + "] " + ChatColor.GRAY,
			errtag = ChatColor.DARK_RED + "[" + ChatColor.RED + "Maunchants" + ChatColor.DARK_RED + "] " + ChatColor.RED;
	private Map<String, MauEnchant> maunchantments;
	private I18n i18n;
	
	@Override
	public void onEnable() {
		long st = System.currentTimeMillis();
		
		maunchantments = new HashMap<String, MauEnchant>();
		
		add(new HeatSeekingMissile(this));
		
		int et = (int) (System.currentTimeMillis() - st);
		getLogger().info("Maunchants v" + getDescription().getVersion() + " by Tulir293 enabled in " + et + "ms.");
	}
	
	public void add(MauEnchant ench) {
		maunchantments.put(ench.getName(), ench);
	}
	
	@Override
	public void onDisable() {
		long st = System.currentTimeMillis();
		
		// TODO Disable code
		
		int et = (int) (System.currentTimeMillis() - st);
		getLogger().info("Maunchants v" + getDescription().getVersion() + " by Tulir293 disabled in " + et + "ms.");
	}
	
	public MauEnchant getEnch(String name) {
		return maunchantments.get(name.toLowerCase(Locale.ENGLISH));
	}
	
	public String translate(String node, Object... arguments) {
		return i18n.translate(node, arguments);
	}
}