package SessioniDiGioco;

import java.util.Collections;
import java.util.List;

import Entita.Modello2d;

public abstract class UpdateThread implements Runnable {
	protected boolean running = true;
	protected int waiting;
	/*Lista contenenti gli elementi su cui fare l'update*/
	protected List<?> list;
	public UpdateThread(List<?> list2,int w){
		this.list = Collections.synchronizedList(list2);
		this.waiting = w;
	}
	@Override
	public void run() {
		while(running){
			synchronized (list) {
				for(int i = 0; i<list.size();i++ ){
					/*Impongo gli update agli zombie*/
					((Modello2d) list.get(i)).update();
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