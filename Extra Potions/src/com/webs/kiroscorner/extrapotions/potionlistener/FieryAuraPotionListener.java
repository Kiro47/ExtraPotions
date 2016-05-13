package com.webs.kiroscorner.extrapotions.potionlistener;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;

import com.webs.kiroscorner.extrapotions.ExtraPotionsMain;
import com.webs.kiroscorner.extrapotions.PotionCustomizer;

public class FieryAuraPotionListener implements Listener{

	// UUID, Seconds
	//public static HashMap<UUID, Double> flyMap= new HashMap<UUID,Double>();
	// eventually make it to where it saves time left on disconnect.
	// set to -1 to begin.
	public static Player p = null;
	long dura = (long)PotionCustomizer.FIERYAURA_DURATION;
	Plugin pl = ExtraPotionsMain.getThisPlugin();
	boolean extendend;
	boolean ext;
	public ArrayList<UUID> burners = new ArrayList<UUID>();
	public ArrayList<UUID> flamers = new ArrayList<UUID>();
	
	
	public FieryAuraPotionListener() {
		if (dura > 60) {
			extendend = true;
		}
		else {
			extendend = false;
		}
		if (dura > 10) {
			ext = true;
		}
		else {
			ext=false;
		}

	}
	static FieryAuraPotionListener instance = new FieryAuraPotionListener();
	public static FieryAuraPotionListener getInstance() {
		return instance;
	}
	
	
	@EventHandler
	public void onFireAuraPotionDrink(PlayerItemConsumeEvent e) {
		if (!(e.getItem().equals(PotionCustomizer.fireAuraPotion()))) return;
		else {
			p = e.getPlayer();
			p.sendMessage(ChatColor.GREEN + "Your blood begins to boil and you can feel the heat radiating from you.");
			burners.add(p.getUniqueId());
		if (PotionCustomizer.FIERYAURA_PARTICLES == true) {
			flamers.add(p.getUniqueId());
		} else {}
		if (extendend = true) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				public void run() {
					p.sendMessage(ChatColor.RED +"The heat slowly disperses, 1 minute until it cools down.");
					return;
				}
			},(long) (dura - (60*20)));
		} else {}
		if (ext = true){
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				public void run(){
					p.sendMessage(ChatColor.RED +"The heat slowly disperses, 30 seconds until it cools down.");
					return;
				}
				}, (long)(dura - (10*20)));  
		} else {}
		if (ext = true){
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				public void run(){
					p.sendMessage(ChatColor.RED +"The heat slowly disperses, 10 seconds until it cools down.");
					return;
				}
				}, (long)(dura - (10*20)));  
		} else {}
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
				public void run(){
					p.sendMessage(ChatColor.RED +"The heat slowly disperses, 3 seconds until it cools down.");
					return;
				}
			}, (long) (dura - (20*3)));
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
				public void run() { 
					p.sendMessage(ChatColor.RED +"Your blood returns to its normal state.");
					burners.remove(p.getUniqueId());
					if (flamers.contains(p.getUniqueId())) {
						flamers.remove(p.getUniqueId());
					} else {}
				}
			}, (long)dura);
			return;
		}
	}
	@EventHandler
	public void onFieryAura(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)){ return;}
		Player p = (Player) e.getEntity();
		if (!(burners.contains(p.getUniqueId()))){ return;}
		else{
			EntityType dm = e.getDamager().getType();
			if ( (dm.equals(EntityType.AREA_EFFECT_CLOUD)) || (dm.equals(EntityType.ENDER_PEARL)) || (dm.equals(EntityType.PRIMED_TNT)) || (dm.equals(EntityType.COMPLEX_PART)) 
					|| (dm.equals(EntityType.DRAGON_FIREBALL)) || (dm.equals(EntityType.ENDER_CRYSTAL)) || (dm.equals(EntityType.MINECART_TNT)) || (dm.equals(EntityType.FALLING_BLOCK)) 
					|| (dm.equals(EntityType.LIGHTNING)) || (dm.equals(EntityType.UNKNOWN)) ) {return;}
			else if (e.getDamager() instanceof Projectile) {
				Projectile pr = (Projectile) e.getDamager();
				if (PotionCustomizer.FIERYAURA_RANGED == true) {
					if (!(pr.getShooter() instanceof Entity)){return;}
					((Entity) pr.getShooter()).setFireTicks( (int)PotionCustomizer.FIERYAURA_FLAME_TICKS);
					return;
				}
				else {
					return;
				}
			}
			else {
				e.getDamager().setFireTicks((int) PotionCustomizer.FIERYAURA_FLAME_TICKS);
				return;
			}
		}
		
	}
	
}
