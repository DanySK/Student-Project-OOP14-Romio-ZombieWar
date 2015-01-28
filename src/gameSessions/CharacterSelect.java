package gameSessions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entities.Player;

public class CharacterSelect extends GameSession{
	
	/**
	 * 
	 * This session let the player select between 2 differ skin to associate to his buddy
	 * 
	 * @author Giovanni Romio
	 */
	
	private boolean playerSelect = true;
	private BufferedImage[] ritratti;
	
	public CharacterSelect(SessionController cds){
		this.cds = cds;
		giocatore = Player.getIstance();
		ritratti = new BufferedImage[2];
		try{
			ritratti[0] = ImageIO.read(getClass().getResourceAsStream("/backgrounds/avionSelect.png"));
			ritratti[1] = ImageIO.read(getClass().getResourceAsStream("/backgrounds/bartSelect.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void init(){
	}
	
	public void update(){
	}
	
	public void draw(Graphics2D g){
		g.clearRect(0, 0, 640, 480);
		g.setBackground(Color.BLACK);
		if(playerSelect)
			g.drawImage(ritratti[0],0,0,null);
		else g.drawImage(ritratti[1],0,0,null);
	}
	
	public void keyPressed(int k) {
		if(k== KeyEvent.VK_D){
			playerSelect=!playerSelect;
		}
		if(k== KeyEvent.VK_A){
			playerSelect=!playerSelect;
		}
		if(k== KeyEvent.VK_ENTER){
			if(playerSelect){
				giocatore.setSkin("/sprites/avion2.png");
			}else{
				giocatore.setSkin("/sprites/marine.png");
			}
			this.cds.aggiungiSessione(new LevelOne(cds));
			this.cds.aggiungiSessione(new GamePause(cds));
			this.cds.setState(SessionController.LEVEL1);
		}
	}

	
	public void keyReleased(int k) {
	}
	
	public void mouseClicked(int x,int y) {
	}
	
	public void mouseReleased() {			
	}
	
	public void setMouse(int x, int y) {		
	}
}
