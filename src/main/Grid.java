package main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;                                                 //test

import map.MapHandler;


public class Grid extends GameObject {
	
        private GameState gs;                                                   //test
	private int xWidth;
	private int yHeight;
	private int offset = 5;
	private int tileSize;
	private MapHandler m;
                    //TEST
        public boolean playerLeft = true;
	public boolean front = false;
        public boolean back = false;
        
	private Image ground;
	private Image rock;
	private Image end_door;
        private Image player_side;                                                   //test
	private Image player_front;
        private Image player_back;
        private Input inp;                                                      //test
	
	public Grid(int xWidth, int yHeight, int tileSize, int offset, MapHandler m) {
		super();
		this.xWidth = xWidth;
		this.yHeight = yHeight;
		this.tileSize = tileSize;
		this.offset = offset;
		this.m = m;
		
		try {
			rock = new Image("assets/gfx/scene/testrock.png");
			ground = new Image ("assets/gfx/scene/groundtest.png");
			end_door = new Image ("assets/gfx/scene/enddoor.png");
                        player_side = new Image ("assets/gfx/scene/player.png");     //test
                        player_front = new Image ("assets/gfx/scene/player_front.png");
                        player_back = new Image ("assets/gfx/scene/player_back.png");
                } catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rock.setFilter(Image.FILTER_NEAREST);
		ground.setFilter(Image.FILTER_NEAREST);
		end_door.setFilter(Image.FILTER_NEAREST);
                player_side.setFilter(Image.FILTER_NEAREST);                         //test
                player_front.setFilter(Image.FILTER_NEAREST);
                player_back.setFilter(Image.FILTER_NEAREST);
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
				else if  ((char) m.getTile(j, i) == '.') {
					ground.draw(currentX, currentY, tileSize/ground.getWidth());
				}
				else if  ((char) m.getTile(j, i) == 'e') {
					end_door.draw(currentX, currentY, tileSize/end_door.getWidth());
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
                                        
                                        
//gfx.fillArc(currentX, currentY, tileSize, tileSize, 0f, 360f);    Player=Circle
				}
                                

				//else gfx.drawRect(currentX, currentY, tileSize, tileSize);
				currentX += tileSize + offset;
				
				
			}
			currentX = getPosX();
			currentY += tileSize + offset;
		}
		
	}

}