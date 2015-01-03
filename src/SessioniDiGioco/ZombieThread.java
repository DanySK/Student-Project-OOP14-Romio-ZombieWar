package SessioniDiGioco;

import java.util.Collections;
import java.util.List;

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
					/*Impongo gli update agli zombie*/
					(m.get(i)).update();
				}
			}

			try {
				Thread.sleep(waiting);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
