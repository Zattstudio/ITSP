package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import scene.Background;
import scene.Button;
import utility.Vector2;

public class MenuState extends BasicGameState {
	
	public static final int ID = 1;
	private Background bg;
	private Image title_logoImage;
	
	private Button startBtn;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		bg = new Background("assets/gfx/scene/pixeltester.png", 5);
		
		try {
			// load image 
			title_logoImage = new Image("assets/gfx/menu/title_menu.png");
			title_logoImage.setFilter(Image.FILTER_NEAREST);
			
		} catch (SlickException e) {
			// Simple catch block
			e.printStackTrace();
		}
		
		
		
		startBtn = new Button("assets/gfx/menu/start_button.png", new Vector2(gc.getWidth()/2, gc.getHeight()/2), 300, 120);
		startBtn.drawDebug = true; // debug for checing button clickable area

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gph) throws SlickException {
		
		bg.draw(gph);
		title_logoImage.draw((gc.getWidth()/2)-(title_logoImage.getWidth()*1.33f), (gc.getHeight()/2)-400, 3);
		startBtn.draw(gph);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
		
		Input inp = gc.getInput();


		
		if (startBtn.mouseIsOnButton(new Vector2(inp.getMouseX(), inp.getMouseY())) & inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(0);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
