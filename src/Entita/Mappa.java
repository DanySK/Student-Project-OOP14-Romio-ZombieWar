package Entita;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mappa{
	/*Immagine della mappa*/
	BufferedImage mappa;
	/*Coordinate del personaggio*/
	int camerax,cameray;
	/*Coordinate per calcolare la traiettoria del personaggio*/
	int previous_Yposition=0;
	int previous_Xposition=0;
	
	public Mappa(String path){
		try {
			mappa = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(int playerx,int playery){
		/*Dobbiamo seguire il personaggio con la nostra camera, noi muoviamo la mappa sotto*/
		if(playerx>320 && playerx<410 && previous_Xposition<=this.camerax){
			//going right
			this.camerax=(playerx-320);
			previous_Xposition = this.camerax;
		}
		else if(playerx>320 && playerx<410 && previous_Xposition>this.camerax){
			//going left
			this.camerax=(playerx-320);
			previous_Xposition = this.camerax;
		}		
		if((playery)>240 && (playery<814) && previous_Yposition<=this.cameray){
			//going down
			this.cameray=(playery-240);
			previous_Yposition= this.cameray;			
		}
		else if((playery)>240 && (playery<814)  && previous_Yposition>this.cameray){
			//going up
			this.cameray=(playery+240);
			previous_Yposition=this.cameray;			
		}
	}
	
	public void draw(Graphics2D g){
		g.drawImage(mappa,-camerax,-cameray,null);
	}
	
}
