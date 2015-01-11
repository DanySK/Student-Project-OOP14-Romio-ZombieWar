package SessioniDiGioco;

import java.util.Collections;
import java.util.List;

import Entita.Base;
import Entita.Giocatore;
import Entita.MammaZombie;

public class ZombieThread extends UpdateThread{
	private List<MammaZombie> m;
	public ZombieThread(List<MammaZombie> l,int w) {
		this.m = Collections.synchronizedList(l);
		this.waiting = w;
	}
	public void run(){
		while(running){
			synchronized (m) {
				for(int i = 0; i<m.size();i++ ){
					/*Impongo gli update agli zombie vivi*/
					if(m.get(i).isAlive()){
						(m.get(i)).update();
						this.checkCollision(m.get(i));
					}else{
						m.remove(i);
					}
				}
			}

			try {
				Thread.sleep(waiting);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void checkCollision(MammaZombie m){
		m.attack();
	}
}
