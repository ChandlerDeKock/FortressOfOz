import edu.princeton.cs.introcs.StdDraw;

public class GoodThing implements GameObject {  

	private Game game = null;
	private Grid grid = null;
	private Location location = null;

	public void init(Game game, Grid grid, Location location) {
		// Initialize the player object with a reference to the game, grid and the 
		// player's starting location.
		this.game = game;
		this.grid = grid;
		this.location = location;
	}

	public GameObject collision(GameObject otherObject) {
		// What does the object do if it collides with another object? Return the object
		// that remains if these two objects collide. Note that you can use the "instanceof" keyword
		// to test whether otherObject is of a given type.
	
			if(otherObject instanceof Player){
				
				game.adjustLives(1);
				return otherObject;
				
			}else{
				
				return this;
			}
			
			
		
		
	}
	public void action(GameObject action){
		
		
	 }

	public void draw(int centerX, int centerY, int cellSize) {
		// Draw the object at location centerX, centerY with 
		// width/height of cellSize by cellSize. You can use get.gif as a placeholder
		// image for this.
		StdDraw.picture(centerX * cellSize, centerY * cellSize, "floor.png", cellSize,cellSize);
		StdDraw.picture(centerX * cellSize, centerY * cellSize, "Green.png", 64, 50);
	
	}

	public Location move() {
		// Update the position of this object, and return the new position. This should
		// move the object to the left.
		// It is recommended that you move the object every 300ms as an initial choice.
		
			return location;
	}
}