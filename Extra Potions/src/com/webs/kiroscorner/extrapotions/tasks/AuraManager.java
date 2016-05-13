package com.webs.kiroscorner.extrapotions.tasks;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

public class AuraManager {

	public AuraManager() {
		
	}
	static AuraManager instance = new AuraManager();
	public static AuraManager getInstance() {
		return instance;
	}

}
