package wumpus;

import java.util.Scanner;

/**
 * Class which control all the game flow
 * @author Pablo
 *
 */
public class GameEngine {
	
	/**
	 * Board of the game
	 */
	private static Board board;
	
	/**
	 * Hunter of the game
	 */
	private static Hunter hunter;
	
	/**
	 * Wumpus of the game
	 */
	private static Wumpus wumpus;
	
	/**
	 * Game is running or not
	 */
	private static boolean gameOn;
	
	/**
	 * Makes a game instance taking the size of the map, the number of holes in the map and the number of arrows which the hunter will start the game.
	 * Initialize the board too
	 * @param mapSize Requested size of the map
	 * @param holesNumber Requested number of holes in the map
	 * @param hunterArrows Requested number of arrows to the hunter
	 * @return True if requested configuration makes a possible map or false if it's impossible
	 */
	public static boolean makeGame(int mapSize, int holesNumber, int hunterArrows){
		board = new Board(mapSize, holesNumber);
		boolean possible = board.initializeBoard();
		hunter = new Hunter(board.getExitCell(), hunterArrows);
		wumpus = new Wumpus(board);
		gameOn = true;
		return possible;
	}
	
	/**
	 * Shows the game welcome, the commands help and start the flow of the game
	 */
	public static void startGame(){
		boolean correctCommand = false;
		System.out.println(DialoguesManager.WELCOME);
		System.out.println();
		System.out.println(DialoguesManager.COMMANDS_HELP);
		System.out.println();
		while(gameOn){
			gameOn = gameCheckings();
			if(gameOn){
				do{
					correctCommand = playerAction();
				}while(!correctCommand);
			}			
		}
		
	}
	
	/**
	 * Make the different checkings after every movement of the player and print the corresponding dialogues 
	 * @return True if the player still alive after his movement or false if he isn't
	 */
	private static boolean gameCheckings(){
		boolean stillAlive = hunter.isAlive();
		
		if(hunter.checkExit(board)){
			System.out.println(DialoguesManager.AT_EXIT);
		}
		
		if(hunter.checkWumpus(wumpus)){
			if(!hunter.isAlive()){
				System.out.println(DialoguesManager.WITH_WUMPUS_ALIVE);
				System.out.println(DialoguesManager.GAME_OVER);
				stillAlive = false;
				return stillAlive;
			} else {
				System.out.println(DialoguesManager.WITH_WUMPUS_DEAD);
			}
		}
		
		if(hunter.checkHole(board)){
			System.out.println(DialoguesManager.AT_HOLE);
			System.out.println(DialoguesManager.GAME_OVER);
			stillAlive = false;
			return stillAlive;			
		}
		
		if(hunter.checkGold(board)){
			System.out.println(DialoguesManager.AT_GOLD);
		}
		
		if(hunter.checkHoleNear(board)){
			System.out.println(DialoguesManager.HOLE_NEAR);
		}
		
		if(hunter.checkWumpusNear(wumpus)){
			System.out.println(DialoguesManager.WUMPUS_NEAR);
		}
		
		System.out.print(DialoguesManager.LOOKING_AT);
		switch(hunter.getOrientation()){
		case 'N':
			System.out.println(DialoguesManager.NORTH);
			break;
		case 'E':
			System.out.println(DialoguesManager.EAST);
			break;
		case 'S':
			System.out.println(DialoguesManager.SOUTH);
			break;
		case 'W':
			System.out.println(DialoguesManager.WEST);
			break;
		}
		
		return stillAlive;
	}
	
	/**
	 * Reads and execute a player command of the game
	 * @return True if the command execution was successfully or false it isn't
	 */
	private static boolean playerAction(){
		boolean correctCommand = true;
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.print(DialoguesManager.COMMAND_CURSOR);
		String command = sc.nextLine();
		switch(command){
		case DialoguesManager.GO_COMMAND:
			if(hunter.move(board.getSize())){
				System.out.println(DialoguesManager.WALL_HIT);
			}
			break;
		case DialoguesManager.LEFT_COMMAND:
			hunter.rotate('L');
			break;
		case DialoguesManager.RIGHT_COMMAND:
			hunter.rotate('R');
			break;
		case DialoguesManager.BOW_COMMAND:
			arrowCommand();
			break;
		case DialoguesManager.EXIT_COMMAND:
			exitCommand();
			break;
		case DialoguesManager.HELP_COMMAND:
			System.out.println(DialoguesManager.COMMANDS_HELP);
			System.out.println();
			break;
		default:
			System.out.println(DialoguesManager.UNKNOWN_COMMAND);
			System.out.println();
			correctCommand = false;
			break;
		}
		return correctCommand;
	}
	
	/**
	 * Manages the exit of the dungeon command
	 */
	private static void exitCommand(){
		if(hunter.checkExit(board)){
			if(hunter.isGoldTaken()){
				System.out.println(DialoguesManager.AT_EXIT_WITH_GOLD);
				gameOn = false;
			} else {
				System.out.println(DialoguesManager.AT_EXIT_WITHOUT_GOLD);
			}
		} else {
			System.out.println(DialoguesManager.NOT_AT_EXIT);
			System.out.println();
		}
	}
	
	/**
	 * Manages the arrow throw command
	 */
	private static void arrowCommand(){
		if(hunter.getArrowsNumber()>0){
			if(hunter.throwArrow(board.getSize(), wumpus)){
				wumpus.setDead();
				System.out.println(DialoguesManager.WUMPUS_DEAD);
			}
		} else {
			System.out.println(DialoguesManager.ARROWS_INSUFICIENTS);
		}
	}
	
	public static Board getBoard(){
		return board;
	}
	
	public static Wumpus getWumpus() {
		return wumpus;
	}

}
