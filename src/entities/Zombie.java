package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import audio.AudioPlayer;

public abstract class Zombie extends SpriteObject{

	/**
	 * This class implements a type of Zombie, the correct gerarchy for zombies should be
	 * Zombie >> MammaZombie
	 * Zombie >> Zombie2
	 * The game implement only 1 type of Zombie so there is no interface Zombie
	 * 
	 * By making the player Singleton, we can update all single Zombie from a different thread
	 * wich control the player. In this way we can access the player coordinated in the map
	 * and go hunt for him without passing the updated position everytime to the ZombieUpdate thread. 
	 * 
	 * @author Giovanni Romio
	 */


	/* Check if player is in the range of the zombie */
	protected int range;
	/* Player */
	protected Player player;	
	/* Base */
	protected Base base;
	/* Di oggetti di questa classe, a differenze del player ne vogliamo istanzare certamente piu di uno*/
	protected double damage;
	protected boolean attackingPlayer;
	protected boolean attackingBase;
	protected long timerPlayerAttack;
	protected long timerBaseAttack;
	protected long hitDelay;
	protected Rectangle vision;
	/* Audio when zombies gets hit*/
	AudioPlayer ap;

	/**
	 * Constructor for the Zombie
	 * @param xSpawn rapresent the position on the map where the Zombie will be put
	 * @param ySpawn rapresent the position on the map where the Zombie will be put
	 */

	public Zombie(int xSpawn, int ySpawn) {
		/* When the new zombies is create we set the coordinate wich it will appear */
		this.xMap=this.xScreen = xSpawn;
		this.yMap=this.yScreen = ySpawn;
		this.player = Player.getIstance();
		this.base = Base.getIstance();
		this.attackingBase = false;
		this.attackingPlayer = false;
		this.hitDelay = 1000;
		this.range = 200;
		this.init();
		ap = new AudioPlayer("/audio/zombie2.wav");
	}

	/**
	 * Initialize zombie parameters
	 */
	public abstract void init();	

	/** 
	 * Check if player if on range vision of the zombie
	 * @return true if player in in vision range of the zombie otherwise return false
	 */
	private boolean visionRange() {
		vision = new Rectangle((int)xMap - (range / 2), (int)yMap - (range / 2), range + width, range + height);
		if(vision.intersects(player.getRectangle())){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Calculate the position where the zombie will move forward 
	 */

	protected void calculatePosition(){
		/* Calculate distance between zombie and player, zombie base. */
		if(visionRange()){
			double rapporto = Math.atan2((player.getYMap() - yMap),(player.getXMap() - xMap));
			/* Object: hunt player*/
			xMap += speed * Math.cos(rapporto);
			yMap += speed * Math.sin(rapporto);
			/* Update Screen coordinates */
			xScreen += speed * Math.cos(rapporto);
			yScreen += speed * Math.sin(rapporto);
		}else{
			/* Object: hunt base */
			if(!base.intersect(this.getRectangle())){
				double rapporto = Math.atan2((0 - yMap),(310 - xMap));
				xMap += speed * Math.cos(rapporto);
				yMap += speed * Math.sin(rapporto);
				/* Update Screen coordinates */
				xScreen += speed * Math.cos(rapporto);
				yScreen += speed * Math.sin(rapporto);
			}else{
				return;
			}
		}

	}

	/**
	 * If one bullet hit the zombie, we decrease the zombie life
	 * @param danno rapresent the damage of the bullet wich hit the zombie
	 */
	public void hit(int damage){
		ap.stop();
		ap.start();
		this.hp -= damage;
		if(hp <= 0){
			alive = false;
		}
		
	}

	/**
	 * @return the damage of the zombie
	 */
	public double getDamage(){
		return damage;
	}

	/**
	 * This is the method wich constatly move the zombie forwar tha player or the base
	 */
	public void update(){
		walk.update();
		this.calculatePosition();		
	}

	/**
	 * Draw is called from the Contoller di Sessione draw() wich is called from the main thread.
	 * @param g rapresent the graphic component to display and draw sprites.
	 */
	public void draw(Graphics2D g){
		AffineTransform at = new AffineTransform();
		/**
		 * Disegniamo lo zombie solo se � nel campo di visibilit� del giocatore 
		 */
		if(player.getXMap() > 320 && player.getXMap() < 410){
			xScreen =  320 + (xMap - player.getXMap());
		}
		if(player.getXMap() > 410){
			xScreen = 640 - (730 - xMap);
		}
		if(player.getYMap() > 240 && player.getYMap() < 814){
			yScreen = 240 + (yMap - player.getYMap());
		}
		if(player.getYMap() > 814){
			yScreen = 480 - (1054 - yMap);
		}
		at.translate(xScreen, yScreen);
		if(visionRange()){
			/*Turn on player position */
			at.rotate(Math.atan2(player.getYScreen() - yScreen,player.getXScreen() - xScreen) + 1.5, 14.5, 17);
		}else{
			/* Turn on base position */
			at.rotate(Math.atan2(0 - yMap, 310 - xMap) + 1.5, 14.5, 17);
		}
		try{
			g.drawImage(walk.getImage(), at, null);
		}
		catch(IndexOutOfBoundsException e){
			/* If animation lunch indexoutofBound we lose only a single frame */
			return;
		}

	}

	/** 
	 * This method use a Timer to subdivide the zombies attacks in different actions.
	 * By doing this we avoid the speed of the game influence the gameplay.
	 */	
	public void attack(){

		if(this.getRectangle().intersects(player.getRectangle())){
			if(attackingPlayer == false){
				timerPlayerAttack = System.currentTimeMillis();
			}
			attackingPlayer = true;
			if(System.currentTimeMillis() > (timerPlayerAttack+hitDelay)){
				player.hit(this.damage);
				attackingPlayer = false;
			}
		}
		if(base.getCollisionPolygon().intersects(this.getRectangle())){
			if(attackingBase == false){
				timerBaseAttack = System.currentTimeMillis();
			}
			attackingBase = true;
			if(System.currentTimeMillis() > (timerBaseAttack+hitDelay)){
				base.hit(this.damage);
				attackingBase = false;
			}
		}
	}

}
