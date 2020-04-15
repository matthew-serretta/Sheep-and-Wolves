import java.awt.*;
import java.util.Optional;

import bos.NoMove;
import bos.RelativeMove;

//Character class is the parent class to characters such as Sheep, Shepherd and Wolf
public abstract class Character implements bos.GamePiece<Cell>{
	Optional<Image> display;
	Cell location;
	Behaviour behaviour;
	Boolean isAlive;
	
	public Character(Cell location, Behaviour behaviour) {
		this.location = location;
		this.display = Optional.empty();
		this.behaviour = behaviour;
		this.isAlive = true;
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
	
	public void die() {
		this.isAlive = false;
	}
	
	//aiMove function is used to return the character's next move based on their behaviour
	public RelativeMove aiMove() {
		if (behaviour == null)
			return new NoMove(Grid.getGrid(), this);
		else
			return behaviour.chooseMove(this);
	}
}
