
import java.util.Scanner;

public class LevelGeneratorDriver {
	
	public static void main(String[] args) {
		LevelGenerator lG = new LevelGenerator();
		
		
		Scanner sC = new Scanner(System.in);
		do {
			int width = (int) (Math.random() * 100 + 10);
			int height = (int) (Math.random() * 100 + 10);
			int numberOfRooms = (int) (Math.max(0.1 * Math.max(width, height), 2));
			int maxRoomWidth = (int) Math.max(4, width * 0.1);
			int maxRoomHeight = (int) Math.max(4, width * 0.1);
			printGrid(lG.generateLevel(height, width, numberOfRooms, 3, 3, maxRoomHeight, maxRoomWidth));
			System.out.println("Type 's' to stop");
		} while (!sC.nextLine().equals("s"));
		sC.close();
		
	}
	
	public static void printGrid(char[][] test) {
		StringBuilder sbuffer = new StringBuilder();
		for (int j = test.length - 1; j >= 0; j--) {
			for (int i = 0; i < test[0].length; i++) {
				sbuffer.append(test[j][i]);
			}
			sbuffer.append("\n");
		}
		System.out.print(sbuffer.toString());// .replace('.', ' '));
	}
}