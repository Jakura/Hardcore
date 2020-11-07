package de.Jakura.Hardcore.Main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import de.Jakura.Hardcore.Commands.buy_CMD;
import de.Jakura.Hardcore.Commands.shop_CMD;
import de.Jakura.Hardcore.Events.DeathHandler;
import de.Jakura.Hardcore.Events.InvClickHandler;
import de.Jakura.Hardcore.Events.JoinHandler;
import de.Jakura.Hardcore.Utils.Methoden;

public class main extends JavaPlugin implements Listener {

	public int sched;

	public String pre = "§7[§6SCP§7]§r ";

	public String pfad = "Extra Leben.";

	public ArrayList<String> list = new ArrayList<String>();
	
	
	@Override
	public void onEnable() {

		loadConfig();
		loadEvents();
		loadCMD();
		
		Bukkit.getConsoleSender().sendMessage(pre + "§6Plugin version §e" 
		+ this.getDescription().getVersion() + " §ageladen.");
		
		Bukkit.getOnlinePlayers().forEach(y -> new Methoden(this).update(y));
	}
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(pre + "§6Plugin version §e"
		+ this.getDescription().getVersion() + " §cgestoppt.");
	}
	public int getHealth(Player p) {
		return this.getConfig().getInt(pfad + p.getName());
	}

	public void loadConfig() {
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	public void loadEvents() {
		new JoinHandler(this);
		new DeathHandler(this);
		new InvClickHandler(this);
	}
	public void loadCMD() {
		getCommand("elbuy").setExecutor(new buy_CMD(this));
		getCommand("elshop").setExecutor(new shop_CMD(this));
	}
	public void createStandardConfig(Player p) {
		this.getConfig().addDefault(pfad + p.getName(), 1);
		this.saveConfig();
	}
	public void setSpecConfig(Player p, boolean var) {
		this.getConfig().set("SpecPlayer." + p.getName(), var);
		this.saveConfig();
	}
	public boolean getSpecConfig(Player p) {
		return this.getConfig().getBoolean("SpecPlayer." + p.getName());
	}
}
