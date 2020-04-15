import bos.Rabbit;
import bos.MoveDown;
import bos.MoveLeft;
import bos.MoveRight;
import bos.MoveUp;

// used to create a new thread to determine the rabbit's next move because this takes a long time
// implements the adapter design pattern
public class RabbitMovementThread implements Runnable {
	private RabbitAdapter rabbit;
	private Rabbit adaptee = new Rabbit();

	public RabbitMovementThread(RabbitAdapter rabbit) {
		this.rabbit = rabbit;
	}

	// calls original rabbit class to determine it's next move and adds it to the
	// adapter's list of next moves
	@Override
	public void run() {
		switch (adaptee.nextMove()) {
		case 0:
			rabbit.nextMoves.add(new MoveDown(Grid.getGrid(), rabbit));
		case 1:
			rabbit.nextMoves.add(new MoveUp(Grid.getGrid(), rabbit));
		case 2:
			rabbit.nextMoves.add(new MoveLeft(Grid.getGrid(), rabbit));
		case 3:
			rabbit.nextMoves.add(new MoveRight(Grid.getGrid(), rabbit));
		}
	}
}
