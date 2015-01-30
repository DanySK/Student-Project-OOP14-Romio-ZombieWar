package entities;

public class EnrageZombie extends Zombie {
	/**
	 * 
	 * @param xSpawn origin position
	 * @param ySpawn origin position
	 */
	public EnrageZombie(int xSpawn, int ySpawn) {
		super(xSpawn, ySpawn);
	}
	
	public void init(){
		/* Load zombie image */
		this.sprite = setSprite("/sprites/enrageZombie.png");
		/*The png file contain 5 different sprites wich make the walk animation*/
		this.width = sprite.getWidth()/4;
		this.height = sprite.getHeight();
		/* Create the animation */
		this.setWalkAnimation(sprite, width, height);
		this.hp = 25;	
		this.damage = 1;
		this.speed = 1;
	}
	
	/**
	 * if zombie has low hp encrease his speed
	 */
	
	private void enrage(){
		this.speed = 2;
	}
	
	
	public void update(){
		/* If zombies got low hp it encrease his speed */
		if(hp < 10){
			this.enrage();
		}
		walk.update();
		this.calculatePosition();	
	}
	

}
