package de.Jakura.Hardcore.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class shop_CMD implements CommandExecutor {

	private main main;
	
	public shop_CMD(main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		if(s instanceof Player) {
			Player p = (Player) s;
			
			if(cmd.getName().equalsIgnoreCase("elshop")) {
				LebenShop l = new LebenShop(main);
				l.openShop(p);
			}
			
		} else {
			s.sendMessage(main.pre + "§cDu musst ein Spieler sein");
		}
		
		return false;
	}
	
}
