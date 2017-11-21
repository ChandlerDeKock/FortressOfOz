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
	private int lives = 0;
	private String levelNo = "0";

	public Game(){
		//Sets up the game playing area

		String message = JOptionPane.showInputDialog(null, " Choose a level from 0 - 999");
		levelNo = message;
		grid = new Grid("Levels/Level" + levelNo + ".rdg", this);
		xRes = 15 * grid.getCellSize();
		yRes = 11 * grid.getCellSize();
		StdDraw.setCanvasSize(xRes -50, yRes);
		StdDraw.setXscale(0, xRes);
		StdDraw.setYscale(0, yRes);
	}

	public void reset() {
		// Reset the game to its initial state.


		msElapsed = 0;
		gameOver = false;
		score = 0;
		grid = new Grid("Levels/Level" + levelNo + ".rdg", this);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(xRes/2 -200 , yRes -50, "FORTRESS OF OZ");
		StdDraw.text(xRes/2 + 200 , yRes -50, "Score " +  getLives());
		StdDraw.text(xRes/2 , yRes -50, "Press Space to roar!");
		grid.draw(0,0);
		grid.populateGrid();
		grid.moveAll();
	}

	public void play(){

		while (!isGameOver()){

			StdDraw.clear();
			StdDraw.text(xRes/2 + 200 , yRes -50, "Score " +  getLives());
			StdDraw.text(xRes/2 , yRes -50, "Press Space to roar!");
			StdDraw.text(xRes/2 -200 , yRes -50, "FORTRESS OF OZ");
			grid.populateGrid();
			grid.draw(grid.player.getRow(),grid.player.getCol());
			msElapsed += 20;
			grid.moveAll();	
			StdDraw.show(20);
		}
	}

	public int getScore(){
		return score;
	}
	public int getLives(){

		return lives;
	}


	public void adjustLives(int scoreDifference){

		lives = lives + scoreDifference;
		if(lives == 0){

			signalGameOver();
		}
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

			StdDraw.picture(xRes/2,yRes/2 ,"wallpaper.png" , xRes, yRes);
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.text(xRes/2, yRes/2, "FORTRESS OF OZ");
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(xRes/2, yRes/4, "To Start Game,");
			StdDraw.text(xRes/2, yRes/8, "Press Any Key!");

			StdDraw.show(20);

		}
		StdDraw.clear();
	}

	public void gameOverScreen() {
		// Insert code to show the "game over" screen, and await a keypress from the
		// player before the game is restarted.

		StdDraw.clear(StdDraw.BLACK);

		while(!StdDraw.isKeyPressed(KeyEvent.VK_ENTER)){

			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(xRes/2, yRes/2, "YOU DIED");
			StdDraw.text(xRes/2, yRes/4, "To Start Game again");
			StdDraw.text(xRes/2, yRes/6, "Press Enter!");
			StdDraw.show(20);
		}
		String message = JOptionPane.showInputDialog(null, " Choose a level from 0 - 999");
		levelNo = message;
		reset();
	}


	public static void test(){

		Game game = new Game();

		while (true) {

			game.titleScreen(); 
			game.play();
			game.gameOverScreen();
			game.reset();
		}
	}

	public static void main(String[] args){ 

		test();
	}
}