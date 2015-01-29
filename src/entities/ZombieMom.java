package entities;

public class ZombieMom extends Zombie{

	public ZombieMom(int xSpawn, int ySpawn) {
		super(xSpawn, ySpawn);
	}
	
	public void init(){
		/* Load zombie image */
		this.sprite = setSprite("/sprites/zombieMom.png");
		/*The png file contain 5 different sprites wich make the walk animation*/
		this.width = sprite.getWidth()/4;
		this.height = sprite.getHeight();
		/* Create the animation */
		this.setWalkAnimation(sprite, width, height);
		this.hp = 25;	
		this.damage = 1;
		this.speed = 1;
	}
	

}
