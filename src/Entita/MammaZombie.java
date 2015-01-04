package Entita;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class MammaZombie extends Modello2d{
	/*Varibile che controlla se il player � nel range dello zombie*/
	private boolean range;
	//player
	private Giocatore player;	
	//Base
	private Base base;
	/*Di oggetti di questa classe, a differenze del player ne vogliamo istanzare certamente piu di uno*/
	public MammaZombie(int xSpawn, int ySpawn,Giocatore player,Base base) {
		/*Quando creiamo lo zombie gli passiamo le coordinate dalle quali verra creato*/
		this.xMap=this.xScreen=xSpawn;
		this.yMap=this.yScreen=ySpawn;
		this.player = player;
		this.base = base;
		this.init();
	}
	public void init(){
		/*Carichiamo l'immagine dello zombie*/
		this.sprite=setSprite("/sprites/zombieMom.png");
		/*Il nostro file png contiene 5 diversi sprite che compono la camminata*/
		this.width= sprite.getWidth()/4;
		this.height= sprite.getHeight();
		/*Creiamo l'animazione per il nostro personaggio*/
		this.setCamminata(sprite, width, height);
		/*Inizializziamo la vita dello zombie*/
		this.hp = 25;
	
	}
	/*Controlla se il giocatore � nel raggio di visione del giocatore*/
	public boolean visionRange() {
		/*TODOOO FARLO PARAMETRICO CON IL CAMPO RANGE*/
		Rectangle vision = new Rectangle((int)xMap-100, (int)yMap-100, 200+width, 200+height);
		if(vision.intersects(player.getRectangle())){
			return true;
		}else{
			return false;
		}

	}
	public void calcolaPosizione(){
		/*Calcoliamo la distanza tra zombie e giocare e zombie base*/
		/*Ipotizziamo che la base sia nel punto (310,0)*/
		if(visionRange()){
			double rapporto = Math.atan2((player.getYMap()-yMap),(player.getXMap()-xMap));
			/*Obbiettivo: caccia giocatore*/
			xMap += Math.cos(rapporto);
			yMap += Math.sin(rapporto);
			/*Aggiorniamo le coordinate nella finestra*/
			xScreen += Math.cos(rapporto);
			yScreen += Math.sin(rapporto);
		}else{
			/*Obbiettivo attacca la base*/
			if(!base.intersect(this.getRectangle())){
				double rapporto = Math.atan2((0-yMap),(310-xMap));
				xMap += Math.cos(rapporto);
				yMap += Math.sin(rapporto);
				/*Aggiorniamo le coordinate nella finestra*/
				xScreen += Math.cos(rapporto);
				yScreen += Math.sin(rapporto);
			}else{
				return;
			}
		}

	}
	public void colpito(int danno){
		this.hp -= danno;
	}
	public void update(){
		camminata.update();
		this.calcolaPosizione();		
	}
	public void draw(Graphics2D g){
		AffineTransform at = new AffineTransform();
		//Disegniamo lo zombie solo se � nel campo di visibilit� del giocatore
		if(player.getXMap()>320 && player.getXMap()<410){
			xScreen=  320+(xMap-player.getXMap());
		}
		if(player.getYMap()>240 && player.getYMap()<814){
			yScreen= 240+(yMap-player.getYMap());
		}
		at.translate(xScreen, yScreen);
		if(visionRange()){
			/*Girato verso il giocatore*/
			at.rotate(Math.atan2(player.getYScreen()-yScreen,player.getXScreen()-xScreen)+1.5,14.5,17);
		}else{
			/*Girato verso la base*/
			at.rotate(Math.atan2(0-yMap,310-xMap)+1.5,14.5,17);
		}
		try{
			g.drawImage(camminata.getImage(),at , null);
		}
		catch(IndexOutOfBoundsException e){
			return;
		}
	}

}
