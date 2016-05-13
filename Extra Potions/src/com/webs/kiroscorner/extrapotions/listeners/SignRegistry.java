package com.webs.kiroscorner.extrapotions.listeners;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.webs.kiroscorner.extrapotions.ExtraPotionsMain;
import com.webs.kiroscorner.extrapotions.PermCache;
import com.webs.kiroscorner.extrapotions.PotionCustomizer;

public class SignRegistry implements Listener {
 FileConfiguration config = ExtraPotionsMain.getThisPlugin().getConfig();
 String cS = config.getString("CurrencySymbol");
	public static String spre = ChatColor.GOLD +"["+ChatColor.AQUA+"ExtraPotions"+ChatColor.GOLD+"]"+ChatColor.RESET;
	
	public static ArrayList<String> types = new ArrayList<String> ();
	@EventHandler
	public void onSignRegister(SignChangeEvent e) {
		types.add("god");
		types.add("fly");
		types.add("nofall");
		types.add("ghost");
		types.add("fieryaura");
		
		if (!(e.getLine(0).equalsIgnoreCase(ChatColor.stripColor("[extrapotions]")))) return;
		else {
			Player p = (Player) e.getPlayer();
			if ((p.hasPermission(PermCache.PERM_SIGN_FLY)) || (p.hasPermission(PermCache.PERM_SIGN_GOD)) ||
				(p.hasPermission(PermCache.PERM_SIGN_NOFALL)) || (p.hasPermission(PermCache.PERM_SIGN_GHOST)) ||
				(p.hasPermission(PermCache.PERM_SIGN_FIERYAURA))){
						if (!(types.contains(e.getLine(1)))) {
							String typelist = null;
							Iterator<String> it = types.iterator();
							while (it.hasNext()) {
								typelist = typelist + ", " +it.next();
							}
							p.sendMessage(ChatColor.RED + "Invalid Type! Valid Types are : " + typelist);
							return;
						}
						else {
							String type = e.getLine(1);
							if (!(NumberUtils.isNumber(e.getLine(2)))) {
								p.sendMessage(ChatColor.RED + "Line 3 should be the cost in digits! (Don't use any currency signs)");
								return;
							}
							else {
								double cost = Double.parseDouble(e.getLine(2));
								if (type.equalsIgnoreCase("fly")){
									if (!(p.hasPermission(PermCache.PERM_SIGN_FLY))) {p.sendMessage(ChatColor.RED + "You don't have permission to make flying potion signs!"); return;}
									else {
										e.setLine(0, spre);
										e.setLine(1, type);
										e.setLine(2, cS + cost);
										if (PotionCustomizer.FLY_DURATION < 1200) {
										e.setLine(3,(((PotionCustomizer.FLY_DURATION)/20)) + " seconds");
										}else {
										e.setLine(3, (((PotionCustomizer.FLY_DURATION)/20)/60)+ " minutes");
										}
										p.sendMessage(ChatColor.GREEN+"Flying Potion sign succefully created!");
										return;
									}
								}
								if (type.equalsIgnoreCase("god")){
									if (!(p.hasPermission(PermCache.PERM_SIGN_GOD))) {p.sendMessage(ChatColor.RED + "You don't have permission to make god potion signs!"); return;}
									else {
										e.setLine(0, spre);
										e.setLine(1, type);
										e.setLine(2, cS + cost);
										if (PotionCustomizer.GOD_DURATION < 1200) {
											e.setLine(3,(((PotionCustomizer.GOD_DURATION)/20)) + " seconds");
											}else {
											e.setLine(3, (((PotionCustomizer.GOD_DURATION)/20)/60)+ " minutes");
											}
										p.sendMessage(ChatColor.GREEN+"God Potion sign succefully created!");
										return;
									}
								}
								if (type.equalsIgnoreCase("nofall")){
									e.setLine(0, spre);
									e.setLine(1, type);
									e.setLine(2, cS + cost);
									if (PotionCustomizer.NOFALL_DURATION < 1200) {
										e.setLine(3,(((PotionCustomizer.NOFALL_DURATION)/20)) + " seconds");
										}else {
										e.setLine(3, (((PotionCustomizer.NOFALL_DURATION)/20)/60)+ " minutes");
										}
									p.sendMessage(ChatColor.GREEN+"No Fall potions sign succefully created!");
									return;
								}
								if (type.equalsIgnoreCase("ghost")) {
									e.setLine(0, spre);
									e.setLine(1, type);
									e.setLine(2, cS + cost);
									if (PotionCustomizer.GHOST_DURATION < 1200) {
										e.setLine(3,(((PotionCustomizer.GHOST_DURATION)/20)) + " seconds");
										}else {
										e.setLine(3, (((PotionCustomizer.GHOST_DURATION)/20)/60)+ " minutes");
										}
									p.sendMessage(ChatColor.GREEN+"Ghost Potion sign succefully created!");
									return;
								}
								if (type.equalsIgnoreCase("fieryaura")) {
									e.setLine(0, spre);
									e.setLine(1, type);
									e.setLine(2, cS + cost);
									if (PotionCustomizer.GHOST_DURATION < 1200) {
										e.setLine(3,(((PotionCustomizer.FIERYAURA_DURATION)/20)) + " seconds");
										}else {
										e.setLine(3, (((PotionCustomizer.FIERYAURA_DURATION)/20)/60)+ " minutes");
										}
									p.sendMessage(ChatColor.GREEN +"Fiery Aura Potion succefully made!");
									return;
								}
							}
						}
					}
			else {
				p.sendMessage(ChatColor.RED + "Permission not found for creating buy signs!");
				return;
			}
		}
	}
}
