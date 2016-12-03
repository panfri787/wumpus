package wumpus;

/**
 * Class which defines the Hunter character of the game
 * @author Pablo
 *
 */
public class Hunter extends Character {	
	
	/**
	 * Says where is looking the hunter
	 */
	private char orientation;
	
	/**
	 * Says if hunter has got the gold or not
	 */
	private boolean gotGold;
	
	/**
	 * Says how many arrows has the hunter
	 */
	private int arrows;
	
	/**
	 * Construct a Hunter object giving the Start/Exit cell of the board and the initial number of arrows thats hunter has.
	 * @param startPosition Start/Exit cell in the board
	 * @param arrows Number of arrows which the hunter start the game
	 */
	public Hunter(Cell startPosition, int arrows){
		super(startPosition.getX(), startPosition.getY());
		this.orientation = 'E';
		this.gotGold = false;
		this.arrows = arrows;
	}
	
	/**
	 * Changes the orientation of the hunter, according the turn direction
	 * @param direction Turn direction
	 */
	public void rotate(char direction){
		if(direction == 'L'){
			switch(this.orientation){
			case 'N':
				this.orientation = 'W';
				break;
			case 'E':
				this.orientation = 'N';
				break;				
			case 'S':
				this.orientation = 'E';
				break;
			case 'W':
				this.orientation = 'S';
				break;
			}
		} else {
			switch(this.orientation){
			case 'N':
				this.orientation = 'E';
				break;
			case 'E':
				this.orientation = 'S';
				break;				
			case 'S':
				this.orientation = 'W';
				break;
			case 'W':
				this.orientation = 'N';
				break;
			}
		}
	}
	
	/**
	 * Check if the hunter it's on the gold cell
	 * @param board Current board of the game
	 * @return True if the hunter it's on the gold cell or false it isn't
	 */
	public boolean checkGold(Board board){
		boolean gotGold = false;
		if(board.getCell(getxPos(), getyPos()).getType() == 'G'){
			gotGold = true;
			this.gotGold = true;
		}
		
		return gotGold;
	}
	
	/**
	 * Check if the hunter it's on the exit cell
	 * @param board Current board of the game
	 * @return True if the hunter it's on the exit cell or false it isn't
	 */
	public boolean checkExit(Board board){
		boolean atExit = false;
		if(board.getCell(getxPos(), getyPos()).getType() == 'E'){
			atExit = true;
		}
		
		return atExit;
	}
	
	/**
	 * Check if the hunter it's on a hole cell
	 * @param board Current board of the game
	 * @return True if the hunter it's on a hole or false it isn't
	 */
	public boolean checkHole(Board board){
		boolean fallDown = false;
		if(board.getCell(getxPos(), getyPos()).getType() == 'H'){
			fallDown = true;
			this.setDead();
		}
		
		return fallDown;
	}
	
	/**
	 * Check if the hunter it's next to hole
	 * @param board Current board of the game
	 * @return True if the hunter it's next to hole or false it isn't
	 */
	public boolean checkHoleNear(Board board){
		boolean holeNear = false;
		Cell north = board.getCell(getxPos()-1, getyPos());
		Cell south = board.getCell(getxPos()+1, getyPos());
		Cell west = board.getCell(getxPos(), getyPos()-1);
		Cell east = board.getCell(getxPos(), getyPos()+1);
		
		if(north != null && !holeNear && north.getType() == 'H'){
			holeNear = true;
		}
		if(south != null && !holeNear && south.getType() == 'H'){
			holeNear = true;
		}
		if(west != null && !holeNear && west.getType() == 'H'){
			holeNear = true;
		}
		if(east != null && !holeNear && east.getType() == 'H'){
			holeNear = true;
		}
		
		return holeNear;
	}
	
	/**
	 * Check if the hunter it's on the same cell like the Wumpus
	 * @param c Wumpus object
	 * @return True if the hunter it's on the same cell like the Wumpus or false it isn't
	 */
	public boolean checkWumpus(Character c){
		boolean sameCell = false;
		if(getxPos() == c.getxPos() && getyPos() == c.getyPos()){
			sameCell = true;
			if(c.isAlive()){
				this.setDead();
			}
		}
		
		return sameCell; 
	}
	
