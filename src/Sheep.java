import java.awt.*;
import java.util.Optional;
import java.util.List;

import bos.MoveRandomly;
import bos.NoMove;
import bos.RelativeMove;

public class Sheep extends Character{
	
	public Sheep(Cell location) {
		super(location);
		display = Optional.of(Color.WHITE);
	}

	@Override
	public RelativeMove aiMove(Stage stage) {
		List<RelativeMove> possibles = stage.grid.movesBetween(this.location, stage.shepherd.location, this);
		if(possibles.size() == 0)
			return new NoMove(stage.grid, this);
		else 
			return possibles.get(0);
	}
}
