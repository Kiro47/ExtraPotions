package com.webs.kiroscorner.extrapotions.listeners;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.webs.kiroscorner.extrapotions.ExtraPotionsMain;
import com.webs.kiroscorner.extrapotions.PotionCustomizer;

public class SignBuyingListener implements Listener{

	FileConfiguration config = ExtraPotionsMain.getThisPlugin().getConfig();
	 String cS = config.getString("CurrencySymbol");
	 
	@EventHandler
	public void onPlayerPotionSignBuying(PlayerInteractEvent e) {
		Player p = (Player) e.getPlayer();
		if (!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)))return;
		Material b = e.getClickedBlock().getType();
		if ((b.equals(Material.SIGN)) || (b.equals(Material.SIGN_POST)) || (b.equals(Material.WALL_SIGN))) {
			BlockState st = e.getClickedBlock().getState();
			Sign s = (Sign) st;
			if ((s.getLine(0).equalsIgnoreCase(SignRegistry.spre)))  return;
			else {
				Double d = Double.parseDouble(s.getLine(2).replace(cS, ""));
				switch (s.getLine(1)) {
				case "god" :
					PotionCustomizer.transact(p,d , "god");
				break;
				case "fly" :
					PotionCustomizer.transact(p,d , "fly");
				break;
				case "nofall" :
					PotionCustomizer.transact(p,d , "nofall");
				break;
				case "fieryaura" :
					PotionCustomizer.transact(p,d , "fieryaura");
				break;
				case "ghost" :
					PotionCustomizer.transact(p,d , "ghost");
				break;
				default:
					break;
				}	
			}
		}
		else {
			return;
		}
	}
}
