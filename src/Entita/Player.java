package Entita;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends Modello2d {
	//Sprite del personaggio
	BufferedImage player;
	public Player(){
		this.init();
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
				if(xScreen>730){ xScreen = 730; }
				if(xMap>730){ xMap = 730; }			
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

	public void update(){
		/*Se comandiamo al personaggio di spostarsi eseguiamo questa routine*/
		if(left||right||up||down){
			camminata.update();
			this.calcolaPosizione();
		}		
		
	}
	
	public void draw(Graphics2D g){
		g.drawImage(camminata.getImage(),xScreen,yScreen,null);
	}
	
}
