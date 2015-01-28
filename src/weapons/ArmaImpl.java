package weapons;

import java.awt.image.BufferedImage;
import java.util.List;

import entities.Bullet;

public abstract class ArmaImpl implements Arma {
	/**
	 * Abstarct class that implements Arma. This class let use the polimorfism construct.
	 * ArmaImpl describe the common methods shared by all Weapons and the common variables.
	 * 
	 * @author Giovanni Romio
	 */
	protected String nome;
	protected int danno;
	protected int colpi;
	protected int caricatore;
	protected BufferedImage sprite;
	protected BufferedImage HUDsprite;
	/* Timer del caricatore */
	protected boolean reloading;
	protected int realoadTime;
	protected long start;
	protected long end;
	/* Coordinate per il draw */
	protected int x;
	protected int y;
	/* Lista contenente tutti i colpi attualmente in esecuzione */
	protected List<Bullet> list;

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
				/** Ha finito di caricare */
				this.colpi=caricatore;
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
		return danno;
	}
	/**
	 * 
	 * @return the name of the weapon
	 */
	public String getWeaponName(){
		return nome;
	}
	/**
	 * 
	 * @return how many bullets left
	 */
	public int getColpi(){
		return this.colpi;
	}

}
