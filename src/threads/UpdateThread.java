package threads;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class UpdateThread implements Runnable {
	
	/**
	 * Abstract class define how thread in game will be structured
	 * 
	 * @author Giovanni Romio
	 * 
	 */
	
	protected boolean running = true;
	protected int waiting;
	protected final AtomicBoolean pauseFlag = new AtomicBoolean(false);
	
	/**
	 * Wait and notify on pauseFlag
	 * 
	 * @param pausa set the pause Flag
	 */
	public void setPausa(boolean pausa){	
		if(pausa){
			pauseFlag.set(true);
		}
		else{
			pauseFlag.set(false);
			synchronized (pauseFlag) {
				pauseFlag.notify();
			}
		}
	}
}