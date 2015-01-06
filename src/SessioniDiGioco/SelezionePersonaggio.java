package SessioniDiGioco;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import Entita.Giocatore;

public class SelezionePersonaggio extends SessioneDiGioco{
	boolean playerSelect = true;
	BufferedImage[] ritratti;
	Giocatore g;
	public SelezionePersonaggio(ControllerDiSessione cds){
		this.cds = cds;
		g = Giocatore.getIstance();
		ritratti = new BufferedImage[2];
		try{
			ritratti[0] = ImageIO.read(getClass().getResourceAsStream("/backgrounds/avionSelect.png"));
			ritratti[1] = ImageIO.read(getClass().getResourceAsStream("/backgrounds/bartSelect.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void init(){

	}
	@Override
	public void update(){

	}
	@Override
	public void draw(Graphics2D g){
		g.clearRect(0, 0, 640, 480);
		g.setBackground(Color.BLACK);
		if(playerSelect)
			g.drawImage(ritratti[0],0,0,null);
		else g.drawImage(ritratti[1],0,0,null);
	}
	@Override
	public void keyPressed(int k) {
		if(k== KeyEvent.VK_D){
			playerSelect=!playerSelect;
		}
		if(k== KeyEvent.VK_A){
			playerSelect=!playerSelect;
		}
		if(k== KeyEvent.VK_ENTER){
			if(playerSelect){
				g.setSkin("/sprites/avion2.png");
			}else{
				g.setSkin("/sprites/marine.png");
			}
			this.cds.setState(ControllerDiSessione.LIVELLO1);
		}
	}

	@Override
	public void keyReleased(int k) {

	}
	@Override
	public void mouseClicked() {

	}
	@Override
	public void mouseReleased() {
		// TODO Auto-generated method stub
		
	}
}
