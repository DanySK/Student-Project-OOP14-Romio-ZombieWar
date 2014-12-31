package Entita;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import Armi.ArmaImpl;

public class Giocatore extends Modello2d {
	//Vogliamo che all'interno del gioco venga creata un'unica entit� player
	private static Giocatore giocatore;
	//Sprite del personaggio
	BufferedImage player;
	ArmaImpl[]arsenale;
	ArmaImpl armacorrente;
	//Sparo
	private boolean reloading;
	private long startTime;
	private boolean shooting;
	private BufferedImage crosshair;

	private Giocatore(){
		this.init();
	}
	private static class Holder {
		static final Giocatore INSTANCE = new Giocatore();
	}
	public static Giocatore getInstance() {
		return Holder.INSTANCE;
	}
	public static Giocatore getIstance(){
		if(giocatore==null){
			giocatore = new Giocatore();
		}
		return giocatore;		
	}

	public void init(){
		/*Cambiamo l'immagine del puntatore*/
		try {
			this.crosshair = ImageIO.read(getClass().getResourceAsStream("/sprites/cross.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Carichiamo l'immagine del nostro personaggio*/
		this.player=setSprite("/sprites/avion2.png");
		/*Assegniamo al nostro personaggio le coordinate di orgine predefinite*/
		xMap=yMap=50;
		xScreen=yScreen=50;
		/*Il nostro file png contiene 5 diversi sprite che compono la camminata*/
		this.width= player.getWidth()/5;
		this.height= player.getHeight();
		/*Creiamo l'animazione per il nostro personaggio*/
		this.setCamminata(player, width, height);		
	}

	/*Metodi per il movimento*/
	public void setLeft(boolean value){
		left = (value == true) ? true : false;
	}

	public void setRight(boolean value){
		right = (value == true) ? true : false;
	}

	public void setUp(boolean value){
		up = (value == true) ? true : false;
	}

	public void setDown(boolean value){
		down = (value == true) ? true : false;
	}

	public void calcolaPosizione(){
		//MOVIMENTI
		if (left){ 
			if(xMap<(640/2) || xMap > 730-320 ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				xScreen -= 2;
				xMap-=2;			
				if( xMap < 0 ){ xMap = 0; }			
			}
			else { xMap -=2 ; }
		}
		if (right){ 
			if(xMap<(640/2) || xMap > 730-320 ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				xScreen += 2;
				xMap+=2;
				if(xMap>730-width){ xMap = 730-width; }			
			}
			else { xMap += 2; }
		}				
		if (up){
			if(yMap<(480/2) || yMap > (1054-240) ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				yScreen -= 2;
				yMap -= 2;
				if(yMap<topy){ yMap = topy; }
			}
			else{ yMap -= 2; }
		}	
		if (down){
			if(yMap<(480/2) || yMap > (814) ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				yScreen += 2;
				yMap +=2;
				if(yMap>bottomy-width) {yMap = bottomy-width; }
			}
			else{ yMap +=2;	}
		}
	}
	public void setWeapons(ArmaImpl[]arsenale){
		this.arsenale=arsenale;
		armacorrente=this.arsenale[2];
	}
	public void calcolaRotazione(){
		/*Rotazione dello sprite*/
		rotazione = Math.atan2(MouseInfo.getPointerInfo().getLocation().getY()-(this.yScreen+height/2),
				MouseInfo.getPointerInfo().getLocation().getX()-(this.xScreen+width/2))-Math.PI/2;
	}

	public void update(){
		if(reloading){
			this.reload();
		}
		/*Se comandiamo al personaggio di spostarsi eseguiamo questa routine*/
		if(left||right||up||down){
			camminata.update();
			this.calcolaPosizione();
		}
		this.calcolaRotazione();
	}

	public void draw(Graphics2D g){
		AffineTransform at = new AffineTransform();
		at.translate(xScreen, yScreen);
		at.rotate(rotazione,width/2,height/2);
		/*Disegno il giocatore*/
		g.drawImage(camminata.getImage(),at,null);
		/*Disegno l'arma corrente, associando le stesse operazione di movimento legate al giocatore*/
		//draw GUN
		AffineTransform gun =new AffineTransform(at);	
		gun.scale(0.8,0.8);
		gun.translate(armacorrente.getX(),armacorrente.getY());
		g.drawImage(armacorrente.getImage(),gun,null);
		/*Disegna mirino*/
		g.drawImage(crosshair,MouseInfo.getPointerInfo().getLocation().x,MouseInfo.getPointerInfo().getLocation().y,null);

	}
	public boolean shoot(double xMouse,double yMouse,List<Proiettile>l){
		if(armacorrente.shoot(this,xMouse,yMouse,l)>0){
			reloading=false;
			return true;
		}
		else{	
			this.reload();
			return false;
		}
	}
	private void reload(){
		this.reloading = true;
		armacorrente.reload();
	}

}
