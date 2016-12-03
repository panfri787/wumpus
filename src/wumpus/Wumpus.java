package wumpus;

import java.util.Random;

/**
 * Class of the Wumpus character of the game 
 * @author Pablo
 *
 */
public class Wumpus extends Character {
	
	/**
	 * Construct a Wumpus object placing it randomly on the board avoiding hole cells.
	 * @param board Current board of the game
	 */
	public Wumpus(Board board){
		super();
		Random randomGenerator = new Random();
		int x,y;
		
		do {
			x = randomGenerator.nextInt(board.getSize());
			y = randomGenerator.nextInt(board.getSize());
		} while (board.getCell(x, y).getType() == 'H');
		setxPos(x);
		setyPos(y);
	}
}
