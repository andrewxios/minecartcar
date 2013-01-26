package com.github.andrewxios.minecartcar;

import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class MinecartListener implements Listener {

	
	Minecart mCart;
	
	@EventHandler
	public void onEnterMinecart(PlayerInteractEntityEvent event){
		if(event.getRightClicked() instanceof Minecart){
			mCart = (Minecart) event.getRightClicked();
		}
		
	}
	
	Vector heading;
	boolean isBreakState = false;
	
	@EventHandler
	public void onDriveEvent(PlayerMoveEvent event){
		if(event.getPlayer().isInsideVehicle() &&
				event.getPlayer().getVehicle() == mCart){
		 heading = event.getPlayer().getLocation().getDirection();
		 heading.setY(0); //ignore y velocity
		 
		 if(!isBreakState){
			 mCart.setVelocity(heading);
		 }
		 else{
			 mCart.setVelocity(new Vector(0,0,0)); 
		 }
		 
		}
	}
	
	@EventHandler void onBreakEvent(PlayerInteractEvent event){
		if(event.getPlayer().getVehicle() == mCart){
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK ){
				isBreakState = !isBreakState;
			}
		}
		else
			isBreakState = false;
	}
}
