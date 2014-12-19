package Entita;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Base {
	private static Base base;
	Polygon p;
	/*Coordinate della base*/
	int [] xPoint = {200,220,270,470,555,555}; 
	int [] yPoint = {0,80,120,120,80,0};
	private Base(){
		p = new Polygon(xPoint,yPoint,6);
	}
	public static Base getIstance(){
		if(base == null){
			base = new Base();
		}
		return base;
	}
	public boolean intersect(Rectangle o){
		return p.intersects(o);
	}
	public void draw(Graphics2D g){
		g.draw(p);
	}
}
