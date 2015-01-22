package SessioniDiGioco;

import java.util.Collections;
import java.util.List;
import Entita.MammaZombie;
import Entita.Proiettile;

public class ProiettileThread extends UpdateThread{
	private List<Proiettile> p;
	private List<MammaZombie> m;
	public ProiettileThread(List<Proiettile> list,List<MammaZombie> list2){
		this.p = Collections.synchronizedList(list);
		this.m = Collections.synchronizedList(list2);
	}
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			/*Se la variabile pausa è true allora mettiamo in wait il nostro thread*/
			if (pauseFlag.get()) {
				synchronized (pauseFlag) {
					while (pauseFlag.get()) {
						try {
							/*Thread in wait*/
							pauseFlag.wait();
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
							/*Se il thread è interrotto terminiamo il ciclo*/
							return;
						}
					}
				}
			}
			else{
				/*Update per i proiettili*/
				try{
					synchronized (p) {
						for(int i = 0; i<p.size(); i++){
							if(p.get(i).getXScreen()<0||p.get(i).getXScreen()>640||p.get(i).getYScreen()<0||p.get(i).getYScreen()>480){
								p.remove(i);							
							}
							else{
								/*Dopo ciascun update controlliamo se il proiettile ha colpito un zombie*/
								p.get(i).update();
								this.checkCollision(p.get(i));
							}
						}
					}
					/*Tempo di attesa tra un update e il successivo*/
					Thread.sleep(20);
				}catch(Exception e){
					return;				
				}
			}
		}

	}
	public void checkCollision(Proiettile pr){		
		for(MammaZombie alive:m){
			synchronized (m) {
				synchronized (p) {
					if(alive.getRectangle().contains(pr.getPosition()))			
					{
						alive.colpito(pr.getDanno());
						p.remove(pr);
						return;
					}
				}
			}
		}
	}
}
