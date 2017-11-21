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
			
			
			return this;
		}
		else{
			
			return this;
		}
	}

	public void action(GameObject action){

	}

	public Location move(){
		
		
		int Row = location.getRow();
		int Col = location.getCol();
		Location move = new Location(Row, Col);
		
		return move; 
	}

	public void draw(int centerX, int centerY, int cellSize){

		StdDraw.picture(centerX * cellSize, centerY * cellSize, "wall.png", cellSize, cellSize);

	}
}
