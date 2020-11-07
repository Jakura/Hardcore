package de.Jakura.Hardcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Jakura.Hardcore.Main.main;
import de.Jakura.Hardcore.Utils.Methoden;

public class buy_CMD implements CommandExecutor {

	private main main;

	public buy_CMD(main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

		if (s instanceof Player) {

			Player p = (Player) s;

			Methoden l = new Methoden(main);
			// elbuy <spier> <herzen>
			// elbuy <herzen>

			if (args.length == 1) {

				if(main.getSpecConfig(p) == true) {
					p.sendMessage(main.pre + "§cDu bist tot du kannst dich nicht selber freikaufen! Bitte einen Mitspieler dich frei zu kaufen!");
				} else {
				
					if(args[0].equals("1")) {
						l.buyLeben(p, p.getName(), 1);
					}
					if(args[0].equals("2")) {
						l.buyLeben(p, p.getName(), 2);
					}
					if(args[0].equals("3")) {
						l.buyLeben(p, p.getName(), 3);
					}
					if(args[0].equals("4")) {
						l.buyLeben(p, p.getName(), 4);
					}
				}
			} else if(args.length == 2) {
				
				Player target = Bukkit.getPlayer(args[0]);
				
				
				if(p != target) {
					if(main.getSpecConfig(target) == true) {
						l.freiKauf(p, target);
					} else {
						
						if(target != null) {
					
							if(args[1].equals("1")) {
								l.buyLeben(p, target.getName(), 1);
							}
							if(args[0].equals("2")) {
								l.buyLeben(p, target.getName(), 2);
							}
							if(args[0].equals("3")) {
								l.buyLeben(p, target.getName(), 3);
							}
							if(args[0].equals("4")) {
								l.buyLeben(p, target.getName(), 4);
							}
					
					} else {
						p.sendMessage(main.pre + "§cDer Spieler ist nicht Online");
					}
						
					}
				} else {
					p.sendMessage(main.pre + "§c/elbuy <Herzen>");
				}
				
			} else {
				p.sendMessage(main.pre + "§c/elbuy <Spieler> <Herzen>");
			}

		} else {
			s.sendMessage(main.pre + "§cDu musst ein Spieler sein!");
		}

		return false;
	}

}
