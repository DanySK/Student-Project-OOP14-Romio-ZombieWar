package SessioniDiGioco;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import Entita.Giocatore;

public class SelezionePersonaggio extends SessioneDiGioco{
	private boolean playerSelect = true;
	private BufferedImage[] ritratti;
	
	public SelezionePersonaggio(ControllerDiSessione cds){
		this.cds = cds;
		giocatore = Giocatore.getIstance();
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
			this.cds.aggiungiSessione(new LivelloUno(cds));
			this.cds.aggiungiSessione(new GamePause(cds));
			this.cds.setState(ControllerDiSessione.LIVELLO1);
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
