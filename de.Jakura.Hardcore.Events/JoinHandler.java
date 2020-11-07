package de.Jakura.Hardcore.Events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.Jakura.Hardcore.Main.main;
import de.Jakura.Hardcore.Utils.Methoden;

public class JoinHandler implements Listener {

	private main main;
	
	public JoinHandler(main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		main.list.add("{username} ist gerade dem Server beigetreten - glhf!");
		main.list.add("{username} ist gerade dem Server beigetreten. Schnell, seht alle beschäftigt aus!");
		main.list.add("Willkommen {username}! Wir hoffen du hast Pizza mitgebracht.");
		main.list.add("{username} ist gerade dem Server beigetreten. Versteckt eure Bananen!");
		main.list.add("{username} ist gerade aufgetaucht. Halt mein Bier.");
		main.list.add("Ist es ein Vogel? Ist es ein Flugzeug? Ah nein, ist nur {username}. ");
		main.list.add("Es ist {username}. Preiset die Sonne!");
		main.list.add("{username} ist da. Party ist vorbei.");
		main.list.add("Rosen sind rot, Veilchen sind blau, {username} riecht nach Kot.");

		Random r = new Random();
		String res = null;
		res = main.list.get(r.nextInt(main.list.size()));
		res = res.replace("{username}", "§a" + p.getName() + "§e");
		e.setJoinMessage("§e" + res);

		new Methoden(main).sendBoard(p);
		new Methoden(main).sendJoinMessage(p);
		

		if (main.getConfig().getString(main.pfad + p.getName()) == null) {
			main.createStandardConfig(p);
			Bukkit.getConsoleSender().sendMessage(main.pre + "§aDatei von §6" + p.getName() + " §awurde Erstellt!");
		} else {
			Bukkit.getConsoleSender().sendMessage(main.pre + "§aDatei von §6" + p.getName() + " §awurde Geladen!");
		}
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		e.setQuitMessage(main.pre + "§c" + p.getName() + " §ehat den Hardcore Server verlassen! bis zum nächsten mal");
	}
}
