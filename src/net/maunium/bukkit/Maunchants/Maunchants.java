package net.maunium.bukkit.Maunchants;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import net.maunium.bukkit.Maussentials.Utils.I18n;

public class Maunchants extends JavaPlugin {
	private final String stag = ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Maunchants" + ChatColor.DARK_GREEN + "] " + ChatColor.GRAY,
			errtag = ChatColor.DARK_RED + "[" + ChatColor.RED + "Maunchants" + ChatColor.DARK_RED + "] " + ChatColor.RED;
	private I18n i18n;
	
	@Override
	public void onEnable() {
		long st = System.currentTimeMillis();
		// TODO If plugin uses config, uncomment the following line.
		// saveDefaultConfig();
		
		// TODO Enable code
		
		int et = (int) (System.currentTimeMillis() - st);
		getLogger().info("Maunchants v" + getDescription().getVersion() + " by Tulir293 enabled in " + et + "ms.");
	}
	
	@Override
	public void onDisable() {
		long st = System.currentTimeMillis();
		
		// TODO Disable code
		
		int et = (int) (System.currentTimeMillis() - st);
		getLogger().info("Maunchants v" + getDescription().getVersion() + " by Tulir293 disabled in " + et + "ms.");
	}
	
	public String translate(String node, Object... arguments) {
		return i18n.translate(node, arguments);
	}
}