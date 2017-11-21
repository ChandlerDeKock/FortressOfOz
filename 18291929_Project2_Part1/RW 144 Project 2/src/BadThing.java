import edu.princeton.cs.introcs.StdDraw;

public class BadThing implements GameObject {

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

			return this;
		}
		else{

			return null;
		}

	}

	public void draw(int centerX, int centerY, int cellSize) {
		// Draw the object at location centerX, centerY with 
		// width/height of cellSize by cellSize. You can use avoid.gif as a placeholder
		// image for this.
		StdDraw.picture(centerX * cellSize, centerY * cellSize, "path.png", cellSize,cellSize);
		StdDraw.picture(centerX * cellSize, centerY * cellSize, "Red.png", 64, 50);
	}

	public Location move() {
		// Update the position of this object, and return the new position. This should
		// move the object to the left.
		// It is recommended that you move the object every 300ms as an initial choice.


		double random = Math.random();
		int down = 0;
		int up = 0;
		int left = 0;
		int right= 0;


		if(random >= 0 && random <= 0.25){

			down = 1;
		}
		if(random >0.25 && random <= 0.5){

			up = 1;
		}
		if(random > 0.5 && random <= 0.75){

			left = 1;
		}
		if(random > 0.75 && random <= 1){

			right = 1;
		}


		if(game.getTimeElapsed() % 999 == 0){

			int Row = location.getRow();
			int Col = location.getCol();
			Location move = new Location(Row + right - left, Col + down - up );
			location = move; 

			return move;
		}
		else{
			return location;

		}	
	}

	public void action(GameObject action) {
		// TODO Auto-generated method stub

	}
}