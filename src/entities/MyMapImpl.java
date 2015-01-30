package entities;

import java.awt.Graphics2D;

/**
 * Map of the Game
 * 
 * @author Giovanni Romio
 */
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MyMapImpl implements MyMap{
	
	/*Map image*/
	private BufferedImage mappa;
	/*player position equals to camera position*/
	private double camerax,cameray;
	/*Coordinates to calculate player trajectory*/
	private double previous_Yposition=0;
	private double previous_Xposition=0;
	
	public MyMapImpl(String path){
		
		try {
			mappa = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param d rapresent the xpostion of the player
	 * @param e rapresent the yposition of the player
	 */
	
	public void update(double d,double e){
		/*Dobbiamo seguire il personaggio con la nostra camera, noi muoviamo la mappa sotto*/
		if(d>320 && d<410 && previous_Xposition<=this.camerax){
			//going right
			this.camerax=(d-320);
			previous_Xposition = this.camerax;
		}
		else if(d>320 && d<410 && previous_Xposition>this.camerax){
			//going left
			this.camerax=(d-320);
			previous_Xposition = this.camerax;
		}		
		if((e)>240 && (e<814) && previous_Yposition<=this.cameray){
			//going down
			this.cameray=(e-240);
			previous_Yposition= this.cameray;			
		}
		else if((e)>240 && (e<814)  && previous_Yposition>this.cameray){
			//going up
			this.cameray=(e+240);
			previous_Yposition=this.cameray;			
		}
	}
	
	/**
	 * 
	 * @param g is the graphic component of the main Panel
	 */	
	public void draw(Graphics2D g){		
		g.drawImage(mappa,(int)-camerax,(int)-cameray,null);
	}
	

}
