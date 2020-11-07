package de.Jakura.Hardcore.Utils;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.Jakura.Hardcore.APIs.ItemAPI;
import de.Jakura.Hardcore.Main.main;

public class LebenShop {

	private main main;

	public LebenShop(main main) {
		this.main = main;
	}

	public void openShop(Player p) {
		
		Inventory inv = Bukkit.createInventory(null, 9*2, "§6Extra Leben kaufen");

		ItemStack lukas = ItemAPI.createHead("§b_Jakura_", "_Jakura_");
		ItemStack tif = ItemAPI.createHead("§bPea_Chick", "Pea_Chick");
		ItemStack pierre = ItemAPI.createHead("§bPixelPlayGamesHD", "PixelPlayGamesHD");
		ItemStack seb = ItemAPI.createHead("§bSepj0", "Sepj0");

		inv.setItem(1, lukas);

		inv.setItem(3, tif);

		inv.setItem(5, pierre);

		inv.setItem(7, seb);
		
		inv.setItem(13, ItemAPI.createItem(Material.BARRIER, 1, "§cClose"));

		p.openInventory(inv);
	}

	public void openExtraLebenBuyInv(Player p, Player p2) {

		Inventory inv = Bukkit.createInventory(null, 9 * 2, "§6Extra Leben für §d" + p2.getName());

		if (main.getSpecConfig(p2) == true) {

			inv.setItem(4, ItemAPI.createItemWith(Material.REDSTONE_BLOCK, p2.getName()));
			
		} else {

			inv.setItem(1, ItemAPI.createItem(Material.DIAMOND, 1, "§e1 §6Extra Leben für §b" + 64 + " §eDiamanten"));

			inv.setItem(3, ItemAPI.createItem(Material.DIAMOND, 2, "§e2 §6Extra Leben für §b" + 64 * 2 + " §eDiamanten"));

			inv.setItem(5, ItemAPI.createItem(Material.DIAMOND, 3, "§e3 §6Extra Leben für §b" + 64 * 3 + " §eDiamanten"));

			inv.setItem(7, ItemAPI.createItem(Material.DIAMOND, 4, "§e4 §6Extra Leben für §b" + 64 * 4 + " §eDiamanten"));
		}

		inv.setItem(13, ItemAPI.createItem(Material.ARROW, 1, "§cBack"));

		p.openInventory(inv);
	}
	
}
