package map;

import utility.Vector2;

public class MapHandler {
	Map map;
	
	char[][] mapData = new char[][] {
	}; // Two dimensional Array (Table) that holds the chars
	
	// temp solution for one test map
	/*["##########",
	 * "#........#",
	 * "#....###.#",
	 * "#........#",
	 * "#####....#",
	 * "#../.....#",
	 * "#.p#..#..#",
	 * "##########"]
	 * */
	
	
	
	private int currentX; // current x position dispatched from player
	private int currentY; // current y position dispatched from player
	final char playerChar = 'p'; // player start char
	MapLoader mLoader;
	
	
	public MapHandler() {
		mLoader = new MapLoader();

	}
	
	void findPlayer() {
		for (int i = 0; i < mapData.length; i++) {
			for (int j = 0; j < mapData[i].length; j++) {
				if (mapData[i][j] == playerChar) {
					currentX = j;
					currentY = i;
					return;		
				}
			}
		}
		// Fallback handling here
	}
	
	public void changeMap(int nr) {
		map = mLoader.loadMap(Integer.toString(nr));
		mapData = map.data;
		findPlayer();
	}
	
	public int getCurrentX() {
		return currentX;
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public int getCurrentY() {
		return currentY;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}

	public char checkRelative(int xOff, int yOff) {
		if (currentX+xOff > mapData[0].length-1 || currentX+xOff < 0 || currentY+yOff < 0 || currentY+yOff > mapData.length-1) {
			/// Wenn out of map boundaries, simuliere wand durch # 
			return '#';
		}
		return mapData[currentY+yOff][currentX+xOff];
	}
	
	public char getCurrentTile() {
		return mapData[currentY][currentX];
	}
	
	public char getTile(int x, int y) {
		return mapData[y][x];
	}
	
	
	// More of a debug function for testing (formerly printFull)
	public void prettyPrint() {
		for (int i = 0; i < mapData.length; i++) {
			for (int j = 0; j < mapData[i].length; j++) {
				if (i == currentY && j == currentX) {
					System.out.print('@');
				}
				else {
					System.out.print(mapData[i][j]);
				}
			}
			System.out.print("\n");
		}
	}
	
	// More of a debug function for testing (formerly printFull)
	public String prettyPrintString() {
		String opt = "";
		for (int i = 0; i < mapData.length; i++) {
			for (int j = 0; j < mapData[i].length; j++) {
				if (i == currentY && j == currentX) {
					opt += '@';
				}
				else {
					opt += mapData[i][j];
					// hallo
				}
			}
			opt += "\n";
		}
		return opt;
	}
	public void setPosition(int x, int y) {
		currentX = x;
		currentY = y;
	}
	
	
	
	
	public Map getMap() {
		return map;
	}

	public Vector2 getMaxExtents() {
		return new Vector2(mapData[0].length, mapData.length);
	}
}
