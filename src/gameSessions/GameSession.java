package gameSessions;
import java.awt.Graphics2D;

import entities.Player;

public abstract class GameSession {
	
	protected SessionController cds;
	protected Player giocatore;
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void keyPressed(int k) throws InterruptedException;
	public abstract void keyReleased(int k);
	public abstract void mouseClicked(int x, int y);
	public abstract void mouseReleased();
	public abstract void setMouse(int x, int y);
}
