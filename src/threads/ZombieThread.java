package threads;

import java.util.Collections;
import java.util.List;

import entities.Zombie;

public class ZombieThread extends UpdateThread{
	
	/**
	 * Very important thread wich call the update method for the zombies
	 * 
	 * @author Giovanni Romio
	 * 
	 */
	
	private List<Zombie> m;
	
	public ZombieThread(List<Zombie> l,int w) {
		this.m = Collections.synchronizedList(l);
		this.waiting = w;
	}
	/**
	 * Main loop of the ZombieThread
	 * If it is not paused call the update method for each zombie then it checkCollisions.
	 * 
	 */
	public void run(){
		/* Thread Loop */
		while (!Thread.currentThread().isInterrupted()) {
			/* Se la variabile pausa è true allora mettiamo in wait il nostro thread */
			if (pauseFlag.get()) {
				synchronized ( pauseFlag) {
					while (pauseFlag.get()) {
						try {
							/* Thread in wait */
							pauseFlag.wait();
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
							/* If thread is interrupted we stop the iteration */
							return;
						}
					}
				}
			}
			else{
				/* if pause == false call update on zombies */
				synchronized(m) {				
					for (int i = 0; i<m.size();i++ ){
						/* Update alive zombies */
						if (m.get(i).isAlive()){
							(m.get(i)).update();
							this.checkCollision (m.get(i));
						} 
						else {
							m.remove(i);
						}
					}
				}
				try {
					Thread.sleep(waiting);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					/* If thread is interrupted we stop the iteration */
					return;
				}
			}

		}
	}
	private void checkCollision(Zombie m){
		m.attack();
	}	

}
