import java.awt.Color;
import java.util.Optional;

import bos.MoveDown;
import bos.MoveLeft;
import bos.MoveRight;
import bos.MoveUp;
import bos.MoveRandomly;
import bos.Rabbit;
import bos.RelativeMove;

public class RabbitAdapter extends Character{
	private Rabbit adaptee;
	
	public RabbitAdapter(Cell location, Behaviour behaviour) {
		super(location, behaviour);
		adaptee = new Rabbit();
        display = Optional.of(Color.LIGHT_GRAY);
	}
	
	@Override
	public RelativeMove aiMove() {
		RelativeMove nextMove = new MoveRandomly(Grid.getGrid(), this); 
		switch (adaptee.nextMove()) {
		case 0:
			nextMove = new MoveDown(Grid.getGrid(), this);
		case 1:
			nextMove = new MoveUp(Grid.getGrid(), this);
		case 2:
			nextMove = new MoveLeft(Grid.getGrid(), this);
		case 3:
			nextMove = new MoveRight(Grid.getGrid(), this);
		}
		return nextMove;
	}
}
