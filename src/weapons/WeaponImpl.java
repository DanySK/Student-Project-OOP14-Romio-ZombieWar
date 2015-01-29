package weapons;

import java.awt.image.BufferedImage;
import java.util.List;

import audio.AudioPlayer;
import entities.Bullet;

public abstract class WeaponImpl implements Weapon {
	
	/**
	 * Abstarct class that implements Arma. This class let use the polimorfism construct.
	 * ArmaImpl describe the common methods shared by all Weapons and the common variables.
	 * 
	 * @author Giovanni Romio
	 */
	
	protected String name;
	protected int damage;
	protected int bullets;
	protected int bulletsPerRound;
	protected BufferedImage sprite;
	protected BufferedImage HUDsprite;
	/* Reload timer */
	protected boolean reloading;
	protected int realoadTime;
	protected long start;
	protected long end;
	/* draw coordinates */
	protected int x;
	protected int y;
	/* This list contains all diplayed bullets */
	protected List<Bullet> list;
	/*Sound effect*/
	protected AudioPlayer ap;

	/**
	 * This method use a Timer to block and restart the gun.
	 * While the gun is realoding it cannot shoot.
	 */
	public void reload() {
		if(!reloading){
			this.start= System.currentTimeMillis();
			reloading =true;
		}
		if(reloading){
			if((end=System.currentTimeMillis())>(start+1500))
			{
				/* Stop reload */
				this.bullets = bulletsPerRound;
				reloading = false;
			}
		}

	}

	/**
	 * @return sprite image of the weapon.
	 */
	
	public BufferedImage getImage() {
		return sprite;
	}
	
	/**
	 * 
	 * @return gives the sprite to display in HUD
	 */
	
	public BufferedImage getHUDImage(){
		return HUDsprite;
	}
	
	/**
	 * 
	 * @return return the position of the weapon
	 */
	
	public int getX(){
		return this.x;
	}
	
	/**
	 * 
	 * @return the position of the weapon
	 */
	
	public int getY(){
		return this.y;
	}
	
	/**
	 * 
	 * @return the damage of the weapon
	 */
	
	public int getDamage() {
		return damage;
	}
	
	/**
	 * 
	 * @return the name of the weapon
	 */
	
	public String getWeaponName(){
		return name;
	}
	
	/**
	 * 
	 * @return how many bullets left
	 */
	
	public int getColpi(){
		return this.bullets;
	}
	
	/**
	 * 
	 * @return if the current weapon is realoding or not
	 */
	
	public boolean isReloading(){
		return reloading;
	}

}
