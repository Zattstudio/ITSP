

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.state.StateBasedGame;

import main.GameState;
import main.MenuState;
import java.io.File;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public class MainGame extends StateBasedGame {

    // Game state identifiers
    //public static final int SPLASHSCREEN = 0;
    //public static final int MAINMENU     = 1;
    public static final int GAME         = 0;
    
    // Application Properties 
    // NOTE: If possible time-wise, replace constants with saved config 
    public static final double SCALE   = 1;
    public static final int WIDTH   = (int) (1920*SCALE);
    public static final int HEIGHT  = (int) (1080*SCALE);
    public static final int FPS     = 60;
    public static final double VERSION = 1.0;
    
	//test
	
	public MainGame(String name) {
		super(name);
	}

	public static void main(String[] args) {
            System.setProperty("org.lwjgl.librarypath", new File("lib/slick/").getAbsolutePath());
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new ScalableGame(new MainGame("ITProjekt"), 1920, 1080) );
			appgc.setDisplayMode(WIDTH,HEIGHT , false); 
                        appgc.setShowFPS(true);
                        appgc.setTargetFrameRate(60);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
                        // Crash handling
		}

	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new MenuState());
		this.addState(new GameState());
		
	}

}
