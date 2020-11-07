package de.Jakura.Hardcore.Events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import de.Jakura.Hardcore.Main.main;
import de.Jakura.Hardcore.Utils.Methoden;

public class DeathHandler implements Listener {

	
	private main main;
	
	public DeathHandler(main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	Methoden m = new Methoden(main);
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();

		String player = "§e" + p.getName() + " §chat nun ";

		Bukkit.getOnlinePlayers().forEach(y -> new Methoden(main).sendBoard(y));
		
		String keinelebenmehr = "";
		String hatnochleben = "";

		int leben = main.getHealth(p) - 1;

		e.setDeathMessage(null);
		if (main.getConfig().getString(main.pfad + p.getName()) != null) {
			e.getDrops().clear();
			e.setDroppedExp(0);
			e.setNewExp(0);
			e.setNewLevel(0);

			if (leben >= 1) {
				hatnochleben = player + "§cnoch §a" + leben + " §cExtra Leben.";

				Bukkit.broadcastMessage(main.pre + "§cDer Spieler §e" + p.getName() + " §cist Gestorben. " + hatnochleben);
			} else if (leben == 0) {
				keinelebenmehr = player + "§4§nkeine Leben mehr§r§c, wen §e" + p.getName() + " §cerneut Stirbt,"
						+ " kann er von einem anderen Spieler für ein Stack §bDiamanten §cfreigekauft werden.";

				Bukkit.broadcastMessage(main.pre + "§cDer Spieler §e" + p.getName() + " §cist Gestorben. " + keinelebenmehr);
			}
			if (leben == -1) {
				main.getConfig().set(main.pfad + p.getName(), 0);
				main.saveConfig();
				main.setSpecConfig(p, true);
				Bukkit.broadcastMessage(main.pre + "§c" + p.getName()
						+ " §cist erneut Gestorben. §4§nund ist nun in Spectator-Modus! §r§cUnd muss freigekauft werden.");
				p.setGameMode(GameMode.SPECTATOR);
			} else if (leben >= 0) {
				main.getConfig().set(main.pfad + p.getName(), main.getHealth(p) - 1);
				main.saveConfig();
			}

		} else {
			main.createStandardConfig(p);
			Bukkit.getConsoleSender().sendMessage(main.pre + "§aDatei von §6" + p.getName() + " §awurde Erstellt!");
		}

	}

	@EventHandler
	public void onresp(PlayerRespawnEvent e) {
		Bukkit.getOnlinePlayers().forEach(y -> new Methoden(main).sendBoard(y));
		Player p = e.getPlayer();

		Location loc = p.getBedSpawnLocation();
		p.teleport(loc);
	}
}
