package scene;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import main.GameObject;
import utility.Vector2;

public class SidePanel  extends GameObject{
	
	
	Button restartBtn = new Button("assets/gfx/menu/restart_button.png", new Vector2(1700, 100), 64, 64);
	@Override
	public void draw(Graphics gfx) {
		gfx.fillRect(1300, 0, 620, 1080);
		restartBtn.draw(gfx);
		
	}
	
	public void panelUpdate(GameContainer gc, StateBasedGame sbg) {
		
	}

}
