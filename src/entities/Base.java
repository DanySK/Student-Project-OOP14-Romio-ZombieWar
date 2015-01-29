package entities;

import java.awt.Polygon;
import java.awt.Rectangle;

public interface Base {
	public boolean intersect(Rectangle o);
	public int getHp();
	public Polygon getCollisionPolygon();
	public void hit(double d);
	public boolean isAlive();
}
