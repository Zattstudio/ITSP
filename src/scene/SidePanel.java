package scene;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import main.GameObject;
import org.newdawn.slick.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import utility.Vector2;

public class SidePanel  extends GameObject{
	
	Image treeImg;
        
        
        Image state1Img;
        Image state2Img;
        int lives = 0;
        int turns = 0;
        String map = "";
        
        FileInputStream fontstream;
        
        Font panelFont;
        Font panelFontBig;
        TrueTypeFont panelTtf;
        TrueTypeFont panelTtfBig;
        
	public Button restartBtn = new Button("assets/gfx/menu/restart_button.png", new Vector2(1790, 890), 128, 128);

	
	public void panelUpdate(GameContainer gc, StateBasedGame sbg, int player_lives, int m_turns, String m_map) {
		lives = player_lives;
                turns = m_turns;
                map = m_map;
	}
        
        public SidePanel(){
            try { // Load font
                fontstream = new FileInputStream("assets/gfx/Pixeled.ttf");
                panelFont = Font.createFont(Font.TRUETYPE_FONT, fontstream);
                panelFont = panelFont.deriveFont(24F);
                panelFontBig = panelFont.deriveFont(40F);
                
                panelTtf = new TrueTypeFont(panelFont, true);
                panelTtfBig = new TrueTypeFont(panelFontBig, true);
            } catch (FontFormatException ex) {
                Logger.getLogger(SidePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SidePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            try {
                    // load image 
                    treeImg = new Image("assets/gfx/scene/ui_tree.png");
                    treeImg.setFilter(Image.FILTER_NEAREST);
                    
                    state1Img = new Image("assets/gfx/scene/player_state_1.png");
                    state2Img = new Image("assets/gfx/scene/player_state_2.png");
                    state1Img.setFilter(Image.FILTER_NEAREST);
                    state2Img.setFilter(Image.FILTER_NEAREST);
            } catch (SlickException e) {
                    // Simple catch block
                    e.printStackTrace();
            }
        }
        @Override
        public void draw(Graphics gfx) {
		//gfx.fillRect(1300, 0, 620, 1080);
                
                treeImg.draw(1920-treeImg.getWidth()*5, 0, 5);
                panelTtfBig.drawString(1770, 150, Integer.toString(turns)); // draw turns left
                panelTtf.drawString(1730, 130, "TURNS");
                panelTtf.drawString(100, 250, map);
                
                drawLife(lives, gfx );
		restartBtn.draw(gfx);
                
		
	}
        
        private void drawLife(int lives, Graphics gfx){
            if (lives > 2) state1Img.draw(1750, 450, 4);
            else state2Img.draw(1750, 450, 4);
            gfx.fillRect(1700, 550, (int)66*lives, 20);
        }

}
