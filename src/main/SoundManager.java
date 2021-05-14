/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.InputStream;
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
    Sound current;
    public void play(int sound) throws IOException, SlickException{
        current = new Sound("assets/sounds/" + Integer.toString(sound)+".wav");
        current.play();
    }
    
    
}
