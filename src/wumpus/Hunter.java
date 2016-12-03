package wumpus;

public class Hunter extends Character {
	
	
	private char orientation;
	private boolean gotGold;
	
	public Hunter(Cell startPosition){
		super(startPosition.getX(), startPosition.getY());
		this.orientation = 'E';
		this.gotGold = false;
	}
	
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
	
	public boolean checkGold(Board board){
		boolean gotGold = false;
		if(board.getCell(getxPos(), getyPos()).getType() == 'G'){
			gotGold = true;
			this.gotGold = true;
		}
		
		return gotGold;
	}
	
	public boolean checkExit(Board board){
		boolean atExit = false;
		if(board.getCell(getxPos(), getyPos()).getType() == 'E'){
			atExit = true;
		}
		
		return atExit;
	}
	
	public boolean checkHole(Board board){
		boolean fallDown = false;
		if(board.getCell(getxPos(), getyPos()).getType() == 'H'){
			fallDown = true;
			this.setDead();
		}
		
		return fallDown;
	}
	
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
	
	public boolean checkWumpusNear(Character c){
		boolean wumpusNear = false;
		if(!wumpusNear && getxPos()-1 == c.getxPos()){
			wumpusNear = true;
		}
		
		if(!wumpusNear && getxPos()+1 == c.getxPos()){
			wumpusNear = true;
		}
		
		if(!wumpusNear && getyPos()-1 == c.getyPos()){
			wumpusNear = true;
		}
		
		if(!wumpusNear && getyPos()+1 == c.getyPos()){
			wumpusNear = true;
		}
			
		return wumpusNear;
	}
	
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

}
