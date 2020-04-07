import java.awt.*;
import java.util.List;
import java.util.Optional;

import bos.MoveRandomly;
import bos.NoMove;
import bos.RelativeMove;

public class Wolf extends Character{
	
	public Wolf(Cell location, Behaviour behaviour) {
		super(location, behaviour);
		display = Optional.of(Color.RED);
	}
}
