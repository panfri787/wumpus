package wumpus;

/**
 * Class it's defines a cell of the board
 * @author Pablo
 *
 */
public class Cell {
	
	/**
	 * Char which define a cell type following that structure
	 * 'N' is a normal cell
	 * 'E' is the exit it's placed cell
	 * 'G' is the gold it's placed cell 
	 * 'P' is a secured path cell
	 * 'H' is a hole cell
	 */
	private char type;
	/**
	 * Define if one cell is locked (main securely path between exit and gold) or not 
	 */
	private boolean locked;
	/**
	 * X coordinate of the cell in the board
	 */
	private int x;
	/**
	 * Y coordinate of the cell in the board
	 */
	private int y;

	/**
	 * Constructs a regular Cell defining its coordinates
	 * @param x X coordinate of the cell in the board
	 * @param y Y coordinate of the cell in the board
	 */
	public Cell(int x, int y) {
		this.locked = false;
		this.type = 'N';
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructs one Cell copying another one
	 * @param other A Cell object
	 */
	public Cell(Cell other){
		this.locked = other.locked;
		this.x = other.x;
		this.y = other.y;
		this.type = other.type;
	}
	
	public void setType(char type) {
		this.type = type;
	}
	
	public char getType() {
		return type;
	}
	
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	

}
