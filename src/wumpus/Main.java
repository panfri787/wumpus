package wumpus;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {		
				
		Scanner sc = new Scanner(System.in);
		int mapSize, holesNumber, arrowsNumber = 0;
		boolean gameCreated = false;
	
		while (!gameCreated) {
			do {
				System.out.print(DialoguesManager.MAP_SIZE_ASK);
				mapSize = sc.nextInt();
			} while (mapSize < 2);
			do {
				System.out.print(DialoguesManager.HOLES_NUMBER_ASK);
				holesNumber = sc.nextInt();
			} while (holesNumber < 0);
			do {
				System.out.print(DialoguesManager.ARROWS_NUMBER_ASK);
				arrowsNumber = sc.nextInt();
			} while (arrowsNumber < 0);
			gameCreated = GameEngine.makeGame(mapSize, holesNumber, arrowsNumber);
			if(!gameCreated){
				System.out.println(DialoguesManager.IMPOSSIBLE_MAP);
			} else {
				GameEngine.getBoard().printBoard();
				Wumpus w = GameEngine.getWumpus();
				System.out.println("El wumpus esta en la "+ w.getxPos()+", "+w.getyPos());
				GameEngine.startGame();
			}
		}
		sc.close();
	}

}
