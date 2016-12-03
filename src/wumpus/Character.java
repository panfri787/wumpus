package wumpus;

public class Character {
	private int xPos;
	private int yPos;
	private boolean alive;
	
	public Character(int x, int y){
		this.xPos = x;
		this.yPos = y;
		this.alive = true;
	}
	
	protected boolean moveNorth() {
		if(getxPos() - 1 >= 0 ){
			setxPos(getxPos()-1);
			return false;
		} else {
			return true;
		}		
	}

	protected boolean moveWest() {
		if(getyPos() - 1 >= 0 ){
			setyPos(getyPos()-1);;
			return false;
		} else {
			return true;
		}	
	}

	protected boolean moveSouth(int mapSize) {
		if(getxPos() + 1 < mapSize ){
			setxPos(getxPos()+1);
			return false;
		} else {
			return true;
		}	
	}

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
