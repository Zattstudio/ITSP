package main;

import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import map.MapHandler;
import scene.Background;
import scene.SidePanel;
import utility.Vector2;

/**
 *
 * @author Luis
 */
public class GameState extends BasicGameState
{
public static final int ID = 0;
String drawmsg = "";
MapHandler mHandler;
Grid grid;
SidePanel panel;

Background bg = new Background("assets/gfx/scene/bg1.png", 4);

int zuege = 0;
int id = 1;                                                                     //1 damit zuerst Level 1 geladen wird

    @Override
    public int getID() {
        return GameState.ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mHandler = new MapHandler();
        mHandler.changeMap(id);                                                 //Change Map
        //mHandler.prettyPrint();
        Vector2 mapSize = mHandler.getMaxExtents();
        grid = new Grid((int)mapSize.x, (int)mapSize.y, 100, 0, mHandler);
        grid.setPos(200, 400);
        
        panel = new SidePanel();
        
        zuege = mHandler.getMap().turns;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

    	//
    	bg.draw(grphcs);
    	
    	
    	grphcs.drawString("Zuege: " + zuege, 10, 40);
    	grid.draw(grphcs);
    	
    	
    	//draw last
    	panel.draw(grphcs);
    }
    
    void move(int dirX, int dirY) {
    	drawmsg = "";
    	if(zuege == 0) {
    		return;
		}
	    char nextBlock = mHandler.checkRelative(dirX, dirY);
	    if (nextBlock  != '#' && nextBlock  != ' ' ){
			mHandler.setCurrentX(mHandler.getCurrentX()+dirX);
			mHandler.setCurrentY(mHandler.getCurrentY()+dirY);
			
			zuege -= 1;
			drawmsg = "Züge: " + zuege + "\n";
			if(nextBlock=='x') {
				drawmsg += "Du bist in eine Pf�tze gelaufen";
			}
                        if(nextBlock=='e') {                                    //
                            id++;                                               //Wenn next Block = e, nächstes Level
                            mHandler.changeMap(id);                             //
			}                                                       //
	    }
	    else {
	    	drawmsg += "Uatsch aut for se woll!";
	    }
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
	    int dirX = 0,dirY = 0;
	    Input inp = gc.getInput();
	    if (inp.isKeyPressed(Input.KEY_LEFT)) {
	    	dirX = -1;
	    	move(dirX, dirY);
                grid.playerLeft = true;
                grid.front = false;
                grid.back = false;
	    }
	    else if (inp.isKeyPressed(Input.KEY_RIGHT)) {
			dirX = 1;
			move(dirX, dirY);
                        grid.playerLeft = false;
                        grid.front = false;
                        grid.back = false;
		}
	    else if (inp.isKeyPressed(Input.KEY_UP)) {
			dirY = -1;
			move(dirX, dirY);
                        grid.back = true;
                        grid.front = false;
		}
	    else if (inp.isKeyPressed(Input.KEY_DOWN)) {
			dirY = 1;
			move(dirX, dirY);
                        grid.front = true;
                        grid.back = false;
		}
            else if (panel.restartBtn.mouseIsOnButton(new Vector2(inp.getMouseX(), inp.getMouseY())) & inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(1);
		}
	
	    
	    
    }
}
