import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Player implements GameObject {

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
		// Handle a player object's collision with another object. Return the object that
		// remains if this collision occurs. Note that you can use the "instanceof" keyword
		// to test whether otherObject is of a given type.
		if(otherObject instanceof GoodThing){

			game.adjustLives(1);
			return this;
		}if(otherObject instanceof BadThing && StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){
			
		
			return this;
		}

		else if((otherObject instanceof BadThing)){
			
			game.signalGameOver();
			return this;
		}else{
			
			return this;
		}
	}

	public void action(GameObject action) {
		
					
			
			
			
		

	}

	public Location move() {
		// Calculate the player's new location. Here, you should handle
		// keyboard input from the player. It is suggested that you update
		// the player's location every 100ms.

		int Row = location.getRow();
		int Col = location.getCol();
		Location move = new Location(Row, Col);


		if(game.getTimeElapsed() % 100 == 0){


			if(StdDraw.isKeyPressed(KeyEvent.VK_UP) ){

				if (Row - 1 < 0){

					move = new Location(Row, Col);
					grid.player = move;
				}

				if((grid.getObjectAt(new Location(Row + 1, Col)) instanceof Wall)){

					move = new Location(Row, Col);
				}
				else{

					move = new Location(Row + 1, Col);
					grid.player = move;
				}		
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT) ){

				if (Col - 1 < 0){

					move = new Location(Row, Col);
				}

				if(grid.getObjectAt(new Location(Row , Col -1)) instanceof Wall) {

					move = new Location(Row, Col);
				}
				else{
					
					move = new Location(Row , Col -1);
					grid.player = move;
				}		
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) ){

				if (!(Col + 1 >= grid.getNumCols())){

					move = new Location(Row, Col);

					if(grid.getObjectAt( new Location(Row , Col + 1)) instanceof Wall){

						move = new Location(Row, Col);
					}
					else{

						move = new Location(Row , Col + 1);
						grid.player = move;
					}
				}
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){

				if (Row - 1 < 0){

					move = new Location(Row, Col);
				}

				if(grid.getObjectAt(new Location(Row  - 1, Col)) instanceof Wall){

					move = new Location(Row, Col);
				}
				else{

					move = new Location(Row - 1, Col);
					grid.player = move;
					
				}		
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){

				game.signalGameOver();
			}
		}
		location = move;
		
		return move;
	}
	

	public void draw(int centerX  , int centerY, int cellSize) {
		// Draw the player at location x and y, with width/height of cellSize.
		// You can use user.gif as a test image for this.
		
		
		if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){
			
			StdDraw.picture(centerX * cellSize, centerY * cellSize , "floor.png", cellSize, cellSize);
			StdDraw.picture(centerX * cellSize, centerY * cellSize, "lion1.png", 64, 50);
			StdDraw.picture(centerX * cellSize, centerY * cellSize, "roarup.png", cellSize, cellSize, 0);
		
		}else{
			
			StdDraw.picture(centerX * cellSize, centerY * cellSize, "floor.png", cellSize, cellSize);
			StdDraw.picture(centerX * cellSize, centerY * cellSize, "lion1.png", 64, 50);
		}
		
	}
}