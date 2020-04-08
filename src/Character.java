import java.awt.*;
import java.util.Optional;

import bos.RelativeMove;

public abstract class Character implements bos.GamePiece<Cell>{
	Optional<Color> display;
	Cell location;
	Behaviour behaviour;
	
	public Character(Cell location, Behaviour behaviour) {
		this.location = location;
		this.display = Optional.empty();
		this.behaviour = behaviour;
	}
	
	public void paint(Graphics g) {
		if(display.isPresent()) {
			g.setColor(display.get());
			g.fillOval(location.x + location.width/4,  location.y + location.height/4,  location.width/2,  location.height/2);			
		}
	}
	
	public Cell getLocationOf() {
		return this.location;
	}
	
	public void setLocationOf(Cell c) {
		this.location = c;
	}
	
	public void setBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
	}
	
	public RelativeMove aiMove() {
		return behaviour.chooseMove(this);
	}
}
