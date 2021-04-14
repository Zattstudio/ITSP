package scene;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.GameObject;

public class Background extends GameObject {
	
	// Image resource for slick
	Image img;

	public  Background(String pathToImg, int size) {
		this.zIndex = -99;
		this.scale = size;
		setPos(0, 0); // always put it on 0,0 for now 
		if (!pathToImg.isEmpty()) { // check for empty path passed
			try {
				// load image 
				img = new Image(pathToImg);
				img.setFilter(Image.FILTER_NEAREST);
				
			} catch (SlickException e) {
				// Simple catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void draw(Graphics gfx) {
		img.draw(posX, posY, scale);
		
	}

}
