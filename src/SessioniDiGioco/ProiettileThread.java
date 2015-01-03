package SessioniDiGioco;

import java.util.Collections;
import java.util.List;

import Entita.Proiettile;

public class ProiettileThread extends UpdateThread{
	private List<Proiettile> p;
	public ProiettileThread(List<Proiettile> list){
		this.p = Collections.synchronizedList(list);
	}
	public void run() {
		while(running){
			/*Update per i proiettili*/
			try{
				synchronized (p) {
					for(int i = 0; i<p.size(); i++){
						if(p.get(i).getXScreen()<0||p.get(i).getXScreen()>640||p.get(i).getYScreen()<0||p.get(i).getYScreen()>480){
							p.remove(i);							
						}
						p.get(i).update();							
					}
				}
				Thread.sleep(30);
			}catch(Exception e){
				e.printStackTrace();				
			}
		}
		
	}


}
