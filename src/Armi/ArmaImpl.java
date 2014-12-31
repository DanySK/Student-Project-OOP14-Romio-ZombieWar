package Armi;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import Entita.Giocatore;
import Entita.Proiettile;

public class ArmaImpl implements Arma {
	
	protected int danno;
	protected int colpi;
	protected int caricatore;
	protected BufferedImage sprite;
	/*Timer del caricatore*/
	protected boolean reloading;
	protected int realoadTime;
	protected long start;
	protected long end;
	/*Coordinate per il draw*/
	protected int x;
	protected int y;
	/*Lista contenente tutti i colpi attualmente in esecuzione*/
	protected List<Proiettile> list;

	@Override
	public int shoot(Giocatore g,double xMouse,double yMouse, List<Proiettile> l) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void reload() {
		if(!reloading){
			this.start= System.currentTimeMillis();
			reloading =true;
		}
		if(reloading){
			if((end=System.currentTimeMillis())>(start+1500))
			{
				//ha finito di caricare
				this.colpi=caricatore;
				reloading = false;
			}
		}

	}

	@Override
	public void setImage(String path) {
		try {
			this.sprite = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) { 
			e.printStackTrace();
		}

	}

	@Override
	public BufferedImage getImage() {
		return sprite;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}

}
