package Entita;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Arma {
	private String nome;
	private int danno;
	private int colpi;
	private int capacita;
	private BufferedImage image;
	private boolean reloading;
	private boolean automatica;
	private long start,end;
	/*Variabile per il render visivo*/
	public int xTRANSLATE;
	public int yTRANSLATE;
	public double rotation;
	public double muzzle_rotation;
	public int xMUZZLETRANSLATE;
	public int YMUZZLETRANSLATE;

	public Arma(String nome,int danno, int capacita,boolean automatica){
		this.nome=nome;
		this.danno=danno;
		this.colpi=this.capacita=capacita;
		this.automatica = automatica;
		/*Carichiamo l'immagine*/
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/sprites/"+nome+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setTRANSFORM(int x, int y, double rotation, double muzzlerotation,
			int xMUZZLE, int YMUZZLE){
		this.xTRANSLATE=x;
		this.yTRANSLATE=y;
		this.rotation=rotation;
		this.muzzle_rotation= muzzlerotation;
		xMUZZLETRANSLATE=xMUZZLE;
		YMUZZLETRANSLATE=YMUZZLE;
	}
	public int shoot(){
		if(this.colpi>0){
			this.reloading=false;
			this.colpi--;
			System.out.println(""+this.colpi);
		}
		return this.colpi;
	}
	public void reload(){
		if(!reloading){
			this.start= System.currentTimeMillis();
			reloading =true;
		}
		if(reloading){
			if((end=System.currentTimeMillis())>(start+1500))
			{
				//ha finito di caricare
				this.colpi=capacita;
				reloading = false;
			}
		}
	}
	public BufferedImage getImage(){
		return this.image;
	}
}
