package Entita;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mappa{
	/*Immagine della mappa*/
	private BufferedImage mappa;
	/*Coordinate del personaggio*/
	private double camerax,cameray;
	/*Coordinate per calcolare la traiettoria del personaggio*/
	private double previous_Yposition=0;
	private double previous_Xposition=0;
	/*Vettore contenente gli spruzzi di sangue*/
	public Mappa(String path){
		try {
			mappa = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

	public void draw(Graphics2D g){
		g.drawImage(mappa,(int)-camerax,(int)-cameray,null);
	}

}
