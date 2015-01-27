
package Entita;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;

import Armi.ArmaImpl;

public class Giocatore extends Modello2d {
	/**
	 * This class Rapresent the Player of the game.
	 * It implementes the Singleton pattern: there is only one istance of this class in the game.
	 *  
	 *  @author Giovanni Romio
	 */
	
	//Vogliamo che all'interno del gioco venga creata un'unica entita player
	private static Giocatore giocatore;
	//Base del gioco, utile per impostare i limiti di percorso
	private Base base;
	//Sprite del personaggio
	private BufferedImage player;
	private ArmaImpl [] arsenale;
	private ArmaImpl armacorrente;
	//Sparo
	private boolean reloading;

	public static Giocatore getIstance(){	
		
		if(giocatore == null){
			giocatore = new Giocatore();
		}
		return giocatore;				
	}
	/**
	 * Initialize player parameters such as position in the map and Health Point.
	 * */
	public void init(){		
		
		xMap = yMap = 50;
		xScreen = yScreen = 50;
		hp = 25;
		alive = true;
		base = Base.getIstance();
	}
	/*
	 * Those methods allow the player move in the Jframe
	 * **/
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
	
	/**
	 * Calculate the position on the screen.
	 * When the player is exploring middle areas of the Map his position will be stopped and 
	 * the Map will be moved so the effect of the player moving in the Map is granted!
	 */

	public void calcolaPosizione(){
		//MOVIMENTI
		if (left){
			if(xMap < (640 / 2) || xMap > 730 - 320 ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				xScreen -= 2;
				xMap -= 2;
				if(base.intersect(this.getRectangle())){
					xScreen += 2;
					xMap +=2;
				}
				if( xMap < 0 ){
					xMap = 0;
					xScreen = 0;
				}			
			}
			else {
				xMap -= 2 ;
			}
		}
		if (right){ 
			if(xMap < (640 / 2) || xMap > 730 - 320 ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				xScreen += 2;
				xMap += 2;
				if(base.intersect(this.getRectangle())){
					xScreen -= 2;
					xMap -=2;
				}
				if(xMap > 730 - width){					
					xMap = 730 - width;
					xScreen = 640-width;
				}			
			}
			else { xMap += 2; }
		}				
		if (up){
			if(yMap<(480/2) || yMap > (1054-240) ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				yScreen -= 2;
				yMap -= 2;
				if(base.intersect(this.getRectangle())){
					yScreen += 2;
					yMap +=2;
				}
				if(yMap<topy){
					yMap = topy;
					yScreen = topy;
				}
			}
			else{ 
				yMap -= 2;
			}
		}	
		if (down){
			if(yMap<(480/2) || yMap > (814) ){
				//siamo nei due range in cui lo sprite si deve effettivamente muovere nella finestra
				yScreen += 2;
				yMap +=2;
				if(yMap>bottomy-width) {
					yMap = bottomy - width; 
					yScreen = 480 - width; 
				}
			}
			else{
				yMap += 2;	
			}
		}
	}

	/**
	 * Change weapons
	 * @param arsenale rapresent the weapon of the Player
	 */
	
	public void setWeapons(ArmaImpl[]arsenale){	
		
		this.arsenale=arsenale;
		armacorrente=this.arsenale[0];		
	}
	
	/**
	 * The player will always be oriented to the mouse Cursor
	 */
	
	public void calcolaRotazione(int x, int y){
		/*Rotazione dello sprite*/
		rotazione = Math.atan2(y-(this.yScreen+height/2),x-(this.xScreen+width/2))-Math.PI/2;
	}
	
	/**
	 * If the player isn't realoding it calls the current wepaon method shoot wich
	 * will add a bullet into l
	 * @return if the player shooted it return true otherwise it return false
	 */
	
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
	
	public void setGun(int i){	
		
		armacorrente = this.arsenale[i];		
	}
	
	public ArmaImpl getWeapon(){
		return armacorrente;
	}
	
	/**
	 * Set up the walk animation for the player
	 * @param path soruce for the image of the player
	 */
	
	public void setSkin(String path){
		
		this.player=setSprite(path);
		/*Il nostro file png contiene 5 diversi sprite che compono la camminata*/
		this.width= player.getWidth()/5;
		this.height= player.getHeight();
		/*Creiamo l'animazione per il nostro personaggio*/
		this.setCamminata(player, width, height);
		
	}
	
	public void colpito(double d){	
		
		if(hp==0){
			alive = false;
		}else{
			this.hp -= d;
		}		
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
			camminata.update();
			this.calcolaPosizione();
		}
		this.calcolaRotazione(x,y);		
	}
	
	/**
	 * Player use the update method with x and y parameter
	 */
	
	public void update() {
		
	}
	
	/**
	 * Draw is called from the Contoller di Sessione draw() wich is called from the main thread.
	 * @param g rapresent the graphic component to display and draw sprites.
	 */
	
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
	}
	
	public boolean isRealoading(){
		return this.reloading;
	}
	
}
