package de.Jakura.Hardcore.Main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

@SuppressWarnings("deprecation")
public class LebenShop implements Listener {

	private main main;

	public LebenShop(main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, main);
	}
	String playername = "";

	public void openShop(Player p) {

		Inventory inv = Bukkit.createInventory(null, 9*2, "§6Extra Leben kaufen");

		ItemStack lukas = createHead("§b_Jakura_", "_Jakura_");
		ItemStack tif = createHead("§bPea_Chick", "Pea_Chick");
		ItemStack pierre = createHead("§bPixelPlayGamesHD", "PixelPlayGamesHD");
		ItemStack seb = createHead("§bSepj0", "Sepj0");

		inv.setItem(1, lukas);

		inv.setItem(3, tif);

		inv.setItem(5, pierre);

		inv.setItem(7, seb);
		
		inv.setItem(13, createItem(Material.BARRIER, 1, "§cClose"));

		p.openInventory(inv);
	}

	public void openExtraLebenBuyInv(Player p, Player p2) {

		Inventory inv = Bukkit.createInventory(null, 9 * 2, "§6Extra Leben für §d" + p2.getName());

		if (main.getSpecConfig(p2) == true) {

			inv.setItem(4, createItemWith(Material.REDSTONE_BLOCK, p2.getName()));
			
		} else {

			inv.setItem(1, createItem(Material.DIAMOND, 1, "§e1 §6Extra Leben für §b" + 64 + " §eDiamanten"));

			inv.setItem(3, createItem(Material.DIAMOND, 2, "§e2 §6Extra Leben für §b" + 64 * 2 + " §eDiamanten"));

			inv.setItem(5, createItem(Material.DIAMOND, 3, "§e3 §6Extra Leben für §b" + 64 * 3 + " §eDiamanten"));

			inv.setItem(7, createItem(Material.DIAMOND, 4, "§e4 §6Extra Leben für §b" + 64 * 4 + " §eDiamanten"));
		}

		inv.setItem(13, createItem(Material.ARROW, 1, "§cBack"));

		p.openInventory(inv);
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
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

				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§b_Jakura_")) {
					if (Bukkit.getPlayer("_Jakura_") != null) {
						openExtraLebenBuyInv(p, Bukkit.getPlayer("_Jakura_"));
						playername = "_Jakura_";
					} else {
						p.sendMessage(main.pre + "§cDer Spieler ist nicht Online");
					}
				}

				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bPea_Chick")) {
					if (Bukkit.getPlayer("Pea_Chick") != null) {
						openExtraLebenBuyInv(p, Bukkit.getPlayer("Pea_Chick"));
					} else {
						p.sendMessage(main.pre + "§cDer Spieler ist nicht Online");
					}
				}

				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bPixelPlayGamesHD")) {
					if (Bukkit.getPlayer("PixelPlayGamesHD") != null) {
						openExtraLebenBuyInv(p, Bukkit.getPlayer("PixelPlayGamesHD"));
					} else {
						p.sendMessage(main.pre + "§cDer Spieler ist nicht Online");
					}
				}

				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bSepj0")) {
					if (Bukkit.getPlayer("Sepj0") != null) {
						openExtraLebenBuyInv(p, Bukkit.getPlayer("Sepj0"));
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
					freiKauf(p, p2);
				}
				
				
				if (e.getCurrentItem().getType() == Material.ARROW) {
					openShop(p);
				}
				

				// 1 LEBEN
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§e1 §6Extra Leben für §b64 §eDiamanten") && e.getCurrentItem().getAmount() == 1) {
					buyLeben(p, playername, 1);
				}
				
				// 1 LEBEN
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§e2 §6Extra Leben für §b128 §eDiamanten") && e.getCurrentItem().getAmount() == 2) {
					buyLeben(p, playername, 2);
				}
				
				// 1 LEBEN
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§e3 §6Extra Leben für §b192 §eDiamanten") && e.getCurrentItem().getAmount() == 3) {
					buyLeben(p, playername, 3);
				}
				
				// 1 LEBEN
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§e4 §6Extra Leben für §b256 §eDiamanten") && e.getCurrentItem().getAmount() == 4) {
					buyLeben(p, playername, 4);
				}
			}
		}

	}

	public ItemStack createHead(String dn, String headname) {

		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(dn);
		meta.setOwner(headname);
		item.setItemMeta(meta);

		return item;
	}

	public ItemStack createItemWith(Material mat, String p) {

		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§c" + p + " §cfreikaufen");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add("§cKaufe " + p + " §cfür ein Stack §bDiamanten §cwieder frei.");
		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;
	}

	public ItemStack createItem(Material mat, int am, String dn) {

		ItemStack item = new ItemStack(mat, am);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(dn);
		item.setItemMeta(meta);

		return item;
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
