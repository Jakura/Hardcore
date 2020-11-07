package de.Jakura.Hardcore.Main;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class main extends JavaPlugin implements Listener {

	int sched;

	String pre = "§7[§6SCP§7]§r ";

	String pfad = "Extra Leben.";

	ArrayList<String> list = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		
		loadConfig();
		
		
		
		Bukkit.getConsoleSender().sendMessage("§6Plugin version §a" + this.getDescription().getVersion() + " §ageladen.");
		Bukkit.getPluginManager().registerEvents(this, this);
		
		new LebenShop(this);
		getCommand("elbuy").setExecutor(new buy_CMD(this));
		getCommand("elshop").setExecutor(new shop_CMD(this));
		
		for (Player al : Bukkit.getOnlinePlayers()) {
			sendBoard(al);
		}
		sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				for (Player al : Bukkit.getOnlinePlayers()) {
					sendBoard(al);

					if (getSpecConfig(al) == true) {
						al.setGameMode(GameMode.SPECTATOR);
					} else {
						al.setGameMode(GameMode.SURVIVAL);
					}
				}
			}

		}, 20, 20);

	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§6Plugin version §a" + this.getDescription().getVersion() + " §cgestoppt.");
	}

	public int getHealth(Player p) {
		return getConfig().getInt(pfad + p.getName());
	}

	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	@SuppressWarnings("deprecation")
	public void sendBoard(Player p) {

		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("aaa", "dummy");

		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§4Extra Leben");

		// LUKAS
		if (getConfig().getString(pfad + "_Jakura_") == null) {
			obj.getScore("§c_Jakura_: §7[§4N/A§7]").setScore(3);
		} else {
			obj.getScore("§a_Jakura_: §6" + getConfig().getInt(pfad + "_Jakura_")).setScore(3);
		}

		// TIFFANY
		if (getConfig().getString(pfad + "Pea_Chick") == null) {
			obj.getScore("§cPea_Chick: §7[§4N/A§7]").setScore(2);
		} else {
			obj.getScore("§aPea_Chick: §6" + getConfig().getInt(pfad + "Pea_Chick")).setScore(2);
		}

		// PIERRE
		if (getConfig().getString(pfad + "PixelPlayGamesHD") == null) {
			obj.getScore("§cPixelPlayGamesHD: §7[§4N/A§7]").setScore(1);
		} else {
			obj.getScore("§aPixelPlayGamesHD: §6" + getConfig().getInt(pfad + "PixelPlayGamesHD")).setScore(1);
		}

		// SEBASTIAN
		if (getConfig().getString(pfad + "Sepj0") == null) {
			obj.getScore("§cSepj0: §7[§4N/A§7]").setScore(0);
		} else {
			obj.getScore("§aSepj0: §6" + getConfig().getInt(pfad + "Sepj0")).setScore(0);
		}

		p.setScoreboard(board);

	}
	@SuppressWarnings("deprecation")
	public void update(Player p) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("aaa", "dummy");

		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§4Extra Leben");

		// LUKAS
		if (getConfig().getString(pfad + "_Jakura_") == null) {
			obj.getScore("§c_Jakura_: §7[§4N/A§7]").setScore(3);
		} else {
			obj.getScore("§a_Jakura_: §6" + getConfig().getInt(pfad + "_Jakura_")).setScore(3);
		}

		// TIFFANY
		if (getConfig().getString(pfad + "Pea_Chick") == null) {
			obj.getScore("§cPea_Chick: §7[§4N/A§7]").setScore(2);
		} else {
			obj.getScore("§aPea_Chick: §6" + getConfig().getInt(pfad + "Pea_Chick")).setScore(2);
		}

		// PIERRE
		if (getConfig().getString(pfad + "PixelPlayGamesHD") == null) {
			obj.getScore("§cPixelPlayGamesHD: §7[§4N/A§7]").setScore(1);
		} else {
			obj.getScore("§aPixelPlayGamesHD: §6" + getConfig().getInt(pfad + "PixelPlayGamesHD")).setScore(1);
		}

		// SEBASTIAN
		if (getConfig().getString(pfad + "Sepj0") == null) {
			obj.getScore("§cSepj0: §7[§4N/A§7]").setScore(0);
		} else {
			obj.getScore("§aSepj0: §6" + getConfig().getInt(pfad + "Sepj0")).setScore(0);
		}

		p.setScoreboard(board);
	}

	public void createStandardConfig(Player p) {

		getConfig().addDefault(pfad + p.getName(), 1);
		saveConfig();
	}

	public void setSpecConfig(Player p, boolean var) {
		getConfig().set("SpecPlayer." + p.getName(), var);
		saveConfig();
	}

	public boolean getSpecConfig(Player p) {

		return getConfig().getBoolean("SpecPlayer." + p.getName());
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		list.add("{username} ist gerade dem Server beigetreten - glhf!");
		list.add("{username} ist gerade dem Server beigetreten. Schnell, seht alle beschäftigt aus!");
		list.add("Willkommen {username}! Wir hoffen du hast Pizza mitgebracht.");
		list.add("{username} ist gerade dem Server beigetreten. Versteckt eure Bananen!");
		list.add("{username} ist gerade aufgetaucht. Halt mein Bier.");
		list.add("Ist es ein Vogel? Ist es ein Flugzeug? Ah nein, ist nur {username}. ");
		list.add("Es ist {username}. Preiset die Sonne!");
		list.add("{username} ist da. Party ist vorbei.");
		list.add("Rosen sind rot, Veilchen sind blau, {username} riecht nach Kot.");


		Random r = new Random();
		String res = null;
		res = list.get(r.nextInt(list.size()));
		res = res.replace("{username}","§a" + p.getName() + "§e");
		e.setJoinMessage("§e" + res);
		

		sendBoard(p);
		p.sendMessage(pre + "§cAchtung! Du bist einem Hardcore-Server beigetreten."
				+ " Stirbst du, verlierst du dein gesamtes Inventar."
				+ " Ist dein letztes Leben aufgebraucht, wirst du von dem Spiel ausgeschlossen."
				+ " Unter /elshop kannst du Leben für Diamanten kaufen.");

		if (getConfig().getString(pfad + p.getName()) == null) {
			createStandardConfig(p);
			Bukkit.getConsoleSender().sendMessage(pre + "§aDatei von §6" + p.getName() + " §awurde Erstellt!");
		} else {
			Bukkit.getConsoleSender().sendMessage(pre + "§aDatei von §6" + p.getName() + " §awurde Geladen!");
		}
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		e.setQuitMessage(pre + "§c" + p.getName() + " §ehat den Hardcore Server verlassen! bis zum nächsten mal");
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();

		String player = "§e" + p.getName() + " §chat nun ";

		String keinelebenmehr = "";
		String hatnochleben = "";

		int leben = getHealth(p) - 1;

		e.setDeathMessage(null);
		if (getConfig().getString(pfad + p.getName()) != null) {
			e.getDrops().clear();
			e.setDroppedExp(0);
			e.setNewExp(0);
			e.setNewLevel(0);

			if (leben >= 1) {
				hatnochleben = player + "§cnoch §a" + leben + " §cExtra Leben.";

				Bukkit.broadcastMessage(pre + "§cDer Spieler §e" + p.getName() + " §cist Gestorben. " + hatnochleben);
			} else if (leben == 0) {
				keinelebenmehr = player + "§4§nkeine Leben mehr§r§c, wen §e" + p.getName() + " §cerneut Stirbt,"
						+ " kann er von einem anderen Spieler für ein Stack §bDiamanten §cfreigekauft werden.";

				Bukkit.broadcastMessage(pre + "§cDer Spieler §e" + p.getName() + " §cist Gestorben. " + keinelebenmehr);
			}
			if (leben == 0) {
				getConfig().set(pfad + p.getName(), 0);
				saveConfig();
				setSpecConfig(p, true);
				Bukkit.broadcastMessage(pre + "§c" + p.getName()
						+ " §cist erneut Gestorben. §4§nund ist nun in Spectator-Modus! §r§cUnd muss freigekauft werden.");
			} else if (leben >= 1) {
				getConfig().set(pfad + p.getName(), getHealth(p) - 1);
				saveConfig();
			}

		} else {
			createStandardConfig(p);
			Bukkit.getConsoleSender().sendMessage(pre + "§aDatei von §6" + p.getName() + " §awurde Erstellt!");
		}

	}
	
	@EventHandler
	public void onresp(PlayerRespawnEvent e) {
		
		Player p = e.getPlayer();
		
		Location loc = p.getBedSpawnLocation();
		p.teleport(loc);
	}
}
