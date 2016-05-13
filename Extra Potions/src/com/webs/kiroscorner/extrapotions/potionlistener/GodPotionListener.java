package com.webs.kiroscorner.extrapotions.potionlistener;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;

import com.webs.kiroscorner.extrapotions.ExtraPotionsMain;
import com.webs.kiroscorner.extrapotions.PotionCustomizer;

public class GodPotionListener implements Listener {

	// UUID, Seconds
	//public static HashMap<UUID, Double> flyMap= new HashMap<UUID,Double>();
	// eventually make it to where it saves time left on disconnect.
	// set to -1 to begin.
	public ArrayList<UUID> gods = new ArrayList<UUID>();
	public ArrayList<UUID> shinyGods = new ArrayList<UUID>();
	public static Player p = null;
	long dura = (long)PotionCustomizer.GOD_DURATION;
	Plugin pl = ExtraPotionsMain.getThisPlugin();
	boolean extendend;
	boolean ext;
	public GodPotionListener() {
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
	static GodPotionListener instance = new GodPotionListener();
	public static GodPotionListener getInstance() {
		return instance;
	}
	
	@EventHandler
	public void onFlyPotionDrink(PlayerItemConsumeEvent e) {
		if (!(e.getItem().equals(PotionCustomizer.godPotion()))) return;
		else {
			p = e.getPlayer();
			p.sendMessage(ChatColor.GREEN + "You begin to feel the pain drain away.");
			gods.add(p.getUniqueId());
			if (PotionCustomizer.GOD_PARTICLES = true) {
				shinyGods.add(p.getUniqueId());
			} else {}
		if (extendend = true) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				public void run() {
					p.sendMessage(ChatColor.RED +"1 minute until your invincibility falters!");
					return;
				}
			},(long) (dura - (60*20)));
		} else {}
		if (ext = true){
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				public void run(){
					p.sendMessage(ChatColor.RED +"30 seconds until your invincibility falters!");
					return;
				}
				}, (long)(dura - (30*20)));  
		} else {}
		if (ext = true){
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				public void run(){
					p.sendMessage(ChatColor.RED +"10 seconds until your invincibility falters!");
					return;
				}
				}, (long)(dura - (10*20)));  
		} else {}
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
				public void run(){
					p.sendMessage(ChatColor.RED +"3 seconds until your invincibility falters!");
					return;
				}
			}, (long) (dura - (20*3)));
			
			// Crashes or just doesn't work, something to do with the while loop on this thread.
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
				public void run() {
					gods.remove(p.getUniqueId());
					p.sendMessage(ChatColor.RED +"You begin to feel the pain and weariness of the world once more.");
					p.sendMessage(ChatColor.RED +"Invincibility has ended.");
					if (shinyGods.contains(p.getUniqueId())) {
						shinyGods.remove(p.getUniqueId());
					} else {}
					return;
				}
			}, (long)dura);
			/////////////////////////
			return;
		}
	}
	
	@EventHandler
	public void onGodlyShield(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		Player p = (Player) e.getEntity();
		if (!(gods.contains(p.getUniqueId()))){
			return;
		}
		else {
			e.setCancelled(true);
			return;
		}
	}

}
