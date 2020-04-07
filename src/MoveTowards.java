import java.util.List;

import bos.NoMove;
import bos.RelativeMove;

public class MoveTowards implements Behaviour{
	Character target;

	public MoveTowards(Character character) {
		this.target = character;
	}
	
	@Override
	public RelativeMove chooseMove(Stage stage, Character mover) {
		List<RelativeMove> movesToTarget = stage.grid.movesBetween(mover.location, target.location, mover);
		if(movesToTarget.size() == 0)
			return new NoMove(stage.grid, mover);
		else 
			return movesToTarget.get(0);
	}
}
