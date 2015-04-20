package net.maunium.bukkit.Maunchants;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import net.maunium.bukkit.Maussentials.Utils.IngameCommandExecutor;

public class CommandMaunchant implements IngameCommandExecutor {
	private Maunchants plugin;
	
	public CommandMaunchant(Maunchants plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(Player p, Command cmd, String label, String[] args) {
		if (args.length > 1) {
			MauEnchant e = plugin.getEnch(args[1]);
			if (e != null) {
				if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("apply")) e.apply(p);
				else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete")) e.remove(p);
				else return false;
			} else p.sendMessage(plugin.errtag + plugin.translate("enchant.notfound"));
			return true;
		} else return false;
	}
}
