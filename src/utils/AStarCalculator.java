package utils;

import java.util.ArrayList;
import java.util.PriorityQueue;

import wumpus.Board;
import wumpus.Cell;

/**
 * Helper class to make a secured path between exit and gold using AStar Algorithm
 * @author Pablo
 *
 */
public class AStarCalculator {
	/**
	 * Copy of current board status
	 */
	private Board board;
	/**
	 * Array with path result cells 
	 */
	private ArrayList<Cell> path;

	/**
	 * Construct an object with an existing board
	 * @param board
	 */
	public AStarCalculator(Board board) {
		this.board = board;
		this.path = new ArrayList<Cell>();
	}
	
	/**
	 * Calculates the AStar for the current board
	 */
	public void calculate(){
		AStarNode start = new AStarNode(board.getExitCell());
		AStarNode goal = new AStarNode(board.getGoldCell());
		ArrayList<AStarNode> children = new ArrayList<AStarNode>();
		PriorityQueue<AStarNode> borderList = new PriorityQueue<AStarNode>(10, start);
		
		borderList.add(start);
		ArrayList<AStarNode> interiorList = new ArrayList<AStarNode>();
		while (!borderList.isEmpty()) {
			AStarNode n = borderList.poll();
			interiorList.add(n);
			if(n.getX()==goal.getX() && n.getY() == goal.getY()){
				this.buildPath(interiorList, n);
				return;
			}
			expand(n, children);
			for(AStarNode m : children ){
				if(!interiorList.contains(m)){
					m.setTraveledDistance(n.getTraveledDistance()+1);
					if (!borderList.contains(m)) {
						m.f(goal);
						m.setFather(n);
						borderList.add(m);
					} else {
						PriorityQueue<AStarNode> aux = new PriorityQueue<AStarNode>(10, start);
						while(!m.equals(borderList.peek())){
							aux.add(borderList.poll());
						}
						if(m.getTraveledDistance() <= borderList.peek().getTraveledDistance()){
							m.f(goal);
							m.setFather(n);
							borderList.poll();
							borderList.add(m);
							while(!aux.isEmpty()){
								borderList.add(aux.poll());
							}
						}
					}
				} 
			}
		}
		
	}

	/**
	 * AStar helper methods which expand the path to the near cells
	 * @param n actual path node
	 * @param children List to fill all possible child nodes
	 */
	private void expand(AStarNode n, ArrayList<AStarNode> children) {
		children.clear();
		
		if(board.getCell(n.getX()-1, n.getY()) != null){
			AStarNode top = new AStarNode(n.getX()-1, n.getY());
			children.add(top);
		}
		
		if(board.getCell(n.getX(), n.getY()-1) != null){
			AStarNode left = new AStarNode(n.getX(), n.getY()-1);
			children.add(left);
		}
		
		if(board.getCell(n.getX()+1, n.getY()) != null){
			AStarNode down = new AStarNode(n.getX()+1, n.getY());
			children.add(down);
		}
		
		if(board.getCell(n.getX(), n.getY()+1) != null){
			AStarNode right = new AStarNode(n.getX(), n.getY()+1);
			children.add(right);
		}
	}
	
	/**
	 * Construct the result path switching the types of the cells
	 * @param interiorList List with all result path nodes
	 * @param n Last node in the path
	 */
	private void buildPath(ArrayList<AStarNode> interiorList, AStarNode n) {
		while(n.getFather()!=null){
			Cell cell = board.getCell(n.getX(), n.getY());
			if(cell.getType() != 'G'){
				cell.setType('P');
			}			
			cell.setLocked(true);
			board.setLockedCells(board.getLockedCells()+1);
			path.add(cell);
			n = n.getFather();
		}		
	}
	
	public ArrayList<Cell> getPath() {
		return path;
	}
}
