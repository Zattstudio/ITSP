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
import org.newdawn.slick.Music;
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

boolean locked = true;
boolean lockedM = true;
boolean finished = false;

Background bg;

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
        locked = true;
        lockedM = true;
        grid.lock();
        grid.lockM();
        grid.keyVisible = true;
        grid.keyMVisible = true;
    }
    
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mapId = 1;
        locked = true;
        lockedM = true;
        finished = false;
        playerLives = 3;
        mHandler = new MapHandler();
        mHandler.changeMap(mapId);                                                 //Change Map
        //mHandler.prettyPrint();
        Vector2 mapSize = mHandler.getMaxExtents();
        grid = new Grid((int)mapSize.x, (int)mapSize.y, 100, 0, mHandler);
        grid.setPos(200, 400);
        grid.keyVisible = true;
        grid.keyMVisible = true;
        
        panel = new SidePanel();
        bg = new Background("assets/gfx/scene/"+mHandler.getMap().bg+".png", 4);
        zuege = mHandler.getMap().turns;
        
        sm.toggleMusic(true);
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
                        if(nextBlock=='c' || nextBlock=='h' || nextBlock=='v') {
				resetMap();
                                sm.play(SoundManager.SOUNDS.HIT);
			}
                        if(nextBlock=='k') {
				locked = false;
                                grid.unlock();
                                grid.keyVisible = false;
                                if(!grid.keyVisible) sm.play(SoundManager.SOUNDS.FINISH);
			}
                        if(nextBlock=='K') {
				lockedM = false;
                                grid.unlockM();
                                grid.keyMVisible = false;
                                if(!grid.keyMVisible) sm.play(SoundManager.SOUNDS.FINISH);
			}
                        if((nextBlock=='l') && (locked==false)) {
                                sm.play(SoundManager.SOUNDS.FINISH);
                            if(mapId == mHandler.getMapCount()){
                                // Ende vom spiel
                                finished = true;
                            }else{
                                mapId++;                                               //Wenn next Block = e, nächstes Level
                                mHandler.changeMap(mapId);
                                locked = true;
                                grid.lock();
                                grid.keyVisible = true;
                                //Aktualisieren der map-voreinstellungen
                                zuege = mHandler.getMap().turns;
                            }
			}
                        if((nextBlock=='L') && (lockedM==false)) {
                                sm.play(SoundManager.SOUNDS.FINISH);
                            if(mapId == mHandler.getMapCount()){
                                // Ende vom spiel
                                finished = true;
                            }else{
                                mapId++;                                               //Wenn next Block = e, nächstes Level
                                mHandler.changeMap(mapId);
                                lockedM = true;
                                grid.lockM();
                                grid.keyMVisible = true;
                                //Aktualisieren der map-voreinstellungen
                                zuege = mHandler.getMap().turns;
                                bg = new Background("assets/gfx/scene/"+mHandler.getMap().bg+".png", 5);
                            }
			}
                        if(nextBlock=='e') {                                    //
                            sm.play(SoundManager.SOUNDS.FINISH);
                            if(mapId == mHandler.getMapCount()){
                                // Ende vom spiel
                                finished = true;
                            }else{
                                mapId++;                                               //Wenn next Block = e, nächstes Level
                                mHandler.changeMap(mapId); 
                                locked = true;
                                grid.lock();
                                grid.keyVisible = true;
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
                 sm.toggleMusic(false);
                this.init(gc, sbg);
               
                sbg.enterState(2); // maybe add game over screen

            }
            if (zuege <= 0){
            resetMap();
            
            }
            
            if (finished){
                sm.toggleMusic(false);
                this.init(gc, sbg);
                
                sbg.enterState(2); // spiel beendet
            }
    }
    

}
