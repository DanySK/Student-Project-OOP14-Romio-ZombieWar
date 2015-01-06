package SessioniDiGioco;

public abstract class UpdateThread implements Runnable {
	protected boolean running = true;
	protected int waiting;
}