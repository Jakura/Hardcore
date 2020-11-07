package de.Jakura.Hardcore.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.Jakura.Hardcore.Main.main;
import de.Jakura.Hardcore.Utils.LebenShop;
import de.Jakura.Hardcore.Utils.Methoden;

public class InvClickHandler implements Listener {

	private main main;
	
	
	String playername = "";
	
	public InvClickHandler(main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		LebenShop l = new LebenShop(main);
		Methoden m = new Methoden(main);
		Player p = (Player) e.getWhoClicked();

		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getCurrentItem().getItemMeta() == null) {
			return;
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
			return;
		}

		if (e.getView().getTitle().equals("§6Extra Leben kaufen")) {
			
			if (e.getRawSlot() < e.getInventory().getSize()) {
				e.setCancelled(true);
        //LUKAS
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§b_Jakura_")) {
					if (Bukkit.getPlayer("_Jakura_") != null) {
						l.openExtraLebenBuyInv(p, Bukkit.getPlayer("_Jakura_"));
						playername = "_Jakura_";
					} else {
						p.sendMessage(main.pre + "§cDer Spieler ist nicht Online");
					}
				}
        //TIFFANY
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bPea_Chick")) {
					if (Bukkit.getPlayer("Pea_Chick") != null) {
						l.openExtraLebenBuyInv(p, Bukkit.getPlayer("Pea_Chick"));
						playername = "Pea_Chick";
					} else {
						p.sendMessage(main.pre + "§cDer Spieler ist nicht Online");
					}
				}
        //PIERRE
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bPixelPlayGamesHD")) {
					if (Bukkit.getPlayer("PixelPlayGamesHD") != null) {
						l.openExtraLebenBuyInv(p, Bukkit.getPlayer("PixelPlayGamesHD"));
						playername = "PixelPlayGamesHD";
					} else {
						p.sendMessage(main.pre + "§cDer Spieler ist nicht Online");
					}
				}
        //SEBASTIAN
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bSepj0")) {
					if (Bukkit.getPlayer("Sepj0") != null) {
						l.openExtraLebenBuyInv(p, Bukkit.getPlayer("Sepj0"));
						playername = "Sepj0";
					} else {
						p.sendMessage(main.pre + "§cDer Spieler ist nicht Online");
					}
				}
				if(e.getCurrentItem().getType() == Material.BARRIER) {
					p.closeInventory();
				}
			}
		}
		
		
		if (e.getView().getTitle().equals("§6Extra Leben für §d" + playername)) {
			if (e.getRawSlot() < e.getInventory().getSize()) {
				e.setCancelled(true);

				if(e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
					Player p2 = Bukkit.getPlayer(playername);
					m.freiKauf(p, p2);
				}
				
				
				if (e.getCurrentItem().getType() == Material.ARROW) {
					l.openShop(p);
				}
				

				// 1 LEBEN
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§e1 §6Extra Leben für §b64 §eDiamanten") && e.getCurrentItem().getAmount() == 1) {
					m.buyLeben(p, playername, 1);
				}
				
				// 2 LEBEN
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§e2 §6Extra Leben für §b128 §eDiamanten") && e.getCurrentItem().getAmount() == 2) {
					m.buyLeben(p, playername, 2);
				}
				
				// 3 LEBEN
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§e3 §6Extra Leben für §b192 §eDiamanten") && e.getCurrentItem().getAmount() == 3) {
					m.buyLeben(p, playername, 3);
				}
				
				// 4 LEBEN
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§e4 §6Extra Leben für §b256 §eDiamanten") && e.getCurrentItem().getAmount() == 4) {
					m.buyLeben(p, playername, 4);
				}
			}
		}
	}
}
