package com.webs.kiroscorner.extrapotions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.webs.kiroscorner.extrapotions.listeners.DrinkingListener;
import com.webs.kiroscorner.extrapotions.listeners.SignBuyingListener;
import com.webs.kiroscorner.extrapotions.listeners.SignRegistry;
import com.webs.kiroscorner.extrapotions.potionlistener.FieryAuraPotionListener;
import com.webs.kiroscorner.extrapotions.potionlistener.FlyPotionListener;
import com.webs.kiroscorner.extrapotions.potionlistener.GodPotionListener;
import com.webs.kiroscorner.extrapotions.potionlistener.NoFallPotionListener;

import net.milkbowl.vault.economy.Economy;

public class ExtraPotionsMain extends JavaPlugin{
	// safe drop is failing, hard.
	// Add in Ghost Listener
	// Get Aura Manager to work. #Check Pure's projectile from custom mobs?
	// Check sign registry to see if minutes are added on line 4 properly
	double ver = 1.0;
	String spigver = Bukkit.getVersion();
	String prefix = ChatColor.YELLOW + "[ExtraPotions]";
	@SuppressWarnings("unused")
	private boolean usingVault;
	private static Economy econ;
	
	public void onEnable() {
		initVaultHook();
		saveDefaultConfig();
		
		if (!(getThisPlugin().getConfig().getDouble("version") == Double.parseDouble(getThisPlugin().getDescription().getVersion()))) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "[ExtraPotions] Config version does not match plugin version, consider deleting the plugin.yml and letting it regenerate!");
		}
		
		Bukkit.getLogger().info(prefix+ChatColor.YELLOW + "Extra Potions, Version:  " + ver + " Enabled");
		Bukkit.getLogger().info(prefix+ChatColor.YELLOW + "Build on Spigot Build: " + spigver);
		
		getCommand("test").setExecutor(new TestCommand());
		////////////////////////////
		PluginManager rl = Bukkit.getPluginManager();
		
		rl.registerEvents(new SignRegistry(), this);
		rl.registerEvents(new SignBuyingListener(), this);
		
		rl.registerEvents(new DrinkingListener(), this);
		
		rl.registerEvents(new FlyPotionListener(), this);
		rl.registerEvents(new GodPotionListener(), this);
		rl.registerEvents(new NoFallPotionListener(), this);
		rl.registerEvents(new FieryAuraPotionListener(), this);
	}
	public void onDisable(){
		GodPotionListener.getInstance().gods.clear();
		GodPotionListener.getInstance().shinyGods.clear();
		FlyPotionListener.getInstance().fliers.clear();
		FlyPotionListener.getInstance().sfliers.clear();
		NoFallPotionListener.getInstance().falR.clear();
		NoFallPotionListener.getInstance().falRP.clear();
		FieryAuraPotionListener.getInstance().burners.clear();
		FieryAuraPotionListener.getInstance().flamers.clear();
		
		
	}
	
	public static Plugin getThisPlugin(){
		return Bukkit.getPluginManager().getPlugin("ExtraPotions");
	}
	public void initVaultHook() {
		PluginManager pluginManager = getServer().getPluginManager();
		if (!pluginManager.isPluginEnabled("Vault")) {
			return;
		}
		
		// Vault seems to exist so retrieve an provider instance of Economy.class
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return;
		}
		
		usingVault = true;
		econ = rsp.getProvider();
	}
	public static Economy getEconomy() {
		return econ;
	}
}
