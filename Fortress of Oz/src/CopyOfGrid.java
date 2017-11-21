//import java.awt.Color;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayDeque;
//import java.util.Queue;
//import java.util.Scanner;
//
//import edu.princeton.cs.introcs.StdDraw;
//
//public class CopyOfGrid {
//
//	private static final String Grid = null;
//
//	// Add instance variables.
//	GameObject[][] grid;
//	BFSnode[][] nodes;
//	int GridCellSize;
//	int NumRows;
//	int NumCols;
//	public Game game = null;
//	public Location player;
//	BFSnode p;
//	public Location bad;
//
//	public CopyOfGrid(String filename, Game game) {
//
//		Scanner game1 = null;
//		this.game = game;
//		try {
//			game1 = new Scanner(new File(filename));
//		} catch (FileNotFoundException FME) {
//			FME.printStackTrace();
//			System.exit(1);
//		}
//		String dimensions = game1.nextLine();
//		String[] split = dimensions.split(";");
//		NumRows = Integer.parseInt(split[0]);
//		NumCols = Integer.parseInt(split[1]);
//		GridCellSize = Integer.parseInt(split[2]);
//		grid = new GameObject[NumRows][NumCols];
//		while (game1.hasNextLine()) {
//
//			String walls = game1.nextLine();
//			String[] split1 = walls.split(";");
//			int xPos = Integer.parseInt(split1[0]);
//			int yPos = Integer.parseInt(split1[1]);
//			String obj = split1[2];
//			if (obj.equals("W")) {
//
//				Wall w = new Wall();
//				Location wall = new Location(xPos, yPos);
//				w.init(game, this, wall);
//				setObjectAt(wall, w);
//
//			}
//			if (obj.equals("F")) {
//
//				Floor f = new Floor();
//				Location floor = new Location(xPos, yPos);
//				f.init(game, this, floor);
//				setObjectAt(floor, f);
//			}
//			if (obj.equals("G")) {
//
//				GoodThing g = new GoodThing();
//				Location good = new Location(xPos, yPos);
//				g.init(game, this, good);
//				setObjectAt(good, g);
//
//			}
//			if (obj.equals("B")) {
//
//				BadThing b = new BadThing();
//				Location bad = new Location(xPos, yPos);
//				b.init(game, this, bad);
//				setObjectAt(bad, b);
//			}
//			if (obj.equals("P")) {
//				Player p = new Player();
//				player = new Location(xPos, yPos);
//				p.init(game, this, player);
//				setObjectAt(player, p);
//
//			}
//		}
//		nodes = new BFSnode[NumRows][NumCols];
//		for (int i = 0; i < NumRows; i++) {
//			for (int j = 0; j < NumCols; j++) {
//
//				nodes[i][j] = new BFSnode(new Location(i, j));
//			}
//		}
//	}
//
//	public void reset() {
//		// Set up an initial grid of game objects. This could be just an empty
//		// grid, but
//		// this depends on how you want your game to start off initially.
//		// This can be called from outside to reset the board at the start of
//		// play after
//		// a game over condition has been reached.
//		// Remember to include a Player object on this new grid, otherwise you
//		// won't
//		// have anything to control in the game!
//
//		Player p = new Player();
//		Location player = new Location(2, 0);
//		p.init(game, this, player);
//
//		setObjectAt(player, p);
//	}
//
//	// Creates the GoodThing and BadThing Objects
//	public void populateGrid() {
//
//		for (int i = 0; i < NumRows; i++) {
//			for (int j = 0; j < NumCols; j++) {
//
//				int generatorX = (int) Math.random() * NumRows;
//				int generatorY = (int) Math.random() * NumCols;
//				double check = Math.random();
//
//				if (grid[generatorX][generatorY] instanceof Floor
//						&& check <= 0.1) {
//
//					setObjectAt(new Location(generatorX, generatorY),
//							new BadThing());
//				}
//				if (grid[generatorX][generatorY] instanceof Floor
//						&& check >= 0.9) {
//
//					setObjectAt(new Location(generatorX, generatorY),
//							new GoodThing());
//				}
//
//			}
//		}
//	}
//
//	public int getNumRows() {
//		// Return the number of rows in the grid.
//		return NumRows;
//	}
//
//	public int getNumCols() {
//		// Return the number of columns in the grid.
//		return NumCols;
//	}
//
//	public GameObject getObjectAt(Location loc) {
//		// Return the object at location loc.
//
//		int Row = loc.getRow();
//		int Col = loc.getCol();
//		return grid[Row][Col];
//	}
//
//	public void setObjectAt(Location loc, GameObject obj) {
//		// Set the object at location loc.
//
//		int Row = loc.getRow();
//		int Col = loc.getCol();
//
//		grid[Row][Col] = obj;
//		grid[Row][Col].init(game, this, loc);
//	}
//
//	public int getCellSize() {
//		// Return the cell size of the grid.
//		return GridCellSize;
//	}
//
//	public void moveAll() {
//		// Ask each object in the grid what its new location should be. Make a
//		// new array
//		// representing the objects in the grid at their new locations. If two
//		// objects
//		// ask to be moved to the same location, resolve this by calling the
//		// collision
//		// method on one of them and use the result of that in the new grid's
//		// corresponding location.
//		GameObject[][] grid1 = new GameObject[NumRows][NumCols];
//
//		for (int i = 0; i < NumRows; i++) {
//			for (int j = 0; j < NumCols; j++) {
//				if (grid[i][j] != null) {
//					Location move = grid[i][j].move();
//
//					if (grid[i][j] instanceof Player) {
//						if (grid[move.getRow()][move.getCol()] instanceof GoodThing) {
//							game.adjustScore(10);
//							// grid1[move.getRow()][move.getCol()].collision(grid[move.getRow()][move.getCol()]);
//						} else {
//							grid1[move.getRow()][move.getCol()] = grid[i][j];
//						}
//						Floor f = new Floor();
//						Location floor = new Location(i, j);
//						f.init(game, this, floor);
//						grid1[floor.getRow()][floor.getCol()] = f;
//						grid1[move.getRow()][move.getCol()] = grid[i][j];
//					}
//					if ((grid[i][j] instanceof Floor)
//							&& !(grid[move.getRow()][move.getCol()] instanceof Player)) {
//						grid1[i][j] = grid[i][j];
//					}
//					if ((grid[i][j] instanceof Wall)
//							&& !(grid[move.getRow()][move.getCol()] instanceof Player)) {
//						grid1[i][j] = grid[i][j];
//					}
//					if ((grid[i][j] instanceof GoodThing)
//							&& !(grid[move.getRow()][move.getCol()] instanceof Player)) {
//						grid1[i][j] = grid[i][j];
//					}
//					if ((grid[i][j] instanceof BadThing)
//							&& !(grid[move.getRow()][move.getCol()] instanceof Player)) {
//						grid1[move.getRow()][move.getCol()] = grid[i][j];
//
//						Floor f = new Floor();
//						Location floor = new Location(i, j);
//						f.init(game, this, floor);
//						grid1[floor.getRow()][floor.getCol()] = f;
//						grid1[move.getRow()][move.getCol()] = grid[i][j];
//					}
//				}
//			}
//		}
//
//		for (int i = 0; i < NumRows; i++) {
//			for (int j = 0; j < NumCols; j++) {
//
//				grid[i][j] = grid1[i][j];
//
//			}
//		}
//	}
//
//	public void draw(int atY, int atX) {
//		// Draw every object in the grid, going through every row and column.
//		// The origin
//		// of the grid should be at drawAtX and drawAtY. You must delegate each
//		// object's
//		// painting the object itself.
//
//		for (int i = atX - 7; i < atX + 7 && i < NumRows + atX + 7; i++) {
//			for (int j = atY - 5; j < atY + 5 && j < NumCols + atY + 5; j++) {
//
//				if (i >= 0 && i < NumCols && j >= 0 && j < NumRows) {
//
//					if (grid[j][i] != null) {
//
//						grid[j][i].draw(i - (atX - 7), j - (atY - 5),
//								GridCellSize);
//
//					}
//				}
//			}
//		}
//	}
//
//	// Moves badthing closer to player
//	public void updatePathToPlayer() {
//
//		int PlayerX = player.getRow();
//		int PlayerY = player.getCol();
//		// Location Player = new Location(PlayerX, PlayerY);
//		for (int i = 0; i < NumRows; i++) {
//			for (int j = 0; j < NumCols; j++) {
//
//				nodes[i][j].setMarked(false);
//				nodes[i][j].setPrev(null);
//			}
//		}
//
//		BFSnode first = nodes[PlayerX][PlayerY];
//		Queue<BFSnode> q = new ArrayDeque<BFSnode>();
//		q.add(first);
//
//		while (!q.isEmpty()) {
//
//			BFSnode n = q.poll();
//			Location nodeLoc = n.getLocation();
//			if (n.getMarked()) {
//
//				continue;
//			}
//			n.setMarked(true);
//
//			if (grid[nodeLoc.getRow()][nodeLoc.getCol()] instanceof BadThing) {
//
//				p = n;
//				p = p.getPrev();
//				bad = p.getLocation();
//				break;
//
//			} else {
//
//				if (nodeLoc.getRow() + 1 < NumRows
//						&& (grid[nodeLoc.getRow() + 1][nodeLoc.getCol()] instanceof Floor || grid[nodeLoc
//								.getRow() + 1][nodeLoc.getCol()] instanceof BadThing)) {
//
//					q.add(nodes[nodeLoc.getRow() + 1][nodeLoc.getCol()]);
//					nodes[nodeLoc.getRow() + 1][nodeLoc.getCol()].setPrev(n);
//				}
//
//				if (nodeLoc.getRow() - 1 >= 0
//						&& (grid[nodeLoc.getRow() - 1][nodeLoc.getCol()] instanceof Floor || grid[nodeLoc
//								.getRow() - 1][nodeLoc.getCol()] instanceof BadThing)) {
//
//					q.add(nodes[nodeLoc.getRow() - 1][nodeLoc.getCol()]);
//					nodes[nodeLoc.getRow() - 1][nodeLoc.getCol()].setPrev(n);
//				}
//
//				if (nodeLoc.getCol() + 1 < NumCols
//						&& (grid[nodeLoc.getRow()][nodeLoc.getCol() + 1] instanceof Floor || grid[nodeLoc
//								.getRow()][nodeLoc.getCol() + 1] instanceof BadThing)) {
//
//					q.add(nodes[nodeLoc.getRow()][nodeLoc.getCol() + 1]);
//					nodes[nodeLoc.getRow()][nodeLoc.getCol() + 1].setPrev(n);
//				}
//
//				if (nodeLoc.getCol() - 1 >= 0
//						&& (grid[nodeLoc.getRow()][nodeLoc.getCol() - 1] instanceof Floor || grid[nodeLoc
//								.getRow()][nodeLoc.getCol() - 1] instanceof BadThing)) {
//					q.add(nodes[nodeLoc.getRow()][nodeLoc.getCol() - 1]);
//					nodes[nodeLoc.getRow()][nodeLoc.getCol() - 1].setPrev(n);
//				}
//			}
//		}
//	}
//
//	public boolean isValid(Location loc) {
//		// Returns true if the location Loc is on the grid, otherwise it returns
//		// false.
//		if (loc.getRow() < 0 || loc.getCol() < 0) {
//
//			return false;
//		}
//		if (loc.getRow() > (NumRows - 1) || loc.getCol() > (NumRows - 1)) {
//
//			return false;
//		}
//
//		return true;
//	}
//}