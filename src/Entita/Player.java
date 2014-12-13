package Entita;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends Modello2d {
	//Vogliamo che all'interno del gioco venga creata un'unica entità player
	private static Player giocatore;
	//Sprite del personaggio
	BufferedImage player;
	Arma[]arsenale;
	Arma armacorrente;
	
	private Player(){
		this.init();
	}
	
	public static Player getIstance(){
		if(giocatore==null){
			giocatore = new Player();
		}
		return giocatore;		
	}
	
	public void init(){
		/*Carichiamo l'immagine del nostro personaggio*/
		this.player=setSprite("/sprites/avion.png");
		/*Assegniamo al nostro personaggio le coordinate di orgine predefinite*/
		xMap=yMap=0;
		xScreen=yScreen=0;
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
				if( xScreen < 0 ){ xScreen = 0; }
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
				if(xScreen>640-width){ xScreen = 640-width; }
				if(xMap>730-width){ xMap = 730-width; }			
			}
			else { xMap += 2; }
		}				
		if (up){
			if(yMap<(480/2) || yMap > (1054-240) ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				yScreen -= 2;
				yMap -= 2;
				if(yScreen<topy){ yScreen = topy; }
				if(yMap<topy){ yMap = topy; }
			}
			else{ yMap -= 2; }
		}	
		if (down){
			if(yMap<(480/2) || yMap > (814) ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				yScreen += 2;
				yMap +=2;
				if(yScreen>= 480-width){yScreen= 480-width; }
				if(yMap>bottomy-width) {yMap = bottomy-width; }
		    }
			else{ yMap +=2;	}
		}
	}
	public void setWeapons(Arma[]arsenale){
		this.arsenale=arsenale;
		armacorrente=this.arsenale[0];
	}
	public void calcolaRotazione(){
		/*Rotazione dello sprite*/
		rotazione = Math.atan2(MouseInfo.getPointerInfo().getLocation().getY()-this.yScreen,
				MouseInfo.getPointerInfo().getLocation().getX()-this.xScreen)-Math.PI/2;
	}

	public void update(){
		/*Se comandiamo al personaggio di spostarsi eseguiamo questa routine*/
		if(left||right||up||down){
			camminata.update();
			this.calcolaPosizione();
		}
		this.calcolaRotazione();
	}
	
	public void draw(Graphics2D g){
		AffineTransform at = new AffineTransform();
		at.scale(0.8, 0.8);
		at.translate(xScreen, yScreen);
		at.rotate(rotazione,width/2,height/2);
		/*Disegno il giocatore*/
		g.drawImage(camminata.getImage(),at,null);
		/*Disegno l'arma corrente, associando le stesse operazione di movimento legate al giocatore*/
		//draw GUN
		AffineTransform gun =new AffineTransform(at);	
		gun.translate(armacorrente.xTRANSLATE,armacorrente.yTRANSLATE);
		g.drawImage(armacorrente.getImage(),gun,null);

	}
	
}
