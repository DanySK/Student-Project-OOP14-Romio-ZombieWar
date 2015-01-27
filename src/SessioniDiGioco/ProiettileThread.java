package SessioniDiGioco;

import java.util.Collections;
import java.util.List;

import Entita.MammaZombie;
import Entita.Proiettile;
import Entita.Sangue;

public class ProiettileThread extends UpdateThread{
	
	private List<Proiettile> p;
	private List<MammaZombie> m;
	private List<Sangue> s;
	public ProiettileThread(List<Proiettile> list,List<MammaZombie> list2, List<Sangue> list3){
		this.p = Collections.synchronizedList(list);
		this.m = Collections.synchronizedList(list2);
		this.s = Collections.synchronizedList(list3);
	}
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			/* Se la variabile pausa è true allora mettiamo in wait il nostro thread */
			if (pauseFlag.get()) {
				synchronized (pauseFlag) {
					while (pauseFlag.get()) {
						try {
							/* Thread in wait */
							pauseFlag.wait();
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
							/* Se il thread è interrotto terminiamo il ciclo */
							return;
						}
					}
				}
			}
			else{
				/* Update per i proiettili */
				try{
					synchronized (p) {
						for(int i = 0; i<p.size(); i++){
							if(p.get(i).getXScreen()<0||p.get(i).getXScreen()>640||p.get(i).getYScreen()<0||p.get(i).getYScreen()>480){
								p.remove(i);							
							}
							else{
								/* Dopo ciascun update controlliamo se il proiettile 
								 * ha colpito un zombie
								 */
								p.get(i).update();
								this.checkCollision(p.get(i));
							}
						}
					}
					/* Tempo di attesa tra un update e il successivo */
					Thread.sleep(20);
				}catch(Exception e){
					/* Se il thread è interrotto terminiamo il ciclo */
					return;			
				}
			}
		}

	}
	/**
	 * 
	 * @param pr is the bullet we check collision for
	 */
	public void checkCollision(Proiettile pr){	
		synchronized (p) {
			synchronized (m) {
				for(int i = 0; i <m.size(); i++){
					if(m.get(i).getRectangle().contains(pr.getPosition()))			
					{
						m.get(i).colpito(pr.getDanno());
						p.remove(pr);
						s.add(new Sangue(m.get(i).getXMap(), m.get(i).getYMap(),m.get(i).getXScreen(),m.get(i).getYScreen()));
						return;
					}
				}
			}
		}
	}

}


