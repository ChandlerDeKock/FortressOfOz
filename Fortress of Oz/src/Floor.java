import edu.princeton.cs.introcs.StdDraw;


public class Floor implements GameObject {
	
	private Game game = null;
	private Grid grid = null;
	private Location location = null;

	public void init(Game game, Grid grid, Location location){
		
		this.game = game;
		this.grid = grid;
		this.location = location;

	}

	public GameObject collision(GameObject otherObject){
		
		if(otherObject instanceof Player){
	
			return otherObject;
		}
		
			return otherObject;
	}

	public void action(GameObject action){
		
		

	}

	public Location move(){
		
		return location; 
	}

	public void draw(int centerX, int centerY, int cellSize){
		
		StdDraw.picture(centerX * cellSize, centerY * cellSize, "floor.png", cellSize, cellSize);

	}
}