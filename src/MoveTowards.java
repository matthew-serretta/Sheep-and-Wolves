import java.util.List;

import bos.NoMove;
import bos.RelativeMove;

//A character with the behaviour MoveTowards will move towards the target when it is their turn
public class MoveTowards implements Behaviour{
	Character target;

	public MoveTowards(Character character) {
		this.target = character;
	}
	
	//returns the next move a character must make
	@Override
	public RelativeMove chooseMove(Character mover) {
		List<RelativeMove> movesToTarget = Grid.getGrid().movesBetween(mover.location, target.location, mover);
		if(movesToTarget.size() == 0)
			return new NoMove(Grid.getGrid(), mover);
		else 
			return movesToTarget.get(0);
	}
}
