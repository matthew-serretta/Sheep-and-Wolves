import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bos.MoveDown;
import bos.MoveLeft;
import bos.MoveRight;
import bos.MoveUp;
import bos.NoMove;
import bos.MoveRandomly;
import bos.Rabbit;
import bos.RelativeMove;

public class RabbitAdapter extends Character{
	private Rabbit adaptee;
	public List<RelativeMove> nextMoves = new ArrayList<RelativeMove>();
	
	public RabbitAdapter(Cell location) {
		super(location, new StandStill());
		adaptee = new Rabbit();
        display = Optional.of(Color.LIGHT_GRAY);
	}
	
	@Override
	public RelativeMove aiMove() {
		if(nextMoves.size() < 1)
			return new NoMove(Grid.getGrid(), this);
		else {
			nextMoves.remove(0);
			return nextMoves.get(0);
		}
	}
}
