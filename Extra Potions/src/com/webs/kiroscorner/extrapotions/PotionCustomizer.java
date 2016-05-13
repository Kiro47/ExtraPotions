package com.webs.kiroscorner.extrapotions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

@SuppressWarnings("deprecation")
public class PotionCustomizer {

	private static FileConfiguration config = ExtraPotionsMain.getThisPlugin().getConfig();
	// these are in ticks
	// the things themselves are in seconds
	public static String FLY_NAME = ChatColor.AQUA + "Potion Of Flight!";
	public static double FLY_DURATION = config.getDouble("FlyPotion.lengthInSeconds") * 20;
	public static boolean FLY_SAFE_DROP= config.getBoolean("FlyPotion.safeLanding");
	public static boolean FLY_PARTICLES =  config.getBoolean("FlyPotion.particles");
	
	public static String GOD_NAME = ChatColor.GOLD + "Potion Of the Gods!";
	public static double GOD_DURATION = config.getDouble("GodPotion.lengthInSeconds") * 20;
	public static boolean GOD_PARTICLES =  config.getBoolean("GodPotion.particles");
	
	public static String NOFALL_NAME = ChatColor.AQUA + "Potion of the chicken?!";
	public static double NOFALL_DURATION = config.getDouble("NoFallPotion.lengthInSeconds") * 20;
	public static boolean NOFALL_PARTICLES =  config.getBoolean("NoFallPotion.particles");
	
	public static String GHOST_NAME = ChatColor.GRAY + "Potion of Fallen Souls";
	public static double GHOST_DURATION = config.getDouble("Ghost.lengthInSeconds") * 20;
	public static boolean GHOST_PARTICLES =  config.getBoolean("Ghost.particles");
	
	public static String FIREYAURA_NAME = ChatColor.RED + "Potion Of The Efreet";
	public static double FIERYAURA_DURATION = config.getDouble("FieryAuraPotion.lengthInSeconds") * 20;
	public static boolean FIERYAURA_PARTICLES =  config.getBoolean("FieryAuraPotion.particles");
	public static boolean FIERYAURA_RANGED = config.getBoolean("FieryAuraPotion.ranged");
	public static double FIERYAURA_FLAME_TICKS = config.getDouble("FieryAuraPotion.burnSeconds") *20;
	
