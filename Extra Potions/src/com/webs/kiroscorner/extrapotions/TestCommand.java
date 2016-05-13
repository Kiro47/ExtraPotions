package com.webs.kiroscorner.extrapotions;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) return true;
		else {
			Player p = (Player) sender;
			p.setGameMode(GameMode.SPECTATOR);
			for (Player pl: Bukkit.getServer().getOnlinePlayers()) {
				pl.showPlayer(p);
				return true;
			}
		}
		return true;
	}

}
