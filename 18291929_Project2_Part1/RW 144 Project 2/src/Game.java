import java.awt.event.KeyEvent;
import java.awt.Font;
import java.io.File;

import javax.swing.JOptionPane;

import edu.princeton.cs.introcs.StdDraw;


public class Game
{
	private Grid grid;
	private long msElapsed;
	private int score;
	private int xRes;
	private int yRes;
	private boolean gameOver;
	
	


	public Game(){
		//Sets up the game playing area
	
		
		grid = new Grid("Level0.rdg", this);
		xRes = grid.getNumCols() * grid.getCellSize();
		yRes = grid.getNumRows()* grid.getCellSize();
		grid.draw(0,10);
		StdDraw.setCanvasSize(xRes/2 , yRes/2);
		StdDraw.setXscale(0, xRes);
		StdDraw.setYscale(0, yRes);
	}

	public void reset() {
		// Reset the game to its initial state.

		gameOver = false;
		grid = new Grid("Level0.rdg", this);
		grid.game = this;
		msElapsed = 0;
		score = 0;
		grid.draw(0,0);
		grid.populateGrid();
		grid.moveAll();
	}

	public void play(){

		while (!isGameOver()){
			//moves all objects of the game

			StdDraw.clear(StdDraw.WHITE);
			grid.draw(0, 10);
			StdDraw.show(20);
			msElapsed += 20;
			//grid.populateGrid();
			grid.moveAll();
		}
	}

	public int getScore(){
		return score;
	}
	
	
	public void adjustScore(int scoreDifference){
	//updates score
		score = score + scoreDifference;
	}

	public long getTimeElapsed() {
	//timer: 
		return msElapsed;
	}

	public boolean isGameOver(){
		
		return gameOver;
	}
	

	public void signalGameOver(){
		
		gameOver = true;
	}

	public void titleScreen() {
		// Insert code to show the title screen and await a keypress from the player
		// to start the game.

	
		while(!StdDraw.hasNextKeyTyped()){

			StdDraw.setPenColor(StdDraw.WHITE);		
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(xRes/2, yRes/4, "To Start Game,");
			StdDraw.text(xRes/2, yRes/8, "Press Any Key!");
			StdDraw.show(20);
		}
	}

	public void gameOverScreen() {
		// Insert code to show the "game over" screen, and await a keypress from the
		// player before the game is restarted.

		StdDraw.clear(StdDraw.BLACK);
		while(!StdDraw.isKeyPressed(KeyEvent.VK_ENTER)){

			StdDraw.setPenColor(StdDraw.BLACK);
			
			StdDraw.show(20);
		}
	}


	public static void test(){

		Game game = new Game();

		while (true) {
			//game.titleScreen(); 
			game.play();
			game.gameOverScreen();
			game.reset();
		}
	}

	public static void main(String[] args){ 

		test();
	}
}