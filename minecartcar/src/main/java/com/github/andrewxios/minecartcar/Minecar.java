package com.github.andrewxios.minecartcar;

import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;


class Minecar{

	private boolean isBrake = true;
	private Vector hDirection = new Vector(0,0,0);
    private final Minecart minecart;
	
	public Minecar(Minecart minecart){
		this.minecart = minecart;
	}
	
	public void toggleBreak(){
		isBrake = !isBrake;
	}
	
	public boolean brakeState(){
		return isBrake;
	}
	
	boolean rightAngle(){
		hDirection = this.minecart.getPassenger().getLocation().getDirection();
		hDirection.setY(0);
	//	if(hDirection.angle(minecart.getLocation().getDirection().setY(0)) < 0.7 ||
		//   hDirection.angle(minecart.getLocation().getDirection().setY(0)) > 5.6){
			return true;
		//}
			//return false;
	}
	
	public void drive(){
		
		if(!isBrake){
			if(rightAngle()){
				minecart.setVelocity(hDirection);
			}
			else{
                Vector oldDirection = hDirection;
				minecart.setVelocity(oldDirection);
			}
		}
		else{
			minecart.setVelocity(new Vector(0,0,0));
		}
	}
	
}
