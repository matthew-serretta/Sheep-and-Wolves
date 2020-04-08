import bos.NoMove;
import bos.RelativeMove;

public class StandStill implements Behaviour{

	@Override
	public RelativeMove chooseMove(Character mover) {
		return new NoMove(Grid.getGrid(), mover);
	}

}
