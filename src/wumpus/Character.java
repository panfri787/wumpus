package wumpus;

/**
 * Defines a character of the game
 * @author Pablo
 *
 */
public class Character {
	/**
	 * X coordinate of the character
	 */
	private int xPos;
	/**
	 * Y coordinate of the character
	 */
	private int yPos;
	/**
	 * Character is alive or not
	 */
	private boolean alive;
	
	/**
	 * A default Character is alive but isn't placed on the board
	 */
	public Character(){
		this.alive = true;
	}
	
	/**
	 * Construct a Character placing it on the board using the coordinates taken by parameters
	 * @param x X coordinate of the character
	 * @param y Y coordinate of the character
	 */
	public Character(int x, int y){
		this.xPos = x;
		this.yPos = y;
		this.alive = true;
	}
	
	/**
	 * Moves the Character one cell to the north of the board
	 * @return Boolean which says if character was trying to move outside of the board or not
	 */
	protected boolean moveNorth() {
		if(getxPos() - 1 >= 0 ){
			setxPos(getxPos()-1);
			return false;
		} else {
			return true;
		}		
	}

	/**
	 * Moves the Character one cell to the west of the board
	 * @return Boolean which says if character was trying to move outside of the board or not
	 */
	protected boolean moveWest() {
		if(getyPos() - 1 >= 0 ){
			setyPos(getyPos()-1);;
			return false;
		} else {
			return true;
		}	
	}

	/**
	 * Moves the Character one cell to the south of the board
	 * @return Boolean which says if character was trying to move outside of the board or not
	 */
	protected boolean moveSouth(int mapSize) {
		if(getxPos() + 1 < mapSize ){
			setxPos(getxPos()+1);
			return false;
		} else {
			return true;
		}	
	}

	/**
	 * Moves the Character one cell to the east of the board
	 * @return Boolean which says if character was trying to move outside of the board or not
	 */
	protected boolean moveEast(int mapSize) {
		if(getyPos() + 1 < mapSize ){
			setyPos(getyPos()+1);
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isAlive(){
		return this.alive;
	}
	
	public void setDead(){
		this.alive = false;
	}
	
	public int getxPos() {
		return xPos;
	}
	
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	
	public int getyPos() {
		return yPos;
	}
	
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
}
