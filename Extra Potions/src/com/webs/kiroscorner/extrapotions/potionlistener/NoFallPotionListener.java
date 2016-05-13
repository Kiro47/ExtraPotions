package com.webs.kiroscorner.extrapotions.potionlistener;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;

import com.webs.kiroscorner.extrapotions.ExtraPotionsMain;
import com.webs.kiroscorner.extrapotions.PotionCustomizer;

public class NoFallPotionListener implements Listener{


	// UUID, Seconds
	//public static HashMap<UUID, Double> flyMap= new HashMap<UUID,Double>();
	// eventually make it to where it saves time left on disconnect.
	// set to -1 to begin.
	public ArrayList<UUID> falR = new ArrayList<UUID>();
	public ArrayList<UUID> falRP = new ArrayList<UUID>();
	public static Player p = null;
	long dura = (long)PotionCustomizer.NOFALL_DURATION;
	Plugin pl = ExtraPotionsMain.getThisPlugin();
	boolean extendend;
	boolean ext;
	public NoFallPotionListener() {
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
	static NoFallPotionListener instance = new NoFallPotionListener();
	public static NoFallPotionListener getInstance() {
		return instance;
	}
	
	@EventHandler
	public void onFlyPotionDrink(PlayerItemConsumeEvent e) {
		if (!(e.getItem().equals(PotionCustomizer.nofallPotion()))) return;
		else {
			p = e.getPlayer();
			p.sendMessage(ChatColor.GREEN + "You feel a light wave of wind pushing up upon you.");
			falR.add(p.getUniqueId());
			if (PotionCustomizer.NOFALL_PARTICLES = true) {
				falRP.add(p.getUniqueId());
			} else {}
		if (extendend = true) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				public void run() {
					p.sendMessage(ChatColor.RED +"1 Minute until the wind loses its force!");
					return;
				}
			},(long) (dura - (60*20)));
		} else {}
		if (ext = true){
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				public void run(){
					p.sendMessage(ChatColor.RED +"30 seconds until the wind loses its force!");
					return;
				}
				}, (long)(dura - (30*20)));  
		} else {}
		if (ext = true){
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				public void run(){
					p.sendMessage(ChatColor.RED +"10 seconds until the wind loses its force!");
					return;
				}
				}, (long)(dura - (10*20)));  
		} else {}
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
				public void run(){
					p.sendMessage(ChatColor.RED +"3 seconds until the wind loses its force!");
					return;
				}
			}, (long) (dura - (20*3)));
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
				public void run() {
					falR.remove(p.getUniqueId());
					p.sendMessage(ChatColor.RED +"The presence of the wind has left you.");
					p.sendMessage(ChatColor.RED +"Fall damage nullifier has ended.");
					if (falRP.contains(p.getUniqueId())) {
						falRP.remove(p.getUniqueId());
					} else {}
					return;
				}
			}, (long)dura);
			return;
		}
	}
	
	@EventHandler
	public void onFeatherFallPotion(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		Player p = (Player) e.getEntity();
		if (!(falR.contains(p.getUniqueId()))){
			return;
		}
		else {
		if (!(e.getCause().equals(DamageCause.FALL))) return;	
		else {
			e.setCancelled(true);
			return;
		}
		}
	}


}
