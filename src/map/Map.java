package map;


// Container for map data populated by map loader class
public class Map {
	public String name;
	public int id;
	public int turns;
	public String bg;
	public char[][] data;
	
	public Map(String name, int id, int turns, String bg, char[][] data) {
		this.name = name;
		this.id = id;
		this.turns = turns;
		this.bg = bg;
		this.data = data;
	}
	
}
