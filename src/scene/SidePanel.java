package scene;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import main.GameObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import utility.Vector2;

public class SidePanel  extends GameObject{
	
	Image treeImg;
        int lives = 0;
        
        
        //Font panelFont = new Font('', Font.BOLD, 32);
        //TrueTypeFont panelTtf = new TrueTypeFont(font, true);
        
	public Button restartBtn = new Button("assets/gfx/menu/restart_button.png", new Vector2(1700, 500), 64, 64);

	
	public void panelUpdate(GameContainer gc, StateBasedGame sbg, int player_lives) {
		lives = player_lives;
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
                
                drawLifeBar(lives, gfx );
		restartBtn.draw(gfx);
		
	}
        
        private void drawLifeBar(int lives, Graphics gfx){
            gfx.setColor(Color.gray);
            gfx.fillRect(1700, 550, (int)66*lives, 20);
            gfx.setColor(Color.white);
        }

}
