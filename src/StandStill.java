import bos.NoMove;
import bos.RelativeMove;

// A character with the behaviour StandStill will make no move when it is their turn
public class StandStill implements Behaviour {

	// don't move!
	@Override
	public RelativeMove chooseMove(Character mover) {
		return new NoMove(Grid.getGrid(), mover);
	}

}
