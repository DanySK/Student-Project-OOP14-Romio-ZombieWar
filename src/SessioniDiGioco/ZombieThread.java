package SessioniDiGioco;

import Entita.Giocatore;
import Entita.MammaZombie;

public class ZombieThread implements Runnable{
	MammaZombie zombie;
	Giocatore g;
	int c = 0;
	public ZombieThread(MammaZombie z) {
		this.zombie = z;
	}
	@Override
	public void run() {
		while(true){
			zombie.update();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
