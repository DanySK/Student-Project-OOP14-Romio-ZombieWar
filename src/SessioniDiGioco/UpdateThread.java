package SessioniDiGioco;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class UpdateThread implements Runnable {
	protected boolean running = true;
	protected int waiting;
	protected final AtomicBoolean pauseFlag = new AtomicBoolean(false);
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