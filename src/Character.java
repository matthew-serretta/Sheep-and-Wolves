import java.awt.*;
import java.util.Optional;

import bos.RelativeMove;

//Character class is the parent class to characters such as Sheep, Shepherd and Wolf
public abstract class Character implements bos.GamePiece<Cell>{
	Optional<Image> display;
	Cell location;
	Behaviour behaviour;
	
	public Character(Cell location, Behaviour behaviour) {
		this.location = location;
		this.display = Optional.empty();
		this.behaviour = behaviour;
	}
	
	//paint function is used to 'paint' the image of the character onto the java display
	public void paint(Graphics g) {
		if(display.isPresent()) {
			g.drawImage(display.get(), location.x+2, location.y+2, 31, 31, null, null);
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
	
	//aiMove function is used to return the character's next move based on their behaviour
	public RelativeMove aiMove() {
		return behaviour.chooseMove(this);
	}
}
