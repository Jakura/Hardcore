package de.Jakura.Hardcore.APIs;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

@SuppressWarnings("deprecation")
public class ItemAPI {

	
	public static ItemStack createHead(String dn, String headname) {

		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(dn);
		meta.setOwner(headname);
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack createItemWith(Material mat, String p) {

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

	public static ItemStack createItem(Material mat, int am, String dn) {

		ItemStack item = new ItemStack(mat, am);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(dn);
		item.setItemMeta(meta);

		return item;
	}
	
}
