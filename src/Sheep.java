import java.awt.*;
import java.util.Optional;
import java.util.List;

import bos.MoveRandomly;
import bos.NoMove;
import bos.RelativeMove;

public class Sheep extends Character{
	
	public Sheep(Cell location, Behaviour behaviour) {
		super(location, behaviour);
		display = Optional.of(Color.WHITE);
	}
}
