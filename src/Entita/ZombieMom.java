package Entita;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ZombieMom extends Modello2d{
	BufferedImage zombie;
	Thread zombieThread;
	double playerDistance;
	double baseDistance;	
	//player
	private double playerXScreen;
	private double playerYScreen;
	private double playerXMAP;
	private double playerYMAP;	
	/*Di oggetti di questa classe, a differenze del player ne vogliamo istanzare certamente piu di uno*/
	public ZombieMom(int xSpawn, int ySpawn){
		/*Quando creiamo lo zombie gli passiamo le coordinate dalle quali verra creato*/
		this.xMap=xSpawn;
		this.yMap=ySpawn;
		this.init();
	}
	public void init(){
		/*Carichiamo l'immagine dello zombie*/
		this.zombie=setSprite("/sprites/zombieMom.png");
		/*Assegniamo al nostro personaggio le coordinate di orgine predefinite*/
		xMap=yMap=0;
		xScreen=yScreen=0;
		/*Il nostro file png contiene 5 diversi sprite che compono la camminata*/
		this.width= zombie.getWidth()/4;
		this.height= zombie.getHeight();
		/*Creiamo l'animazione per il nostro personaggio*/
		this.setCamminata(zombie, width, height);
	}
	public void calcolaPosizione(){
		/*Calcoliamo la distanza tra zombie e giocare e zombie base*/
		/*Ipotizziamo che la base sia nel punto (310,0)*/	
		playerDistance = Math.pow(playerYMAP-yMap,2)+ Math.pow(playerXMAP-xMap,2);
		baseDistance =  Math.pow(0-yMap,2)+ Math.pow(310-xMap,2);
		if(playerDistance<baseDistance){
			double rapporto = Math.atan2((playerYMAP-yMap),(playerXMAP-xMap));
			/*Obbiettivo: caccia giocatore*/
			xMap += Math.cos(rapporto);
			yMap += Math.sin(rapporto);
			/*Aggiorniamo le coordinate nella finestra*/
			xScreen += Math.cos(rapporto);
			yScreen += Math.sin(rapporto);
		}else{
			/*Obbiettivo attacca la base*/
			double rapporto = Math.atan2((0-yMap),(310-xMap));
			xMap += Math.cos(rapporto);
			yMap += Math.sin(rapporto);
			/*Aggiorniamo le coordinate nella finestra*/
			xScreen += Math.cos(rapporto);
			yScreen += Math.sin(rapporto);
		}
		
	}
	public void update(double playerXScreen, double playerYScreen, double playerXMap, double playerYMap){
		camminata.update();
		this.playerXScreen=playerXScreen;
		this.playerYScreen=playerYScreen;
		
		this.playerXMAP = playerXMap;
		this.playerYMAP = playerYMap;
		this.calcolaPosizione();		
	}
	public void draw(Graphics2D g){
		AffineTransform at = new AffineTransform();
		//Disegniamo lo zombie solo se è nel campo di visibilità del giocatore
		if(playerXMAP>320 && playerXMAP<410){
			xScreen=  320+(xMap-playerXMAP);
		}
		if(playerYMAP>240 && playerYMAP<814){
			yScreen= 240+(yMap-playerYMAP);
		}
		at.translate(xScreen, yScreen);		
		at.rotate(Math.atan2(playerYScreen-yScreen,playerXScreen-xScreen)+1.5,14.5,17);
		g.drawImage(camminata.getImage(),at , null);
	}
		
}
