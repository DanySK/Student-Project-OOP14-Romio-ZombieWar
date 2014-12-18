package Entita;

import java.awt.Polygon;
import java.awt.Rectangle;

public class Base {
	private static Base base;
	Polygon p;
	/*Coordinate della base*/
	int [] xPoint = {200,210,260,460,555,555}; 
	int [] yPoint = {0,90,120,120,90,0};
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
}
