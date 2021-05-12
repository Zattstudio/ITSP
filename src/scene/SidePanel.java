package scene;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import main.GameObject;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utility.Vector2;

public class SidePanel  extends GameObject{
	
	Image treeImg;
	public Button restartBtn = new Button("assets/gfx/menu/restart_button.png", new Vector2(1700, 500), 64, 64);

	
	public void panelUpdate(GameContainer gc, StateBasedGame sbg) {
		
	}
        
        public SidePanel(){
            try {
                    // load image 
                    treeImg = new Image("assets/gfx/scene/ui_tree.png");
                    treeImg.setFilter(Image.FILTER_NEAREST);

            } catch (SlickException e) {
                    // Simple catch block
                    e.printStackTrace();
            }
        }
        @Override
        public void draw(Graphics gfx) {
		//gfx.fillRect(1300, 0, 620, 1080);
                
                treeImg.draw(1920-treeImg.getWidth()*5, 0, 5);
                
		restartBtn.draw(gfx);
		
	}

}
