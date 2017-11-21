import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdDraw;

public class Grid {

	private static final String Grid = null;

	// Add instance variables.
	GameObject[][] grid;
	int GridCellSize;
	int NumRows;
	int NumCols;
	public Game game = null;


	public Grid (String filename , Game game){

		Scanner game1 = null;
		this.game = game;
		try{
			game1 = new Scanner(new File(filename));
		}
		catch(FileNotFoundException FME){
			FME.printStackTrace();
			System.exit(1);
		}
		String dimensions = game1.nextLine();
		String [] split = dimensions.split(";");
		NumRows = Integer.parseInt(split[0]);
		NumCols = Integer.parseInt(split[1]);
		GridCellSize = Integer.parseInt(split[2]);
		grid = new GameObject[NumRows][NumCols];
		while(game1.hasNextLine()){

			String walls = game1.nextLine();
			String [] split1 = walls.split(";");
			int xPos = Integer.parseInt(split1[0]);
			int yPos = Integer.parseInt(split1[1]);
			String obj = split1[2];
			if(obj.equals("W")){

				Wall w = new Wall();
				Location wall = new Location(xPos, yPos);
				w.init(game, this, wall);
				setObjectAt(wall, w);

			}
			if (obj.equals("F")){

				Floor f = new Floor();
				Location floor = new Location(xPos, yPos);
				f.init(game, this, floor);
				setObjectAt(floor, f);
			}
			if (obj.equals("G")){

				GoodThing g = new GoodThing();
				Location good = new Location(xPos, yPos);
				g.init(game, this, good);
				setObjectAt(good, g);

			}
			if (obj.equals("E")){

				BadThing b = new BadThing();
				Location bad = new Location(xPos, yPos);
				b.init(game, this, bad);
				setObjectAt(bad, b);
			}
			if (obj.equals("P")){
				Player p = new Player();
				Location player = new Location(xPos, yPos);
				p.init(game, this, player);

				setObjectAt(player, p);

			}
		}
	}

	public void reset() {
		// Set up an initial grid of game objects. This could be just an empty
		// grid, but
		// this depends on how you want your game to start off initially.
		// This can be called from outside to reset the board at the start of
		// play after
		// a game over condition has been reached.
		// Remember to include a Player object on this new grid, otherwise you
		// won't
		// have anything to control in the game!

		Player p = new Player();
		Location player = new Location(2, 0);
		p.init(game, this, player);

		setObjectAt(player, p);
	}
	//Creates the GoodThing and BadThing Objects
	public void populateGrid() {


	}
	public int getNumRows() {
		// Return the number of rows in the grid.
		return NumRows;
	}

	public int getNumCols() {
		// Return the number of columns in the grid.
		return NumCols;
	}

	public GameObject getObjectAt(Location loc) {
		// Return the object at location loc.

		int Row = loc.getRow();
		int Col = loc.getCol();
		return grid[Row][Col];
	}

	public void setObjectAt(Location loc, GameObject obj) {
		// Set the object at location loc.

		int Row = loc.getRow();
		int Col = loc.getCol();

		grid[Row][Col] = obj;
		grid[Row][Col].init(game, this, loc);
	}

	public int getCellSize() {
		// Return the cell size of the grid.
		return GridCellSize;
	}

	public void moveAll() {
		// Ask each object in the grid what its new location should be. Make a
		// new array
		// representing the objects in the grid at their new locations. If two
		// objects
		// ask to be moved to the same location, resolve this by calling the
		// collision
		// method on one of them and use the result of that in the new grid's
		// corresponding location.
		GameObject [][] grid1 = new GameObject[NumRows][NumCols];

		for (int i = 0; i < NumRows; i++) {
			for (int j = 0; j < NumCols; j++) {

				if(grid[i][j] != null){

					if(grid[i][j] instanceof Player){
						
						Location move = grid[i][j].move();
						grid1[move.getRow()][move.getCol()] = grid[i][j];
						
						if(grid1[move.getRow()][move.getCol()] instanceof Floor){

							System.out.println("Player moving into floor");
							grid1[move.getRow()][move.getCol()] = grid[i][j].collision(grid1[move.getRow()][move.getCol()]);
						}
						else if(grid1[move.getRow()][move.getCol()] instanceof Wall){
							System.out.println("Player moving into wall");
							
							grid1[i][j]= grid[i][j].collision(grid[move.getRow()][move.getCol()]);
						}

					}
					if(grid[i][j] instanceof BadThing){

						Location move = grid[i][j].move();
						grid1[move.getRow()][move.getCol()] = grid[i][j];

					}
					if(grid[i][j] instanceof GoodThing){

						Location move = grid[i][j].move();
						grid1[move.getRow()][move.getCol()] = grid[i][j];

					}
					if(grid[i][j] instanceof Floor){

						Location move = grid[i][j].move();
						grid1[move.getRow()][move.getCol()] = grid[i][j];
					}
					if(grid[i][j] instanceof Wall){

						Location move = grid[i][j].move();
						grid1[move.getRow()][move.getCol()] = grid[i][j];
					}
				}
			}
		}
		for (int i = 0; i < NumRows; i++) {
			for (int j = 0; j < NumCols; j++) {

				grid[i][j] = grid1[i][j];

			}
		}
	}



	public void draw(int atX, int atY) {
		// Draw every object in the grid, going through every row and column.
		// The origin
		// of the grid should be at drawAtX and drawAtY. You must delegate each
		// object's
		// painting the object itself.
		for (int i = 0; i < NumRows; i++) {
			for (int j = 0; j < NumCols; j++) {

				if (grid[i][j] != null) {

					grid[i][j].draw(j, i, GridCellSize);

				}
			}
		}
	}

	public boolean isValid(Location loc) {
		// Returns true if the location Loc is on the grid, otherwise it returns
		// false.
		if (loc.getRow() < 0 || loc.getCol() < 0) {

			return false;
		}
		if (loc.getRow() > (NumRows - 1) || loc.getCol() > (NumRows - 1)) {

			return false;
		}

		return true;
	}
}