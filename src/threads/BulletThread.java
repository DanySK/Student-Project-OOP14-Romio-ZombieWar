package threads;

import java.util.Collections;
import java.util.List;

import entities.Blood;
import entities.Bullet;
import entities.Zombie;

public class BulletThread extends UpdateThread{
	
	private List<Bullet> p;
	private List<Zombie> m;
	private List<Blood> s;
	public BulletThread(List<Bullet> list,List<Zombie> list2, List<Blood> list3){
		this.p = Collections.synchronizedList(list);
		this.m = Collections.synchronizedList(list2);
		this.s = Collections.synchronizedList(list3);
	}
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			/* If pausa == true set thread wait */
			if (pauseFlag.get()) {
				synchronized (pauseFlag) {
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
				/* Bullets update */
				try{
					synchronized (p) {
						for(int i = 0; i<p.size(); i++){
							if(p.get(i).getXScreen()<0||p.get(i).getXScreen()>640||p.get(i).getYScreen()<0||p.get(i).getYScreen()>480){
								p.remove(i);							
							}
							else{
								/* After each update checks if bullets hit a zombie */ 
								p.get(i).update();
								this.checkCollision(p.get(i));
							}
						}
					}
					/* Wait time between two updates */
					Thread.sleep(20);
				}catch(Exception e){
					/* If thread is interrupted we stop the iteration */
					return;			
				}
			}
		}

	}
	/**
	 * 
	 * @param pr is the bullet we check collision for
	 */
	public void checkCollision(Bullet pr){	
		synchronized (p) {
			synchronized (m) {
				for(int i = 0; i <m.size(); i++){
					if(m.get(i).getRectangle().contains(pr.getPosition()))			
					{
						m.get(i).hit(pr.getDanno());
						p.remove(pr);
						s.add(new Blood(m.get(i).getXMap(), m.get(i).getYMap(),m.get(i).getXScreen(),m.get(i).getYScreen()));
						return;
					}
				}
			}
		}
	}

}


