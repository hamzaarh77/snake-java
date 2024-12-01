package utils;

import java.util.ArrayList;


public class FeaturesSnake {



	ArrayList<Position> positions;
	
	private AgentAction lastAction;
	
	ColorSnake colorSnake;
	
	boolean isInvincible;
	boolean isSick;
	
	public FeaturesSnake(ArrayList<Position> positions, AgentAction lastAction, ColorSnake colorSnake, boolean isInvincible, boolean isSick) {
		
		this.positions = positions;
		this.colorSnake = colorSnake;
		this.lastAction = lastAction;
		this.isInvincible = isInvincible;
		this.isSick = isSick;
		
	}
	
	// getters and setters
	public ArrayList<Position> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<Position> positions) {
		this.positions = positions;
	}




	public ColorSnake getColorSnake() {
		return colorSnake;
	}


	public void setColorSnake(ColorSnake colorSnake) {
		this.colorSnake = colorSnake;
	}


	public boolean isInvincible() {
		return isInvincible;
	}


	public void setInvincible(boolean isInvincible) {
		this.isInvincible = isInvincible;
	}


	public boolean isSick() {
		return isSick;
	}


	public void setSick(boolean isSick) {
		this.isSick = isSick;
	}


	public AgentAction getLastAction() {
		return lastAction;
	}


	public void setLastAction(AgentAction lastAction) {
		this.lastAction = lastAction;
	}

	// methods 
	public void addQueu(){
		Position addedPosition;
		Position lastPosition = positions.get(positions.size()-1);
		if(lastAction.equals(AgentAction.MOVE_UP)){
			addedPosition = new Position(lastPosition.getX(), lastPosition.getY()+1);
		}else if(lastAction.equals(AgentAction.MOVE_DOWN)){
			addedPosition = new Position(lastPosition.getX(), lastPosition.getY()-1);
		}else if(lastAction.equals(AgentAction.MOVE_LEFT)){
			addedPosition = new Position(lastPosition.getX()+1, lastPosition.getY());
		}else {
			addedPosition = new Position(lastPosition.getX()-1, lastPosition.getY());
		}
		positions.add(addedPosition);
	}
}
