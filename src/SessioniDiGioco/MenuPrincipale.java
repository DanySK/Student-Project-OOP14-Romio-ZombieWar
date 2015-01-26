package SessioniDiGioco;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuPrincipale extends SessioneDiGioco{
	private BufferedImage background;
	private String[] scelta={
			"Start",
			"Options",
			"Credits",
			"Quit"
	};
	private int currentChoice = 0;
	private Font font;
	private Color defaultColor;
	private Color choiceColor;
	private GraphicsEnvironment ge ;

	public MenuPrincipale(ControllerDiSessione cds){
		this.cds=cds;
		/*Carico l'immagine dello sfondo del menu*/
		try {
			background=ImageIO.read(getClass().getResourceAsStream("/backgrounds/menu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*Seleziono il font della nostra finestra e i colori per stampare le stringhe*/
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/TrueLies.ttf")).deriveFont(40f);
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/TrueLies.ttf")).deriveFont(40f));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		defaultColor=(Color.BLACK);
		choiceColor=(Color.RED);	
	}
	
	public void init(){
		currentChoice = 0;
	}
	
	public void update(){

	}
	
	public void draw(Graphics2D g){
		/*Disegno l'immagine di sfondo*/
		g.drawImage(background,0,0,null);
		g.setFont(font);
		/*Il colore della scelta � rosso, quello di default � nero*/
		for(int i = 0; i<scelta.length; i++){
			if(currentChoice==i){
				g.setColor(choiceColor);				
			}
			else{
				g.setColor(defaultColor);
			}
			g.drawString(scelta[i], 280, 200+i*50);
		}

	}
	
	private void select(){
		switch(currentChoice){
		case 0:
			this.cds.aggiungiSessione(new SelezionePersonaggio(cds));
			this.cds.setState(ControllerDiSessione.SELEZIONEPERSONAGGIO);			
			break;
		case 1: this.cds.setState(ControllerDiSessione.OPZIONI); break;
		case 2: this.cds.setState(ControllerDiSessione.CREDITS); break;			
		case 3: System.exit(0);break;
		}
	}
	
	public void keyPressed(int k){
		if(k== KeyEvent.VK_ENTER){
			select();
		}
		if(k== KeyEvent.VK_W){
			currentChoice --;
			if( currentChoice==-1){
				currentChoice= scelta.length-1;
			}
		}
		if(k== KeyEvent.VK_S){
			currentChoice ++;
			if (currentChoice==scelta.length){
				currentChoice=0;
			}
		}
	}	
	
	public void keyReleased(int k){
	}
	
	public void mouseClicked(int x,int y) {	
	}
	
	public void mouseReleased() {
	}
	
	public void setMouse(int x, int y) {
				
	}
}
