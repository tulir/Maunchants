package net.maunium.bukkit.Maunchants.Enchants;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

import net.maunium.bukkit.Maunchants.MauEnchant;
import net.maunium.bukkit.Maunchants.Maunchants;

public class HeatSeekingMissile implements MauEnchant, Listener {
	public static final String MISSILE_META = "MaunchantsHeatSeekingMissile";
	private Maunchants plugin;
	
	public HeatSeekingMissile(Maunchants plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@Override
	public String getDisplayName() {
		return plugin.translate("enchant.heatmissile.itemname");
	}
	
	@EventHandler
	public void onProjectileShoot(ProjectileLaunchEvent evt) {
		if (evt.getEntity().getShooter() instanceof Player && evt.getEntity() instanceof Arrow) {
			Player p = (Player) evt.getEntity().getShooter();
			if (p.getItemInHand().getType().equals(Material.BOW) && p.getItemInHand().getItemMeta().hasDisplayName()
					&& p.getItemInHand().getItemMeta().getDisplayName().equals(getName())) {
				new SeekRunnable((Arrow) evt.getEntity(), 10).start();
			}
		}
	}
	
	public class SeekRunnable implements Runnable {
		private Arrow e;
		private int recalculations, taskId;
		
		public SeekRunnable(Arrow e, int recalculations) {
			this.e = e;
			this.recalculations = recalculations;
		}
		
		public void start() {
			taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, 20, 20);
		}
		
		@Override
		public void run() {
			if (e == null || e.getVelocity().lengthSquared() == 0 || e.isDead() || recalculations < 1) {
				plugin.getServer().getScheduler().cancelTask(taskId);
				return;
			}
			Player nearest = null;
			double nearestDist = Double.MAX_VALUE;
			for (Entity v : e.getNearbyEntities(10, 5, 10)) {
				if (v instanceof Player) {
					double dist = v.getLocation().distanceSquared(e.getLocation());
					if (dist < nearestDist) {
						nearest = (Player) v;
						nearestDist = dist;
					}
				}
			}
			
			if (nearest != null) {
				Vector from = new Vector(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ());
				Vector to = new Vector(nearest.getLocation().getX(), nearest.getLocation().getY(), nearest.getLocation().getZ());
				
				e.setVelocity(to.subtract(from));
			}
			
			recalculations--;
		}
	}
}
