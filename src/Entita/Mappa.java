package Entita;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mappa{
	BufferedImage mappa;
	/*Coordinate del personaggio*/
	int playerx,playery;
	/*Coordinate per calcolare la traiettoria del personaggio*/
	int previous_Yposition=0;
	int previous_Xposition=0;
	
	public Mappa(String path){
		try {
			mappa = ImageIO.read(getClass().getResourceAsStream("backgrounds/map.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(int playerx,int playery){
		/*Dobbiamo seguire il personaggio con la nostra camera, noi muoviamo la mappa sotto*/
		this.playerx=playerx;
		this.playery=playery;
		if(playerx>320 && playerx<410 && previous_Xposition<=this.playerx){
			//going right
			this.playerx=(playerx-320);
			previous_Xposition = this.playerx;
		}
		else if(playerx>320 && playerx<410 && previous_Xposition>this.playerx){
			//going left
			this.playerx=(playerx-320);
			previous_Xposition = this.playerx;
		}
		
		if((playery)>240 && (playery<814) && previous_Yposition<=this.playery){
			//going down
			this.playery=(playery-240);
			previous_Yposition= this.playery;			
		}
		else if((playery)>240 && (playery<814)  && previous_Yposition>this.playery){
			//going up
			this.playery=(playery+240);
			previous_Yposition=this.playery;			
		}
	}
	
	public void draw(Graphics2D g){
		
	}
	
}
