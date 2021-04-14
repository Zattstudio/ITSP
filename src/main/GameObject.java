package main;
import org.newdawn.slick.Graphics;

public abstract class GameObject {
	// On screen positions x,y of the object 
	protected int posX;
	protected int posY;
	// render Index for scene-tree rendering
	protected int zIndex;
	// object scale multiplier
	protected float scale = 1f;
	// sprite or animation flipped
	protected boolean flipped = false;
	// is object hidden ?
	protected boolean hidden;
	// object name, maybe unnecessary
	protected String name;
	public enum Direction {LEFT, RIGHT, UP, DOWN};
	
	
	public abstract void draw(Graphics gfx);
	
	public void setPos(int newX, int newY) {
		posX = newX;
		posY = newY;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {;
		return posY;
	}
}