	/**
	 * Check if the hunter it's next to the Wumpus
	 * @param c Wumpus object
	 * @return True if the hunter it's next to the Wumpus or false it isn't
	 */
	public boolean checkWumpusNear(Character c){
		boolean wumpusNear = false;
		if(!wumpusNear && getxPos()-1 == c.getxPos() && getyPos() == c.getyPos()){
			wumpusNear = true;
		}
		
		if(!wumpusNear && getxPos()+1 == c.getxPos() && getyPos() == c.getyPos()){
			wumpusNear = true;
		}
		
		if(!wumpusNear && getyPos()-1 == c.getyPos() && getxPos() == c.getxPos()){
			wumpusNear = true;
		}
		
		if(!wumpusNear && getyPos()+1 == c.getyPos() && getxPos() == c.getxPos()){
			wumpusNear = true;
		}
			
		return wumpusNear;
	}
	
	/**
	 * Moves one cell the hunter according it orientation
	 * @param mapSize Current size of the map
	 * @return True if hunter hits the wall of the map or false it isn't
	 */
	public boolean move(int mapSize){
		boolean wallHit = false;
		switch(this.orientation){
		case 'N':
			wallHit = moveNorth();
			break;
		case 'E':
			wallHit = moveEast(mapSize);
			break;				
		case 'S':
			wallHit = moveSouth(mapSize);
			break;
		case 'W':
			wallHit = moveWest();
			break;
		}
		
		return wallHit;
	}
	
	/**
	 * Throws an arrow according the orientation of the hunter
	 * @param mapSize Used to set the arrow path
	 * @param c Wumpus object to get its position
	 * @return True if the Wumpus is hitted by the arrow or false if it isn't
	 */
	public boolean throwArrow(int mapSize, Character c){
		boolean hitWumpus = false;
		
		if(this.arrows > 0){
			switch(this.orientation){
			case 'N':
				hitWumpus = throwArrowToNorth(mapSize, c);
				break;
			case 'E':
				hitWumpus = throwArrowToEast(mapSize, c);
				break;
			case 'S':
				hitWumpus = throwArrowToSouth(mapSize, c);
				break;
			case 'W':
				hitWumpus = throwArrowToWest(mapSize, c);
				break;
			}
			this.arrows--;
		}
		
		return hitWumpus;
	}	
	
	/**
	 * Throw the arrow to the north of the hunter
	 * @param mapSize Current size of the map
	 * @param c Wumpus object
	 * @return True if the wumpus is hitted or false if it isn't
	 */
	private boolean throwArrowToNorth(int mapSize, Character c) {
		boolean hit = false;
		for(int i = getxPos(); i >= 0; i--){
			if(i == c.getxPos() && getyPos() == c.getyPos()){
				hit = true;
			}
		}
		return hit;
	}
	
	/**
	 * Throw the arrow to the south of the hunter
	 * @param mapSize Current size of the map
	 * @param c Wumpus object
	 * @return True if the wumpus is hitted or false if it isn't
	 */
	private boolean throwArrowToSouth(int mapSize, Character c) {
		boolean hit = false;
		for(int i = getxPos(); i < mapSize; i++){
			if(i == c.getxPos() && getyPos() == c.getyPos()){
				hit = true;
			}
		}
		return hit;
	}
	
	/**
	 * Throw the arrow to the east of the hunter
	 * @param mapSize Current size of the map
	 * @param c Wumpus object
	 * @return True if the wumpus is hitted or false if it isn't
	 */
	private boolean throwArrowToEast(int mapSize, Character c) {
		boolean hit = false;
		for(int i = getyPos(); i < mapSize; i++){
			if(i == c.getyPos() && getxPos() == c.getxPos()){
				hit = true;
			}
		}
		return hit;
	}
	
	/**
	 * Throw the arrow to the west of the hunter
	 * @param mapSize Current size of the map
	 * @param c Wumpus object
	 * @return True if the wumpus is hitted or false if it isn't
	 */
	private boolean throwArrowToWest(int mapSize, Character c) {
		boolean hit = false;
		for(int i = getyPos(); i >= 0; i--){
			if(i == c.getyPos() && getxPos() == c.getxPos()){
				hit = true;
			}
		}
		return hit;
	}

	public boolean isGoldTaken(){
		return this.gotGold;
	}
	
	public char getOrientation() {
		return orientation;
	}
	
	public int getArrowsNumber() {
		return arrows;
	}

}
