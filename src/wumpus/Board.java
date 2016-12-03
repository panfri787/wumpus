package wumpus;

import java.util.Random;

import utils.AStarCalculator;

/**
 * Class it's define the board of the game
 * @author Pablo
 *
 */
public class Board {
	
	/**
	 * Size of the board 
	 */
	private int size;
	
	/**
	 * Matrix of cells which compose the board
	 */
	private Cell[][] cells;
	
	/**
	 * The exit cell of the game
	 */
	private Cell exitCell;
	
	/**
	 * The gold location cell
	 */
	private Cell goldCell;
	
	private int holesNumber;
	
	private int lockedCells;
	
	/**
	 * Construct a default board with all cells as default cells.
	 */
	public Board(int size, int holesNumber) {
		this.exitCell = null;
		this.goldCell = null;
		this.holesNumber = holesNumber;
		this.cells = new Cell[size][size];
		this.size = size;
		this.lockedCells = 0;
		
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				this.cells[i][j] = new Cell(i,j);
			}			
		}
	}
	
	/**
	 * Obtains a single cell of the board defining it's coordinates. 
	 * @param i x coordinate in the board of the cell.
	 * @param j y coordinate in the board of the cell.
	 * @return The selected cell.
	 */
	public Cell getCell(int i, int j){
		
		Cell selected = null;
		
		if((i >= 0 && i < this.size) && (j >= 0 && j < this.size)){
			selected = this.cells[i][j];
		}
		
		return selected;
	}
	
	/**
	* Initialize all board special cells if it's possible because the hole number fits into current free cells of the board.
	* @return Boolean which says if it's possible fit that number of holes or not.
	*/
	public boolean initializeBoard(){
		boolean possible = true;
		this.placeExitCell();
		this.placeGoldCell();
		//That following two instructions make a secured path between exit and gold
		AStarCalculator pathGenerator = new AStarCalculator(this);
		pathGenerator.calculate();
		possible = this.placeHoles();
		
		return possible;
	}
	
	/**
	 * Place all the holes around the board. If there are more holes than free cells will return false.
	 * @return Boolean which says if it's possible fit that number of holes or not.
	 */
	private boolean placeHoles() {
		Random randomGenerator = new Random();
		Cell selectedCell = null;
		boolean holesFitInBoard = false;
		int holesToPlace = holesNumber;
		
		if (holesNumber <= (size*size)-lockedCells) {
			holesFitInBoard = true;
			while (holesToPlace > 0) {
				do {
					int x = randomGenerator.nextInt(this.size);
					int y = randomGenerator.nextInt(this.size);
					selectedCell = this.getCell(x, y);

					if (selectedCell != null && !selectedCell.isLocked()) {
						selectedCell.setLocked(true);
						this.lockedCells++;
						holesToPlace--;
						selectedCell.setType('H');
					}
				} while (selectedCell == null || selectedCell.getType() != 'H');
			} 
		}
		return holesFitInBoard;
	}

	/**
	 * Places the exit cell randomly around the board.
	 */
	private void placeExitCell(){
		Random randomGenerator = new Random();
		Cell selectedCell;

		do {
			int x = randomGenerator.nextInt(this.size);
			int y = randomGenerator.nextInt(this.size);
			selectedCell = this.getCell(x, y);
			
			if (selectedCell != null && !selectedCell.isLocked()) {
				selectedCell.setLocked(true);
				this.lockedCells++;
				selectedCell.setType('E');
				this.exitCell = selectedCell;
			}			
		} while (selectedCell == null || !selectedCell.isLocked());
	}
	
	/**
	 * Places the gold container cell randomly around the board.
	 */
	private void placeGoldCell(){
		Random randomGenerator = new Random();
		Cell selectedCell;

		do {
			int x = randomGenerator.nextInt(this.size);
			int y = randomGenerator.nextInt(this.size);
			selectedCell = this.getCell(x, y);
			
			if ((selectedCell != null) && (selectedCell.getType() != 'E')) {
				selectedCell.setLocked(true);
				this.lockedCells++;
				selectedCell.setType('G');
				this.goldCell = selectedCell;
			}			
		} while (this.goldCell == null);
	}
	
	/**
	 * Prints by console the board
	 */
	public void printBoard(){
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				System.out.print(this.getCell(i, j).getType() + " ");
			}
			System.out.println();
		}
	}
	
	public Cell getExitCell() {
		return exitCell;
	}
	
	public Cell getGoldCell() {
		return goldCell;
	}
	
	public int getLockedCells() {
		return lockedCells;
	}
	
	public void setLockedCells(int lockedCells) {
		this.lockedCells = lockedCells;
	}
	
	public int getSize() {
		return size;
	}

}
