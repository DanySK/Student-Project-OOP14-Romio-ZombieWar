package entities;

import java.util.List;

import weapons.WeaponImpl;

public interface Player {
	
	public void setLeft(boolean value);
	public void setRight(boolean value);
	public void setUp(boolean value);
	public void setDown(boolean value);
	public void setWeapons(WeaponImpl[]arsenale);
	public boolean shoot(double xMouse,double yMouse,List<Bullet>l);
	public void setGun(int i);
	public WeaponImpl getWeapon();
	public void setSkin(String path);
	public void hit(double d);
	public void update(int x, int y);
	public boolean isRealoading();
	
}
