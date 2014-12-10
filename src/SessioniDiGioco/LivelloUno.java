package SessioniDiGioco;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entita.Player;

public class LivelloUno extends SessioneDiGioco{
	/*Un giocatore*/
	Player player;
	/*Una mappa*/
	BufferedImage map;
	public LivelloUno(){
		this.init();
	}
	@Override
	public void init(){
		/*Inizializziamo il giocatore*/
		player = new Player();
		/*Carichiamo la mappa*/
		try {
			map = ImageIO.read(getClass().getResourceAsStream("/backgrounds/map.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(){
		/*Imponiamo l'update al giocatore*/
		player.update();
	}
	@Override
	public void draw(Graphics2D g)
	{
		/*Disegniamo la mappa*/
		g.drawImage(map,0,0,null);
		/*Disegniamo il giocatore*/
		player.draw(g);
	}
	@Override
	public void keyPressed(int k){
		/*Imponiamo al personaggio uno spostamento*/
	}
	@Override
	public void keyReleased(int k){
		/*Imponiamo al personaggio di stare fermo*/
	}
	@Override
	public void mouseClicked(){
		
	}
}
