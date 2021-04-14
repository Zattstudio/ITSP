package scene;
import main.Actor;

public class Player extends Actor {
	
	public int turns;
	public int lives;
	
	public void move(Direction d) {
		switch (d){
		case LEFT:
			setPos(getPosX()-1, getPosY());
			break;
		case RIGHT:
			setPos(getPosX()+1, getPosY());
			break;
		case UP:
			setPos(getPosX(), getPosY()-1);
			break;
		case DOWN:
			setPos(getPosX(), getPosY()+1);
			break;
		}
		
	}
	
}
