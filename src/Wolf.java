import java.awt.*;
import java.util.List;
import java.util.Optional;

import bos.MoveRandomly;
import bos.NoMove;
import bos.RelativeMove;

public class Wolf extends Character{
	
	public Wolf(Cell location) {
		super(location);
		display = Optional.of(Color.RED);
	}

	@Override
	public RelativeMove aiMove(Stage stage) {
		List<RelativeMove> possibles = stage.grid.movesBetween(this.location, stage.sheep.location, this);
		if(possibles.size() == 0)
			return new NoMove(stage.grid, this);
		else 
			return possibles.get(0);
	}
}
