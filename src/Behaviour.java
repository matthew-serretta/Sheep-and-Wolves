import bos.RelativeMove;

// interface to describe the movement type of a character
// e.g. MoveTowards(target), StandStill()
public interface Behaviour {
	public RelativeMove chooseMove(Character mover);
}
