import edu.princeton.cs.introcs.StdDraw;


public class Wall implements GameObject {
	
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
			System.out.println("PLayer collision");
			
			return this;
		}
		else{
			
			return this;
		}
	}

	public void action(GameObject action){

	}

	public Location move(){
		return location; 
	}

	public void draw(int centerX, int centerY, int cellSize){

		StdDraw.picture(centerX * cellSize, centerY * cellSize, "grass.png", cellSize, cellSize);

	}
}
