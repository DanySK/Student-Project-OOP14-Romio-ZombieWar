package SessioniDiGioco;

import java.util.List;

import Entita.Modello2d;
import Entita.Proiettile;

public class ProiettileThread extends UpdateThread {
	public ProiettileThread(List<Proiettile> list2, int w) {
		super(list2, w);		
	}
	@Override
	public void run(){
		while(running){
			try {
				synchronized (list) {
					for(int i=0;i<list.size();i++){
						if(((Modello2d) list.get(i)).getXScreen()>640||((Modello2d) list.get(i)).getXScreen()<0||((Modello2d) list.get(i)).getYScreen()>480||((Modello2d) list.get(i)).getYScreen()<0)
						{
							list.remove(i);
						}
						else{
							((Proiettile) list.get(i)).update();			
						}
					}
				}try {
					Thread.sleep(waiting);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
