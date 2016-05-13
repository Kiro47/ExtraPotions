package com.webs.kiroscorner.extrapotions.potionlistener;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;

import com.webs.kiroscorner.extrapotions.ExtraPotionsMain;
import com.webs.kiroscorner.extrapotions.PotionCustomizer;

public class FlyPotionListener implements Listener{
	// UUID, Seconds
	//public static HashMap<UUID, Double> flyMap= new HashMap<UUID,Double>();
	// eventually make it to where it saves time left on disconnect.
	// set to -1 to begin.
	public static Player p = null;
	long dura = (long)PotionCustomizer.FLY_DURATION;
	Plugin pl = ExtraPotionsMain.getThisPlugin();
	boolean extendend;
	boolean ext;
	public ArrayList<UUID> fliers = new ArrayList<UUID>();
	public ArrayList<UUID> sfliers = new ArrayList<UUID>();
	
	
	
	public FlyPotionListener() {
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
	static FlyPotionListener instance = new FlyPotionListener();
	public static FlyPotionListener getInstance() {
		return instance;
	}
	
	
	@EventHandler
	public void onFlyPotionDrink(PlayerItemConsumeEvent e) {
		if (!(e.getItem().equals(PotionCustomizer.flyPotion()))) return;
		else {
			p = e.getPlayer();
			p.setAllowFlight(true);
			p.setFlying(true);
			fliers.add(p.getUniqueId());
		if (PotionCustomizer.FLY_PARTICLES == true) {
			sfliers.add(p.getUniqueId());
		} else {}
		if (extendend = true) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				public void run() {
					p.sendMessage(ChatColor.RED +"1 minute until flying ends!");
					return;
				}
			},(long) (dura - (60*20)));
		} else {}
		if (ext = true){
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				public void run(){
					p.sendMessage(ChatColor.RED +"30 seconds until flying ends!");
					return;
				}
				}, (long)(dura - (10*20)));  
		} else {}
		if (ext = true){
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				public void run(){
					p.sendMessage(ChatColor.RED +"10 seconds until flying ends!");
					return;
				}
				}, (long)(dura - (10*20)));  
		} else {}
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
				public void run(){
					p.sendMessage(ChatColor.RED +"3 seconds until flying ends!");
					p.sendMessage(ChatColor.RED +"Descend now!");
					return;
				}
			}, (long) (dura - (20*3)));
			
			// Crashes or just doesn't work, something to do with the while loop on this thread.
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable(){
				public void run() { 
					p.sendMessage(ChatColor.RED +"Flying has ended.");
					p.setFlying(false);
					p.setAllowFlight(false);
					if (sfliers.contains(p.getUniqueId())) {
						sfliers.remove(p.getUniqueId());
					} else {}
					/*
					if (PotionCustomizer.FLY_SAFE_DROP == true) {
						Location loc = p.getLocation();
						boolean isOnLand = false;
						int y = 144;
						Location tploc = new Location(loc.getWorld(), loc.getX(), y, loc.getZ());
						while (isOnLand == false){
							if (!(tploc.getBlock().getType().equals(Material.AIR))){
								isOnLand = true;
							}
							else {
								y--;
							}
						}
						
					p.teleport(tploc.add(0, 1, 0));
					Bukkit.getLogger().severe("90");
						return;
					}
					*/
				}
			}, (long)dura);
			/////////////////////////
			return;
		}
	}
	}