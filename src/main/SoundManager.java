/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;


/**
 *
 * @author Luis
 */
public class SoundManager{
    // This Sound Manager Class provides the most simple solution to play sound in the game
    
    // Enum for simple and effective acess to the sound library
    enum SOUNDS {
        WALK,
        HIT,
        RESET,
        DEATH,
        FINISH
    }
    Sound walk;
    Sound hit;
    Sound reset;
    Sound death;
    Sound finish;
    
    Music music;
    
    
    Sound current;
    public void play(SoundManager.SOUNDS sound){
        switch(sound){
            case WALK:
                walk.play();
                break;
            case HIT:
                hit.play();
                break;
            case RESET:
                reset.play();
                break;
            case DEATH:
                death.play();
                break;
            case FINISH:
                finish.play();
                break;
        }
    }
    
    public void toggleMusic(boolean on){
        if(!on){
            music.stop();
        }
        else{
            music.loop();
        }
    }

    public SoundManager() {
        try {
            walk = new Sound("assets/sounds/0.wav");
            hit = new Sound("assets/sounds/1.wav");
            reset = new Sound("assets/sounds/2.wav");
            death = new Sound("assets/sounds/3.wav");
            finish = new Sound("assets/sounds/4.wav");
            
            music = new Music("assets/sounds/score.wav");
            music.setVolume(1);
        } catch (SlickException ex) {
            Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
