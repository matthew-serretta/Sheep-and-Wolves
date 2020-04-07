import java.awt.*;
import java.util.Optional;

import bos.RelativeMove;

public abstract class Character implements bos.GamePiece<Cell>{
	Optional<Color> display;
	Cell location;
	
	public Character(Cell location) {
		this.location = location;
		this.display = Optional.empty();
	}
	
	public void paint(Graphics g) {
		if(display.isPresent()) {
			g.setColor(display.get());
			g.fillOval(location.x + location.width/4,  location.y + location.height/4,  location.width/2,  location.height/2);			
		}
	}
	
	public Cell getLocationOf() {
		return location;
	}
	
	public void setLocationOf(Cell c) {
		location = c;
	}
	
	public abstract RelativeMove aiMove(Stage stage);
}
