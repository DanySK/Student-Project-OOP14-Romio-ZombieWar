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
		while(running){
			/*Update per i proiettili*/
			try{
				synchronized (p) {
					for(int i = 0; i<p.size(); i++){
						if(p.get(i).getXScreen()<0||p.get(i).getXScreen()>640||p.get(i).getYScreen()<0||p.get(i).getYScreen()>480){
							p.remove(i);							
						}
						else{
							p.get(i).update();
							this.checkCollision(p.get(i));
						}
					}
				}
				Thread.sleep(30);
			}catch(Exception e){
				e.printStackTrace();				
			}
		}

	}
	public void checkCollision(Proiettile pr){		
		for(MammaZombie alive:m){
			synchronized (m) {
				synchronized (p) {
					if(alive.getRectangle().contains(pr.getPosition()))			
					{
						p.remove(pr);
						return;
					}
				}
			}
		}
	}
}

