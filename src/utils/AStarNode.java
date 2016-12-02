package utils;

import java.util.Comparator;

import wumpus.Cell;

/**
 * Class which expands a board cell to convert it into AStarNode.
 * Implements Comparator interface because AStar needs to use PriorityQueues.
 * @author Pablo
 *
 */
public class AStarNode extends Cell implements Comparator{
	/**
	 * Traveled distance from start node to actual node
	 */
	private int traveledDistance;
	
	/**
	 * f(n) for the actual node
	 */
	private int f;
	
	/**
	 * h(n) for the actual node
	 */
	private int h;
	
	/**
	 * Actual node father
	 */
	private AStarNode father;
	
	/**
	 * Constructs an AStarNode object using two coordinates
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public AStarNode(int x, int y) {
		super(x,y);
		this.traveledDistance = 0;
		this.f = 0;
		this.h = 0;
		this.father = null;		
	}
	
	/**
	 * Constructs an AStarNode object using a Cell object
	 * @param cell Cell class object
	 */
	public AStarNode(Cell cell) {
		super(cell);
		this.traveledDistance = 0;
		this.f = 0;
		this.h = 0;
		this.father = null;		
	}
	
	/**
	 * Method used to compare two nodes into PriorityQueue
	 */
	@Override
	public int compare(Object o1, Object o2) {
		int result = 0;
		AStarNode n1 = (AStarNode)o1;
		AStarNode n2 = (AStarNode)o2;
		
		if(n1.f < n2.f){
			result = -1;
		} else {
			if(n1.f > n2.f){
				result = 1;
			}
		}		
		return result;
	}
	
	/**
	 * Calculates f(n) of current node. f(h) is the distance from start to the actual node plus the heuristic "h(n)" from actual node to goal node
	 * @param n Actual node
	 */
	public void f(AStarNode n){
		this.f = h(n) + traveledDistance;
	}
	
	/**
	 * Calculates the heuristic between two nodes using the Manhattan Distance
	 * @param n A node
	 * @return The heuristic result
	 */
	public int h(AStarNode n){
		int result;
		
		result = 10*(Math.abs(this.getX() - n.getX()) + Math.abs(this.getY() - n.getY()));
		
		return result;
	}
	
	public void setFather(AStarNode father) {
		this.father = father;
	}
	
	public AStarNode getFather() {
		return father;
	}
	
	public void setTraveledDistance(int traveledDistance) {
		this.traveledDistance = traveledDistance;
	}
	
	public int getTraveledDistance() {
		return traveledDistance;
	}

}
