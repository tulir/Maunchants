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
		
	}
}
