import java.awt.*;
import java.util.Optional;

import bos.MoveRandomly;
import bos.NoMove;
import bos.RelativeMove;

public class Shepherd extends Character{
	
	public Shepherd(Cell location) {
		super(location);
		display = Optional.of(Color.GREEN);
	}

	@Override
	public RelativeMove aiMove(Stage stage) {
		return new NoMove(stage.grid, this);
	}
}
