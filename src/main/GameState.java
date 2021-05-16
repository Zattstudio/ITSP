package main;

import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import map.MapHandler;
import org.newdawn.slick.Color;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.transition.FadeInTransition;
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
MapHandler mHandler;
Grid grid;
SidePanel panel;
SoundManager sm = new SoundManager();

boolean finished = false;

Background bg = new Background("assets/gfx/scene/bg1.png", 4);

int zuege = 0;
int playerLives = 3;

int mapId = 1;                                                                     //1 damit zuerst Level 1 geladen wird

    @Override
    public int getID() {
        return GameState.ID;
    }

    public void resetMap(){
        sm.play(SoundManager.SOUNDS.RESET);
        zuege = mHandler.getMap().turns;
        mHandler.changeMap(mapId);   
    }
    
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mapId = 1;   
        finished = false;
        playerLives = 3;
        mHandler = new MapHandler();
        mHandler.changeMap(mapId);                                                 //Change Map
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
    	
    	
    	grid.draw(grphcs);
    	
    	
    	//draw last
    	panel.draw(grphcs);
    }
    
    void move(int dirX, int dirY) {
    	if(zuege == 0) {return;} // keine zuege mehr
	    char nextBlock = mHandler.checkRelative(dirX, dirY);
	    if (nextBlock  != '#' && nextBlock  != ' '&& nextBlock != '+' ){
			mHandler.setCurrentX(mHandler.getCurrentX()+dirX);
			mHandler.setCurrentY(mHandler.getCurrentY()+dirY);
                        
			sm.play(SoundManager.SOUNDS.WALK);
                        
			zuege -= 1;
			if(nextBlock=='x') {
				playerLives -= 1;
                                sm.play(SoundManager.SOUNDS.HIT);
			}
                        if(nextBlock=='e') {                                    //
                            sm.play(SoundManager.SOUNDS.FINISH);
                            if(mapId == mHandler.getMapCount()){
                                // Ende vom spiel
                                finished = true;
                            }else{
                                mapId++;                                               //Wenn next Block = e, nächstes Level
                                mHandler.changeMap(mapId);  
                                //Aktualisieren der map-voreinstellungen
                                zuege = mHandler.getMap().turns;
                            }
			}                                                       //
	    }
	    else {
	    	//Collision
	    }
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
	    int dirX = 0,dirY = 0;
	    Input inp = gc.getInput();
            
            panel.panelUpdate(gc, sbg, playerLives, zuege, mapId + " - "+ mHandler.getMap().name);
            
            // update side panel
            if (panel.restartBtn.mouseIsOnButton(new Vector2(inp.getMouseX(), inp.getMouseY())) & inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			resetMap();
            }
            
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
            
	
	    
            if (playerLives <= 0){
                // Wenn keine leben mehr reset komplett
                sm.play(SoundManager.SOUNDS.DEATH);
                this.init(gc, sbg);
                sbg.enterState(2); // maybe add game over screen

            }
            if (zuege <= 0){
            resetMap();
            
            }
            
            if (finished){
                this.init(gc, sbg);
                sbg.enterState(2); // spiel beendet
            }
    }
    

}
