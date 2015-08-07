package ru.mydroid;

public class Point {
	public int x, y;
	
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			return (this.x == p.x) && (this.y == p.y);
		}
		return false;
	}
}
