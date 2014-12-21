package SessioniDiGioco;

import java.util.Collections;
import java.util.List;

import Entita.Giocatore;
import Entita.MammaZombie;

public class ZombieThread implements Runnable{
	List<MammaZombie> list;
	Giocatore g;
	int i;
	int c = 0;
	public ZombieThread(List<MammaZombie> list2) {
		this.list = Collections.synchronizedList(list2);
	}
	@Override
	public void run() {
		while(true){
			synchronized (list) {
				for(int i = 0; i<list.size();i++ ){
				/*Impongo gli update agli zombie*/
				list.get(i).update();
				}
			}
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
