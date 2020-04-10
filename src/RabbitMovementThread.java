import bos.MoveDown;
import bos.MoveLeft;
import bos.MoveRight;
import bos.MoveUp;
import bos.Rabbit;

public class RabbitMovementThread implements Runnable{
	private RabbitAdapter rabbit;
	private Rabbit adaptee = new Rabbit();

	public RabbitMovementThread(RabbitAdapter rabbit) {
		this.rabbit = rabbit;
	}

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
