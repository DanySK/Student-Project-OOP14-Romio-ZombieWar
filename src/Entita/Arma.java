package Entita;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Arma {
	private String nome;
	private int danno;
	private int colpi;
	private int capacit�;
	private BufferedImage image;
	private boolean reloading;
	private boolean automatica;
	private long start,end;
	public Arma(String nome,int danno, int capacit�,boolean automatica){
		this.nome=nome;
		this.danno=danno;
		this.colpi=this.capacit�=capacit�;
		/*Carichiamo l'immagine*/
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/sprites/"+nome+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.automatica = automatica;
	}
	public int shoot(){
		return 0;
	}
	public void reload(){
		
	}
}
