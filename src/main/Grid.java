package main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utility.Numbers;
                                             //test

import map.MapHandler;


public class Grid extends GameObject {
	
	private int xWidth;
	private int yHeight;
	private int offset = 5;
	private int tileSize;
	private MapHandler m;
                    //TEST
        public boolean playerLeft = true;
	public boolean front = false;
        public boolean back = false;
        private boolean locked = true;
        private boolean lockedM = true;
        public boolean keyVisible = true;
        public boolean keyMVisible = true;
        
	public Image ground;
	private Image rock;
        private Image puddle;
        private Image gravestone;
        
        private Image player_side;
	private Image player_front;
        private Image player_back;
        
        public Image key;
        public Image end_door;
        public Image led;                                                       // locked end door
        
        public Image master_key;
        public Image master_door;
        public Image lmd;                                                       // locked master door
        
        private Image cross;                                                    // cross water
        private Image horizontal;                                               // horizontal water
        private Image vertical;                                                 // vertical water
        private Image bH;                                                       // bridge horizontal
        private Image bV;                                                       // bridge vertical
	
	public Grid(int xWidth, int yHeight, int tileSize, int offset, MapHandler m) {
		super();
		this.xWidth = xWidth;
		this.yHeight = yHeight;
		this.tileSize = tileSize;
		this.offset = offset;
		this.m = m;
		// stupid solution as for batch loading but works for now
		try {
			rock = new Image("assets/gfx/scene/testrock.png");
			ground = new Image ("assets/gfx/scene/groundtest.png");
			end_door = new Image ("assets/gfx/scene/enddoor.png");
                        puddle = new Image ("assets/gfx/scene/puddle.png");
                        gravestone = new Image ("assets/gfx/scene/gravestone.png");
                        key = new Image ("assets/gfx/scene/key.png");
                        led = new Image ("assets/gfx/scene/locked_end_door.png");
                        
                        master_key = new Image ("assets/gfx/scene/master_key.png");
                        master_door = new Image ("assets/gfx/scene/master_door.png");
                        lmd = new Image ("assets/gfx/scene/locked_master_door.png");
              
                        cross = new Image ("assets/gfx/scene/cross.png");
                        horizontal = new Image ("assets/gfx/scene/horizontal.png");
                        vertical = new Image ("assets/gfx/scene/vertical.png");
                        bH = new Image ("assets/gfx/scene/bridgeH.png");
                        bV = new Image ("assets/gfx/scene/bridgeV.png");
                        
                        player_side = new Image ("assets/gfx/scene/player.png");
                        player_front = new Image ("assets/gfx/scene/player_front.png");
                        player_back = new Image ("assets/gfx/scene/player_back.png");
                } catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
                //Setting filters so pixel images do not blur
		rock.setFilter(Image.FILTER_NEAREST);
		ground.setFilter(Image.FILTER_NEAREST);
		end_door.setFilter(Image.FILTER_NEAREST);
                puddle.setFilter(Image.FILTER_NEAREST);
                gravestone.setFilter(Image.FILTER_NEAREST);
                key.setFilter(Image.FILTER_NEAREST);
                led.setFilter(Image.FILTER_NEAREST);
                
                master_door.setFilter(Image.FILTER_NEAREST);
                master_key.setFilter(Image.FILTER_NEAREST);
                lmd.setFilter(Image.FILTER_NEAREST);
                
                cross.setFilter(Image.FILTER_NEAREST);                    
                horizontal.setFilter(Image.FILTER_NEAREST);
                vertical.setFilter(Image.FILTER_NEAREST);
                bH.setFilter(Image.FILTER_NEAREST);
                bV.setFilter(Image.FILTER_NEAREST);
                
                player_side.setFilter(Image.FILTER_NEAREST);                    
                player_front.setFilter(Image.FILTER_NEAREST);
                player_back.setFilter(Image.FILTER_NEAREST);
        }
	
        public void unlock(){
            locked = false;
        }
        public void lock(){
            locked = true;
        }
        public void unlockM(){
            lockedM = false;
        }
        public void lockM(){
            lockedM = true;
        }
        
	@Override
	public void draw(Graphics gfx) {
		int currentX = getPosX();
		int currentY = getPosY();
		for (int i = 0; i < yHeight; i++) {
			for (int j = 0; j < xWidth; j++) {
                                
				if ((char) m.getTile(j, i) == '#') {
					rock.draw(currentX, currentY, tileSize/rock.getWidth()+1);
				}
                                else if  ((char) m.getTile(j, i) == 'm') {
					master_door.draw(currentX, currentY, tileSize/master_door.getWidth());
				}
                                if ((char) m.getTile(j, i) == 'L') {
					if(lockedM) lmd.draw(currentX, currentY, tileSize/lmd.getWidth());
                                    else master_door.draw(currentX, currentY, tileSize/master_door.getWidth());
				}
				else if  ((char) m.getTile(j, i) == '.') {
                                    ground.getFlippedCopy(i%3 != 0, i%2 == 0).draw(currentX, currentY, tileSize/ground.getWidth());
				}
				else if  ((char) m.getTile(j, i) == 'e' || (char) m.getTile(j, i) == 'E') {
					end_door.draw(currentX, currentY, tileSize/end_door.getWidth());
				}
                                else if  ((char) m.getTile(j, i) == 'x') {
					puddle.draw(currentX, currentY, tileSize/puddle.getWidth());
				}
                                else if  ((char) m.getTile(j, i) == '+') {
					gravestone.draw(currentX, currentY, tileSize/gravestone.getWidth());
				}
                                else if  ((char) m.getTile(j, i) == 'c') {
					cross.draw(currentX, currentY, tileSize/cross.getWidth());
				}
                                else if  ((char) m.getTile(j, i) == 'h') {
					horizontal.draw(currentX, currentY, tileSize/horizontal.getWidth());
				}
                                else if  ((char) m.getTile(j, i) == 'v') {
					vertical.draw(currentX, currentY, tileSize/vertical.getWidth());
				}
                                else if  ((char) m.getTile(j, i) == 'H') {
                                    bH.getFlippedCopy(i%3 != 0, i%2 == 0).draw(currentX, currentY, tileSize/bH.getWidth());
				}
                                else if  ((char) m.getTile(j, i) == 'V') {
                                    bV.getFlippedCopy(i%3 != 0, i%2 == 0).draw(currentX, currentY, tileSize/bV.getWidth());
				}
                                else if  ((char) m.getTile(j, i) == 'k' && keyVisible) {
					key.draw(currentX, currentY, tileSize/key.getWidth());
				}
                                else if  ((char) m.getTile(j, i) == 'K' && keyMVisible) {
					master_key.draw(currentX, currentY, tileSize/master_key.getWidth());
				}
                                else if  ((char) m.getTile(j, i) == 'l') {
                                    if(locked) led.draw(currentX, currentY, tileSize/led.getWidth());
                                    else end_door.draw(currentX, currentY, tileSize/end_door.getWidth());
				}
                                
				if (j == m.getCurrentX() && i == m.getCurrentY()) {
                                        
                                        if(front == true){
                                            player_front.draw(currentX, currentY, tileSize/player_front.getWidth());
                                        }
                                        else if (back == true) {
                                            player_back.draw(currentX, currentY, tileSize/player_front.getWidth());
                                        }
                                        else{
                                            player_side.getFlippedCopy(!playerLeft, false).draw(currentX, currentY, tileSize/player_side.getWidth());    //Player = PlayerImage
                                        }
                                        
                                        
				}
                                

				
				currentX += tileSize + offset;
				
				
			}
			currentX = getPosX();
			currentY += tileSize + offset;
		}
		
	}

}