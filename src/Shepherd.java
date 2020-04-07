import java.awt.*;
import java.util.Optional;

import bos.MoveRandomly;
import bos.NoMove;
import bos.RelativeMove;

public class Shepherd extends Character{
	
	public Shepherd(Cell location, Behaviour behaviour) {
		super(location, behaviour);
		display = Optional.of(Color.GREEN);
	}
}
