import java.awt.Rectangle;
import java.util.ArrayList;

//import edu.princeton.cs.introcs.StdDraw;

public class LevelGenerator {

	private int gridHeight;
	private int gridWidth;
	private int N;
	private int maxHeight;
	private int maxWidth;
	private int minHeight;
	private int minWidth;
	private char [][] generatelevel = new char [gridWidth][gridHeight];
	private ArrayList<Rectangle> Level;

	//	public LevelGenerator(int gridheight, int gridwidth,int n, int maxheight, int maxwidth, int minheight, int minwidth){
	//
	//		this.gridHeight = gridheight;
	//		this.gridWidth = gridwidth;
	//		this.maxHeight = maxheight;
	//		this.maxWidth = maxwidth;
	//		this.minHeight = minheight;
	//		this.minWidth = minwidth;
	//		this.N = n;
	//}
	public char[][] generateLevel(int gridheight, int gridwidth,int n, int maxheight, int maxwidth, int minheight, int minwidth){

		this.gridHeight = gridheight;
		this.gridWidth = gridwidth;
		this.maxHeight = maxheight;
		this.maxWidth = maxwidth;
		this.minHeight = minheight;
		this.minWidth = minwidth;
		this.N = n;
		ArrayList<Rectangle> Level = new ArrayList<Rectangle>();
		//
		//		StdDraw.setCanvasSize(640, 640);
		//		StdDraw.setXscale(0, 100);
		//		StdDraw.setYscale(0, 100);

		int set = 0;
		while(set < N){

			int centerX = (int) (Math.random()* gridHeight);
			int centerY = (int) (Math.random()* gridWidth);	
			int rectWidth = (int) Math.abs(((Math.random()* maxWidth)-(Math.random()*minWidth)));
			int rectHeight = (int) Math.abs(((Math.random()* maxHeight)-(Math.random()*minHeight)));
			if(rectWidth < minWidth  || rectHeight < minHeight){

				rectHeight = minHeight;
				rectWidth = minWidth;
			}

			Rectangle current = new Rectangle(centerX, centerY, rectWidth, rectHeight);

			if(Level.isEmpty()){

				Level.add(current);
				set++;

			}else{

				for(int i = 0 ; i < Level.size() ; i++){

					if(Level.get(i).intersects(current)|| Level.get(i).contains(current)){

						break;
					}
				}

			}Level.add(current);
			set++;

			for(int i = centerX - (rectWidth/2); i <  rectWidth ; i++){
				for(int j = centerY - (rectWidth/2) ; j <  rectHeight ; j++){

					generatelevel[i][j] ='F';

				}			
			}
		}
		return generatelevel;
	}
	public  void printGrid (char[][] level){

		for (int i = 0; i < gridHeight; i++) {
			for (int j = 0; j < gridWidth; j++) {

				if(level[i][j] != 'F'){

					level[i][j] = '.';
				}
			}
		}
	}
}
