
package com.github.andrewxios.minecartcar;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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
	
	Vector heading, tmpheading = new Vector(0,0,0);
	boolean isBreakState = false;
	
	@EventHandler
	public void onDriveEvent(PlayerMoveEvent event){
		Player player = event.getPlayer();
		if(player.isInsideVehicle() &&
				player.getVehicle() == mCart){
		 heading = player.getLocation().getDirection();
		 heading.setY(0); //ignore y
		 if(!isBreakState){
			 if((mCart.getLocation().getDirection().angle(player.getLocation().getDirection().setY(0)) < 0.7) ||
					 (mCart.getLocation().getDirection().angle(player.getLocation().getDirection().setY(0)) > 5.6)){
				 mCart.setVelocity(heading);
			 }
			 else{
				 mCart.setVelocity(tmpheading);
			 }
			 
		 }
		 else{
			 mCart.setVelocity(new Vector(0,0,0)); 
		 }
		 tmpheading = heading;
		}
		
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	void onBreakEvent(PlayerInteractEvent event){
		if(event.getPlayer().getVehicle() == mCart){
			if(event.getAction() == Action.RIGHT_CLICK_AIR || 
					event.getAction() == Action.RIGHT_CLICK_BLOCK ){
				isBreakState = !isBreakState;
			}
		}
		else
			isBreakState = false;
	}
}
