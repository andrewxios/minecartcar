package com.github.andrewxios.minecartcar;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecartCar extends JavaPlugin{
	
	@Override
	public void onEnable(){
		Plugin plugin = this;
		plugin.getLogger().info("MinecartCar plugin enabled");
		plugin.getServer().getPluginManager().registerEvents(new MinecartListener(), plugin);
	}
	
	@Override
	public void onDisable(){
		getServer().getLogger().info("MinecartCar plugin disabled");
	}
	
}