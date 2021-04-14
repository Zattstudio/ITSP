package scene;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.GameObject;
import utility.Vector2;

public class Button extends GameObject {
	Image buttonImg;
	Vector2 buttonPos;
	int buttonWidth;
	int buttonHeight;
	public boolean drawDebug = false; // darws a red border around button to check the clickable area
	
	public Button(String pathToImg, Vector2 buttonPos, int buttonWidth, int buttonHeight) {
		super();
		this.buttonPos = buttonPos;
		this.buttonWidth = buttonWidth;
		this.buttonHeight = buttonHeight;
		if (!pathToImg.isEmpty()) { // check for empty path passed
			try {
				// load image 
				buttonImg = new Image(pathToImg);
				buttonImg.setFilter(Image.FILTER_NEAREST);
				
			} catch (SlickException e) {
				// Simple catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void draw(Graphics gfx) {
		if (drawDebug) {
			gfx.setColor(Color.red);
			gfx.drawRect(buttonPos.x -(buttonWidth/2), buttonPos.y-(buttonHeight/2), buttonWidth, buttonHeight);
			gfx.setColor(Color.white);
		}
		buttonImg.draw(buttonPos.x -(buttonWidth/2), buttonPos.y-(buttonHeight/2), buttonWidth, buttonHeight);
		
	}
	
	public boolean mouseIsOnButton(Vector2 pos) {
		if   (pos.x > (buttonPos.x -(buttonWidth/2)) & pos.x < ((buttonPos.x -(buttonWidth/2)) + buttonWidth)
			& pos.y >(buttonPos.y -(buttonHeight/2)) & pos.y < ((buttonPos.y -(buttonHeight/2)) + buttonHeight)) { // is param pos in rect
			return true;
		}
		return false;
	}
	
	
}
