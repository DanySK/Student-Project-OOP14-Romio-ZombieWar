
package entities;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;
import weapons.AK47;
import weapons.Glock;
import weapons.Minigun;
import weapons.WeaponImpl;

public class Player extends SpriteObject {
	/**
	 * This class Rapresent the Player of the game.
	 * It implementes the Singleton pattern: there is only one istance of this class in the game.
	 *  
	 *  @author Giovanni Romio
	 */
	
	/* Movements */
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	//Vogliamo che all'interno del gioco venga creata un'unica entita player
	private static Player giocatore;
	//Base del gioco, utile per impostare i limiti di percorso
	private Base base;
	private WeaponImpl [] arsenale;
	private int armacorrente;
	// Shoot
	private boolean reloading;
	/* Player bounds */
	private static final int leftx=0;
	private static final int rightx=730;
	private static final int topy=0;
	private static final int bottomy=1054;
	/* Weapons */
	public static final int GLOCK = 0;
	public static final int AK47 = 1;
	public static final int MINIGUN = 2;
	
	/**
	 * 
	 * @return singleTon istance
	 */
	
	public static Player getIstance(){	

		if(giocatore == null){
			giocatore = new Player();
		}
		return giocatore;		
		
	}
	/**
	 * Initialize player parameters such as position in the map and Health Point.
	 */
	public void init(){				
		xMap = yMap = 50;
		xScreen = yScreen = 50;
		hp = 25;
		alive = true;
		base = Base.getIstance();
		speed = 2;
		arsenale = new WeaponImpl[3];
		arsenale[0] = new Glock();
		arsenale[1] = new AK47();
		arsenale[2] = new Minigun();
		left = false;
		up = false;
		right = false;
		down = false;
	}
	/**
	 * 
	 * @param value allow player move left or stop move left
	 */
	public void setLeft(boolean value){				
		left = (value == true) ? true : false;		
	}
	/**
	 * 
	 * @param value allow player move right or stop move right
	 */
	public void setRight(boolean value){		
		right = (value == true) ? true : false;		
	}
	/**
	 * 
	 * @param value allow player move up or stop move up
	 */
	public void setUp(boolean value){		
		up = (value == true) ? true : false;		
	}
	/**
	 * 
	 * @param value allow player move down or stop move down
	 */
	public void setDown(boolean value){			
		down = (value == true) ? true : false;		
	}
	
	/**
	 * Calculate the position on the screen.
	 * When the player is exploring middle areas of the Map his position will be stopped and 
	 * the Map will be moved so the effect of the player moving in the Map is granted!
	 */

	private void calculatePosition(){
		//MOVEMENTS
		if (left){
			if(xMap < (640 / 2) || xMap > rightx - 320 ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				xScreen -= speed;
				xMap -= speed;
				if(base.intersect(this.getRectangle())){
					xScreen += speed;
					xMap += speed;
				}
				if( xMap < leftx ){
					xMap = leftx;
					xScreen = leftx;
				}			
			}
			else {
				xMap -= speed ;
			}
		}
		if (right){ 
			if(xMap < (640 / 2) || xMap > rightx - 320 ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				xScreen += speed;
				xMap += speed;
				if(base.intersect(this.getRectangle())){
					xScreen -= speed;
					xMap -= speed;
				}
				if(xMap > 730 - width){					
					xMap = 730 - width;
					xScreen = 640-width;
				}			
			}
			else { xMap += speed; }
		}				
		if (up){
			if(yMap<(480/2) || yMap > (1054-240) ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				yScreen -= speed;
				yMap -= speed;
				if(base.intersect(this.getRectangle())){
					yScreen += speed;
					yMap += speed;
				}
				if(yMap<topy){
					yMap = topy;
					yScreen = topy;
				}
			}
			else{ 
				yMap -= speed;
			}
		}	
		if (down){
			if(yMap<(480/2) || yMap > (1054-240) ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				yScreen += speed;
				yMap += speed;
				if(yMap>bottomy-width) {
					yMap = bottomy - width; 
					yScreen = 480 - width; 
				}
			}
			else{
				yMap += speed;	
			}
		}
	}

