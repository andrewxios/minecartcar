
package com.github.andrewxios.minecartcar;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;

class MinecartListener implements Listener {

			//player   minecart
	private final Map<Player, Minecar> mineCar = new HashMap<Player, Minecar>();
	
	@EventHandler
	public void onEnterMinecart(VehicleEnterEvent event){
		if(event.getVehicle() instanceof Minecart &&
				event.getEntered() instanceof Player){
			Player player = (Player)event.getEntered();
			Minecar minecar = new Minecar((Minecart)event.getVehicle());
			
			mineCar.put(player, minecar);
		}		
		
	}
	
	@EventHandler
	public void onLeaveMinecart(VehicleExitEvent event){
		if(event.getVehicle() instanceof Minecart &&
				event.getExited() instanceof Player){
			mineCar.remove((Player)event.getExited());
		}		
		
	}

	@EventHandler //test
	public void onDriveEvent(VehicleMoveEvent event){
		if(event.getVehicle().getPassenger() instanceof Player &&
				event.getVehicle() instanceof Minecart){
			if(mineCar.containsKey((Player)event.getVehicle().getPassenger())){
				mineCar.get((Player)event.getVehicle().getPassenger()).drive();
			}
		}
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	void onBreakEvent(PlayerInteractEvent event){
		if(mineCar.containsKey(event.getPlayer())){
			if(event.getAction()==Action.RIGHT_CLICK_AIR||
					event.getAction()==Action.RIGHT_CLICK_BLOCK)
			mineCar.get(event.getPlayer()).toggleBreak();
			mineCar.get(event.getPlayer()).drive();
		}
	}
}
