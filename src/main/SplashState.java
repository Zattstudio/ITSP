/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;

/**
 *
 * @author Luis
 */
public class SplashState extends BasicGameState{
    
    public static final int ID = 2;
    
    Image splashImg;
    final int MAX_FRAME_TIME = 2000; // Time in ms after which the splash fades out
    int running = 0;

// Returning 'ID' from class 'MainMenu'
    @Override
    public int getID() {
            return SplashState.ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        splashImg = new Image("assets/gfx/scene/splash.png", false);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        splashImg.draw(gc.getWidth()/2-splashImg.getWidth()/2, gc.getHeight()/2-splashImg.getHeight()/2); //Always draw image splash in center
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if(running >= MAX_FRAME_TIME){
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
        else{
            running += delta;
        }
        
    }
    
    
}
