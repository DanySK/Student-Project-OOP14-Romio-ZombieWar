package Entita;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class MammaZombie extends Modello2d{

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


	/** Varibile che controlla se il player è nel range dello zombie */
	private int range;
	/** Giocatore */
	private Giocatore giocatore;	
	/** Base */
	private Base base;
	/** Di oggetti di questa classe, a differenze del player ne vogliamo istanzare certamente piu di uno*/
	private double danno;
	private boolean attaccandoGiocatore;
	private boolean attaccandoBase;
	private long tentaAttaccoGiocatore;
	private long tentaAttaccoBase;
	private long tempoPerColpire;
	private Rectangle vision;

	/**
	 * Constructor for the Zombie
	 * @param xSpawn rapresent the position on the map where the Zombie will be put
	 * @param ySpawn rapresent the position on the map where the Zombie will be put
	 */
	public MammaZombie(int xSpawn, int ySpawn) {
		/** Quando creiamo lo zombie gli passiamo le coordinate dalle quali verra creato */
		this.xMap=this.xScreen = xSpawn;
		this.yMap=this.yScreen = ySpawn;
		this.giocatore = Giocatore.getIstance();
		this.base = Base.getIstance();
		this.attaccandoBase = false;
		this.attaccandoGiocatore = false;
		this.tempoPerColpire = 1000;
		this.range = 200;
		this.init();
	}
	public void init(){
		/** Carichiamo l'immagine dello zombie */
		this.sprite = setSprite("/sprites/zombieMom.png");
		/** Il nostro file png contiene 5 diversi sprite che compono la camminata */
		this.width = sprite.getWidth()/4;
		this.height = sprite.getHeight();
		/** Creiamo l'animazione per il nostro personaggio */
		this.setCamminata(sprite, width, height);
		/** Inizializziamo la vita dello zombie */
		this.hp = 25;	
		this.danno = 1;
	}
	/** Controlla se il giocatore � nel raggio di visione del giocatore */
	public boolean visionRange() {
		vision = new Rectangle((int)xMap - (range/2), (int)yMap - (range/2), range + width, range + height);
		if(vision.intersects(giocatore.getRectangle())){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * Calculate the position where the zombie will move forward 
	 */

	public void calcolaPosizione(){
		/** Calcoliamo la distanza tra zombie e giocare e zombie base
			Ipotizziamo che la base sia nel punto (310,0) */
		if(visionRange()){
			double rapporto = Math.atan2((giocatore.getYMap() - yMap),(giocatore.getXMap() - xMap));
			/*Obbiettivo: caccia giocatore*/
			xMap += Math.cos(rapporto);
			yMap += Math.sin(rapporto);
			/** Aggiorniamo le coordinate nella finestra */
			xScreen += Math.cos(rapporto);
			yScreen += Math.sin(rapporto);
		}else{
			/** Obbiettivo attacca la base */
			if(!base.intersect(this.getRectangle())){
				double rapporto = Math.atan2((0 - yMap),(310 - xMap));
				xMap += Math.cos(rapporto);
				yMap += Math.sin(rapporto);
				/** Aggiorniamo le coordinate nella finestra */
				xScreen += Math.cos(rapporto);
				yScreen += Math.sin(rapporto);
			}else{
				return;
			}
		}

	}

	/**
	 * If one bullet hit the zombie, we decrease the zombie life
	 * @param danno rapresent the damage of the bullet wich hit the zombie
	 */

	public void colpito(int danno){

		this.hp -= danno;
		if(hp <= 0){
			alive = false;
		}
	}

	public double getDanno(){

		return this.danno;
	}

	/**
	 * This is the method wich constatly move the zombie forwar tha player or the base
	 */
	public void update(){

		camminata.update();
		this.calcolaPosizione();		
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
		if(giocatore.getXMap() > 320 && giocatore.getXMap() < 410){
			xScreen =  320+(xMap-giocatore.getXMap());
		}
		if(giocatore.getYMap() > 240 && giocatore.getYMap() < 814){
			yScreen = 240 + (yMap - giocatore.getYMap());
		}
		at.translate(xScreen, yScreen);
		if(visionRange()){
			/** Girato verso il giocatore */
			at.rotate(Math.atan2(giocatore.getYScreen() - yScreen,giocatore.getXScreen() - xScreen) + 1.5, 14.5, 17);
		}else{
			/** Girato verso la base */
			at.rotate(Math.atan2(0-yMap,310-xMap)+1.5,14.5,17);
		}
		try{
			g.drawImage(camminata.getImage(), at, null);
		}
		catch(IndexOutOfBoundsException e){
			/** Se l'animazione da un index out of bound perdiamo solamente un frame */
			return;
		}

	}

	/** 
	 * This method use a Timer to subdivide the zombies attacks in different actions.
	 * By doing this we avoid the speed of the game influence the gameplay.
	 */
	public void attack(){

		if(this.getRectangle().intersects(giocatore.getRectangle())){
			if(attaccandoGiocatore == false){
				tentaAttaccoGiocatore = System.currentTimeMillis();
			}
			attaccandoGiocatore = true;
			if(System.currentTimeMillis() > (tentaAttaccoGiocatore+tempoPerColpire)){
				giocatore.colpito(this.danno);
				attaccandoGiocatore = false;
			}
		}
		if(base.getCollisionPolygon().intersects(this.getRectangle())){
			if(attaccandoBase == false){
				tentaAttaccoBase = System.currentTimeMillis();
			}
			attaccandoBase = true;
			if(System.currentTimeMillis() > (tentaAttaccoBase+tempoPerColpire)){
				base.colpito(this.danno);
				attaccandoBase = false;
			}
		}
	}


}
