package me.therealjeremy.mp;

public class Mine {

	private int x;
	private int y;
	private int z;
	private int dx;
	private int dy;
	private int dz;
	private String world;

	public Mine(int x1, int y1, int z1, int x2, int y2, int z2, String world) {
		this.x = x1 < x2 ? x1 : x2;
		this.y = y1 < y2 ? y1 : y2;
		this.z = z1 < z2 ? z1 : z2;
		this.dx = Math.abs(x1 - x2);
		this.dy = Math.abs(y1 - y2);
		this.dz = Math.abs(z1 - z2);
		this.world = world;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}
	
	public int getDz() {
		return dz;
	}
	
	public String getWorld() {
		return world;
	}
	
}
