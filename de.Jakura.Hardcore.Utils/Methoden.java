package de.Jakura.Hardcore.Utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import de.Jakura.Hardcore.Main.main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Methoden {

	private main main;
	
	public Methoden(main main) {
		this.main = main;
	}
	
	@SuppressWarnings("deprecation")
	public void sendJoinMessage(Player p) {
		
		TextComponent cfor = new TextComponent(main.pre + "§cAchtung! Du bist einem Hardcore-Server beigetreten."
				+ " Stirbst du, verlierst du dein gesamtes Inventar. "
				+ "Ist dein letztes Leben aufgebraucht, wirst du von dem Spiel ausgeschlossen."
				+ " Unter ");
		TextComponent c = new TextComponent("§c/elshop ");
		TextComponent clow = new TextComponent("§ckannst du Leben für Diamanten kaufen.");
		
		c.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Klick hier, um ein Leben zu kaufen.")));
		c.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/elshop"));
		
		
		cfor.addExtra(c);
		cfor.addExtra(clow);
		
		p.spigot().sendMessage(cfor);
	}
	
	public int getAmount(Player p, ItemStack item) {
		int amount = 0;
		for (ItemStack contents : p.getInventory().getContents()) {
			if (contents == null || contents.getType() == Material.AIR) continue;
			if (contents.isSimilar(item))
				amount += contents.getAmount();
		}
		return amount;
	}
	public void update(Player p) {
		sendBoard(p);
		main.sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			@Override
			public void run() {
				sendBoard(p);
				if (main.getSpecConfig(p) == true) {
					p.setGameMode(GameMode.SPECTATOR);
				} else {
					p.setGameMode(GameMode.SURVIVAL);
				}

			}

		}, 20 * 10, 20 * 10);

	}
	
	@SuppressWarnings("deprecation")
	public void sendBoard(Player p) {

		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("aaa", "dummy");

		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§4Extra Leben");

		// LUKAS
		if (main.getConfig().getString(main.pfad + "_Jakura_") == null) {
			obj.getScore("§c_Jakura_: §7[§4N/A§7]").setScore(3);
		} else {
			obj.getScore("§a_Jakura_: §6" + main.getConfig().getInt(main.pfad + "_Jakura_")).setScore(3);
		}

		// TIFFANY
		if (main.getConfig().getString(main.pfad + "Pea_Chick") == null) {
			obj.getScore("§cPea_Chick: §7[§4N/A§7]").setScore(2);
		} else {
			obj.getScore("§aPea_Chick: §6" + main.getConfig().getInt(main.pfad + "Pea_Chick")).setScore(2);
		}

		// PIERRE
		if (main.getConfig().getString(main.pfad + "PixelPlayGamesHD") == null) {
			obj.getScore("§cPixelPlayGamesHD: §7[§4N/A§7]").setScore(1);
		} else {
			obj.getScore("§aPixelPlayGamesHD: §6" + main.getConfig().getInt(main.pfad + "PixelPlayGamesHD")).setScore(1);
		}

		// SEBASTIAN
		if (main.getConfig().getString(main.pfad + "Sepj0") == null) {
			obj.getScore("§cSepj0: §7[§4N/A§7]").setScore(0);
		} else {
			obj.getScore("§aSepj0: §6" + main.getConfig().getInt(main.pfad + "Sepj0")).setScore(0);
		}

		p.setScoreboard(board);

	}

	public void buyLeben(Player buyer, String tar, int live) {
		Player target = Bukkit.getPlayer(tar);

		if (target != null) {
			if (buyer != target) {
				if (getAmount(buyer, new ItemStack(Material.DIAMOND)) >= live * 64) {
					main.getConfig().set(main.pfad + target.getName(), main.getHealth(target) + live);
					main.saveConfig();
					target.sendMessage(main.pre + "§6" + buyer.getName() + " §chat dir §e" + live
							+ " §cExtra Leben gekauft! Du solltest dich dafür bedanken.");
					buyer.sendMessage(main.pre + "§aDu hast §d" + target.getName() + " §e" + live + " §aExtra Leben gekauft!");
					Bukkit.broadcastMessage(main.pre + "§6" + buyer.getName() + " §ahat §d" + target.getName() + " §e"
							+ live + " §aExtra Leben gekauft.");
					buyer.getInventory().removeItem(new ItemStack(Material.DIAMOND, live * 64));
					buyer.closeInventory();
					Bukkit.getOnlinePlayers().forEach(y -> sendBoard(y));
					return;
				} else {
					buyer.sendMessage(main.pre + "§cDu hast nicht genug Diamanten!");
					return;
				}
			}else {
				if (getAmount(target, new ItemStack(Material.DIAMOND)) >= live * 64) {
					main.getConfig().set(main.pfad + target.getName(), main.getHealth(target) + live);
					main.saveConfig();
					target.sendMessage(main.pre + "§aDu hast dir §e" + live + " §aExtra Leben gekauft!");
					Bukkit.broadcastMessage(main.pre + "§d" + target.getName() + " §ahat sich §e" + live + " §aExtra Leben gekauft.");
					target.getInventory().removeItem(new ItemStack(Material.DIAMOND, live * 64));
					target.closeInventory();
					Bukkit.getOnlinePlayers().forEach(y -> sendBoard(y));
					return;
				} else {
					target.sendMessage(main.pre + "§cDu hast nicht genug Diamanten!");
					return;
				}
			}

		} else {
			buyer.sendMessage(main.pre + "§cDer Spieler ist nicht Online");
			return;
		}
	}
	
	public void freiKauf(Player buyer, Player target) {
		
		if(buyer != target) {
			if (getAmount(buyer, new ItemStack(Material.DIAMOND)) >= 64) {
				main.getConfig().set(main.pfad + target.getName(), main.getHealth(target) + 1);
				main.saveConfig();
				
				target.sendMessage(main.pre + "§6" + buyer.getName() + " §chat dich §eFrei §cgekauft! Du solltest dich dafür wirklich bedanken.");
				
				buyer.sendMessage(main.pre + "§aDu hast §d" + target.getName() + " §eFrei §agekauft!");
				
				Bukkit.broadcastMessage(main.pre + "§6" + buyer.getName() + " §ahat §d" + target.getName() + "§eFrei §agekauft.");
				
				buyer.getInventory().removeItem(new ItemStack(Material.DIAMOND, 64));
				buyer.closeInventory();
				main.setSpecConfig(target, false);
				target.setGameMode(GameMode.SURVIVAL);
				Bukkit.getOnlinePlayers().forEach(y -> sendBoard(y));
				return;
			} else {
				buyer.sendMessage(main.pre + "§cDu hast nicht genug Diamanten!");
				return;
			}
		} else {
			buyer.sendMessage(main.pre + "§cDu bist tot du kannst dich nicht selber freikaufen! Bitte einen Mitspieler dich frei zu kaufen!");
		}
		
	}
	
}