	public static ItemStack flyPotion() {
		ItemStack pot = new ItemStack(Material.POTION);
		Potion flyPot = new Potion(PotionType.SPEED);
		flyPot.apply(pot);
		pot.setAmount(1);
		ItemMeta im = pot.getItemMeta();
		PotionMeta pm = (PotionMeta) im;
		pm.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 0, 0, false, false), true);
		pm.setDisplayName(FLY_NAME);
		List<String> ls = new ArrayList<String>();
		ls.add(ChatColor.GREEN + "A potion that allows you to fly freely!");
		ls.add(ChatColor.DARK_RED + "WARNING: You will recieve a warning");
		ls.add(ChatColor.DARK_RED +"30 seconds and one minute before the effect ends.");
		ls.add(ChatColor.DARK_RED + "After this you shall fall from the sky!");
		ls.add(ChatColor.LIGHT_PURPLE + "Lasts for " + ((FLY_DURATION /20)/60)  + " minutes!");
		pm.setLore(ls);
		pm.addEnchant(Enchantment.ARROW_INFINITE, 0, true);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		pm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	 	pot.setItemMeta(pm);
	 
		return pot;
	}
	 public static ItemStack godPotion() {
		ItemStack pot = new ItemStack(Material.POTION);
		Potion flyPot = new Potion(PotionType.INSTANT_HEAL);
		flyPot.apply(pot);
		pot.setAmount(1);
		ItemMeta im = pot.getItemMeta();
		PotionMeta pm = (PotionMeta) im;
		pm.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 0, 0, false, false), true);
		pm.setDisplayName(GOD_NAME);
		List<String> ls = new ArrayList<String>();
		ls.add(ChatColor.GREEN + "A potion that allows you to be invincible!");
		ls.add(ChatColor.DARK_RED + "WARNING: You will recieve a warning");
		ls.add(ChatColor.DARK_RED +"30 seconds and one minute before the effect ends.");
		ls.add(ChatColor.DARK_RED + "After this you shall recieve a debuff!!");
		ls.add(ChatColor.LIGHT_PURPLE + "Lasts for " + ((GOD_DURATION /20)/60)  + " minutes!");
		pm.setLore(ls);
		pm.addEnchant(Enchantment.ARROW_INFINITE, 0, true);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		pm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	 	pot.setItemMeta(pm);
	 
		return pot;
	
	}
	public static ItemStack ghostPotion() {
		ItemStack pot = new ItemStack(Material.POTION);
		Potion flyPot = new Potion(PotionType.INVISIBILITY);
		flyPot.apply(pot);
		pot.setAmount(1);
		ItemMeta im = pot.getItemMeta();
		PotionMeta pm = (PotionMeta) im;
		pm.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 0, 0, false, false), true);
		pm.setDisplayName(GHOST_NAME);
		List<String> ls = new ArrayList<String>();
		ls.add(ChatColor.GREEN + "A potion that allows you to be invincible!");
		ls.add(ChatColor.DARK_RED + "WARNING: You will recieve a warning");
		ls.add(ChatColor.DARK_RED +"30 seconds and one minute before the effect ends.");
		ls.add(ChatColor.DARK_RED + "After this you shall recieve a debuff!!");
		ls.add(ChatColor.LIGHT_PURPLE + "Lasts for " + ((GHOST_DURATION/20) /60)  + " minutes!");
		pm.setLore(ls);
		pm.addEnchant(Enchantment.ARROW_INFINITE, 0, true);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		pm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	 	pot.setItemMeta(pm);
	 
		return pot;
	
	}
	public static ItemStack nofallPotion() {
		ItemStack pot = new ItemStack(Material.POTION);
		Potion flyPot = new Potion(PotionType.SLOWNESS);
		flyPot.apply(pot);
		pot.setAmount(1);
		ItemMeta im = pot.getItemMeta();
		PotionMeta pm = (PotionMeta) im;
		pm.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 0, 0, false, false), true);
		pm.setDisplayName(NOFALL_NAME);
		List<String> ls = new ArrayList<String>();
		ls.add(ChatColor.GREEN + "A potion that allows you to be immune to fall damage!");
		ls.add(ChatColor.DARK_RED + "WARNING: You will recieve a warning");
		ls.add(ChatColor.DARK_RED +"30 seconds and one minute before the effect ends.");
		ls.add(ChatColor.DARK_RED + "After this you shall recieve a debuff!!");
		ls.add(ChatColor.LIGHT_PURPLE + "Lasts for " + ((NOFALL_DURATION/20)/60)  + " minutes!");
		pm.setLore(ls);
		pm.addEnchant(Enchantment.ARROW_INFINITE, 0, true);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		pm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	 	pot.setItemMeta(pm);
	 
		return pot;
	
	}
	public static ItemStack fireAuraPotion() {
		ItemStack pot = new ItemStack(Material.POTION);
		Potion flyPot = new Potion(PotionType.FIRE_RESISTANCE);
		flyPot.apply(pot);
		pot.setAmount(1);
		ItemMeta im = pot.getItemMeta();
		PotionMeta pm = (PotionMeta) im;
		pm.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 0, 0, false, false), true);
		pm.setDisplayName(FIREYAURA_NAME);
		List<String> ls = new ArrayList<String>();
		ls.add(ChatColor.GREEN + "A potion that allows you to be cast a fiery aura!");
		ls.add(ChatColor.GREEN + "towards those who attack you!.");
		ls.add(ChatColor.DARK_RED + "WARNING: You will recieve a warning");
		ls.add(ChatColor.DARK_RED +"30 seconds and one minute before the effect ends.");
		ls.add(ChatColor.DARK_RED + "After this you shall recieve a debuff!!");
		ls.add(ChatColor.LIGHT_PURPLE + "Lasts for " + ((FIERYAURA_DURATION/20) /60)  + " minutes!");
		pm.setLore(ls);
		pm.addEnchant(Enchantment.ARROW_INFINITE, 0, true);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		pm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	 	pot.setItemMeta(pm);
	 
		return pot;
	}
	
	@SuppressWarnings("deprecation")
	public static void transact(Player p, double cost, String potionType) {
		Economy econ = ExtraPotionsMain.getEconomy();
		EconomyResponse er = econ.withdrawPlayer(p.getName(), cost);
		
		if (potionType.equalsIgnoreCase("fieryaura")) {
			if (er.transactionSuccess() == false) {
				p.sendMessage(ChatColor.RED + "You cannot afford this!");
				return;
			}
			else {
				if (p.getInventory().firstEmpty() == -1){
					p.sendMessage(ChatColor.RED + "Your inventory is full!");
					return;
				}
				else {
					p.getInventory().addItem(fireAuraPotion());
					p.sendMessage(ChatColor.GREEN +"You have succefully bought a Fiery Aura Potion for $" +cost );
					return;
				}
			}
		}
		else if (potionType.equalsIgnoreCase("nofall")) {
			if (er.transactionSuccess() == false) {
				p.sendMessage(ChatColor.RED + "You cannot afford this!");
				return;
			}
			else {
				if (p.getInventory().firstEmpty() == -1){
					p.sendMessage(ChatColor.RED + "Your inventory is full!");
					return;
				}
				else {
					p.getInventory().addItem(nofallPotion());
					p.sendMessage(ChatColor.GREEN +"You have succefully bought a No Fall Potion for $" +cost );
					return;
				
				}
				
			}	
		}
		else if (potionType.equalsIgnoreCase("ghost")) {
			if (er.transactionSuccess() == false) {
				p.sendMessage(ChatColor.RED + "You cannot afford this!");
				return;
			}
			else {
				if (p.getInventory().firstEmpty() == -1){
					p.sendMessage(ChatColor.RED + "Your inventory is full!");
					return;
				}
				else {
					p.getInventory().addItem(ghostPotion());
					p.sendMessage(ChatColor.GREEN +"You have succefully bought a Ghost Potion for $" +cost );
					return;
				
					
				}
			}
		}
		else if (potionType.equalsIgnoreCase("god")) {
			if (er.transactionSuccess() == false) {
				p.sendMessage(ChatColor.RED + "You cannot afford this!");
				return;
			}
			else {
				if (p.getInventory().firstEmpty() == -1){
					p.sendMessage(ChatColor.RED + "Your inventory is full!");
					return;
				}
				else {
					p.getInventory().addItem(godPotion());
					p.updateInventory();
					p.sendMessage(ChatColor.GREEN +"You have succefully bought a God Potion for $" +cost );
					return;
				}
				
			}
		}
		else if (potionType.equalsIgnoreCase("fly")) {
			if (er.transactionSuccess() == false) {
				p.sendMessage(ChatColor.RED + "You cannot afford this!");
				return;
			}
			else {
				if (p.getInventory().firstEmpty() == -1){
					p.sendMessage(ChatColor.RED + "Your inventory is full!");
					return;
				}
				else {
					p.getInventory().addItem(flyPotion());
					p.sendMessage(ChatColor.GREEN +"You have succefully bought a Flying Potion for $" +cost );
					return;		
				}
				
			}
	
		}
	}
}