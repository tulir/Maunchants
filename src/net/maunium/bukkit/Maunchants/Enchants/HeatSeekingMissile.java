package net.maunium.bukkit.Maunchants.Enchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
	public String getLoreLine() {
		return plugin.translate("enchant.loreline.heatmissile");
	}
	
	@Override
	public void apply(Player p) {
		if (p.getItemInHand().getType().equals(Material.BOW)) {
			ItemStack is = p.getItemInHand();
			ItemMeta im = is.getItemMeta();
			List<String> lore;
			if (im.hasLore()) lore = im.getLore();
			else lore = new ArrayList<String>();
			
			if (!lore.contains(getLoreLine())) {
				lore.add(getLoreLine());
				p.sendMessage(plugin.stag + plugin.translate("enchant.applied.heatmissile"));
				im.setLore(lore);
				is.setItemMeta(im);
			} else p.sendMessage(plugin.errtag + plugin.translate("enchant.error.alreadyin"));
		} else p.sendMessage(plugin.errtag + plugin.translate("enchant.error.nobowfound"));
	}
	
	@Override
	public void remove(Player p) {
		if (p.getItemInHand().getType().equals(Material.BOW)) {
			ItemStack is = p.getItemInHand();
			ItemMeta im = is.getItemMeta();
			List<String> lore;
			if (im.hasLore()) lore = im.getLore();
			else {
				p.sendMessage(plugin.errtag + plugin.translate("enchant.error.notin"));
				return;
			}
			
			if (lore.contains(getLoreLine())) {
				lore.remove(getLoreLine());
				p.sendMessage(plugin.stag + plugin.translate("enchant.removed.heatmissile"));
				im.setLore(lore);
				is.setItemMeta(im);
			} else p.sendMessage(plugin.errtag + plugin.translate("enchant.error.notin"));
		} else p.sendMessage(plugin.errtag + plugin.translate("enchant.error.nobowfound"));
	}
	
	@EventHandler
	public void onProjectileShoot(ProjectileLaunchEvent evt) {
		if (evt.getEntity().getShooter() instanceof Player && evt.getEntity() instanceof Arrow) {
			Player p = (Player) evt.getEntity().getShooter();
			if (p.getItemInHand().getType().equals(Material.BOW) && p.getItemInHand().getItemMeta().hasLore()
					&& p.getItemInHand().getItemMeta().getLore().contains(getLoreLine())) {
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
