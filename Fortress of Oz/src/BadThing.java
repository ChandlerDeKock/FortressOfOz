import java.awt.event.KeyEvent;

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

		if(otherObject instanceof Player && StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && !(otherObject instanceof Floor)){

			return otherObject;
			
			
		}else if(otherObject instanceof Player){
			
			game.signalGameOver();
			return otherObject;
		}
		else{
			
			return this;
		}
	}

	public void draw(int centerX, int centerY, int cellSize) {
		// Draw the object at location centerX, centerY with 
		// width/height of cellSize by cellSize. You can use avoid.gif as a placeholder
		// image for this.
		StdDraw.picture(centerX * cellSize, centerY * cellSize, "floor.png", cellSize, cellSize);
		StdDraw.picture(centerX * cellSize, centerY * cellSize, "Flying-Monkeys.png", 64, 50);
	}

	public Location move() {
		// Update the position of this object, and return the new position. This should
		// move the object to the left.
		// It is recommended that you move the object every 300ms as an initial choice.

		grid.updatePathToPlayer();

		int i = grid.bad.getRow();
		int j = grid.bad.getCol();

		Location move = new Location(i , j);

		if(game.getTimeElapsed() % 500 == 0 ){

			if(grid.getObjectAt(move) instanceof Wall){

				return location;

			}else{

				location = move;
				return move;
			}
		}else{

			return location;
		}
	}

	public void action(GameObject action) {
		// TODO Auto-generated method stub

	}
}