	/**
	 * Change weapons
	 * @param arsenale rapresent the weapon of the Player
	 */
	
	public void setWeapons(WeaponImpl[]arsenale){			
		this.arsenale = arsenale;
		armacorrente = GLOCK;		
	}
	
	/**
	 * The player will always be oriented to the mouse Cursor
	 */
	
	private void calculateRotation(int x, int y){
		/*Rotazione dello sprite*/
		rotation = Math.atan2(y-(this.yScreen+height/2),x-(this.xScreen+width/2))-Math.PI/2;
	}
	
	/**
	 * If the player isn't realoding it calls the current wepaon method shoot wich
	 * will add a bullet into l
	 * @return if the player shooted it return true otherwise it return false
	 * @param xMouse mouse poisition
	 * @param yMouse mouse position
	 * @param l rapresent the list wich contain displayed bullet
	 */
	
	public boolean shoot(double xMouse, double yMouse, List<Bullet>l){
		if(arsenale[armacorrente].shoot(this,xMouse,yMouse,l) > 0){
			reloading=false;
			return true;
		}else{	
			this.reload();
			return false;
		}		
	}

	/**
	 * Set the gun to reload 
	 */
	
	private void reload(){
		arsenale[armacorrente].reload();
		if(arsenale[armacorrente].isReloading()){
			reloading = true;
		}else{
			reloading = false;
		}
	}
	
	/**
	 * 
	 * @param i value to change the current gun hold by the player
	 */
	
	public void setGun(int i){			
		armacorrente = i;	
	}
	
	/**
	 * 
	 * @return the current weapon
	 */
	
	public WeaponImpl getWeapon(){
		return arsenale[armacorrente];
	}
	
	/**
	 * Set up the walk animation for the player
	 * @param path soruce for the image of the player
	 */
	
	public void setSkin(String path){		
		this.sprite = setSprite(path);
		/*Il nostro file png contiene 5 diversi sprite che compono la camminata*/
		this.width = sprite.getWidth() / 5;
		this.height = sprite.getHeight();
		/*Creiamo l'animazione per il nostro personaggio*/
		this.setWalkAnimation(sprite, width, height);
		
	}
	
	/**
	 * 
	 * @param d is the damage of the zombi
	 */
	
	public void hit(double d){			
		this.hp -= d;
		if(hp == 0){
			alive = false;
		}
	}
	
	public void update() {		
	}	
	
	/**
	 *  Update is called from called from the Contoller di Sessione wich is called form the main
	 *  thread and will update player position rotation and stutus.
	 *  @param x rapresent the realtive position of the Mouse in JFrame
	 *  @param y rapresent the realtive position of the Mouse in JFrame
	 */
	public void update(int x, int y){
		if(reloading){
			this.reload();
		}		
		/*Se comandiamo al personaggio di spostarsi eseguiamo questa routine*/
		if(left||right||up||down){
			walk.update();
			this.calculatePosition();
		}
		this.calculateRotation(x,y);		
	}
	
	
	/**
	 * Draw is called from the Contoller di Sessione draw() wich is called from the main thread.
	 * @param g rapresent the graphic component to display and draw sprites.
	 */	
	public void draw(Graphics2D g){			
		AffineTransform at = new AffineTransform();
		at.translate(xScreen, yScreen);
		at.rotate(rotation,width/2,height/2);
		/* Draw player */
		g.drawImage(walk.getImage(),at,null);
		/*Draw current weapon with player affine trasform operation */
		/* Draw GUN */
		AffineTransform gun =new AffineTransform(at);	
		gun.scale(0.8,0.8);
		gun.translate(arsenale[armacorrente].getX(),arsenale[armacorrente].getY());
		g.drawImage(arsenale[armacorrente].getImage(),gun,null);		
	}
	
	public boolean isRealoading(){
		return this.reloading;
	}
}
