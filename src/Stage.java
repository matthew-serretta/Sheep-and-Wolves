import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import bos.MoveRandomly;
import bos.Rabbit;
import bos.RelativeMove;

import java.time.*;

public class Stage extends KeyObservable {
	private static Stage singleton = null;
	
	protected Grid grid;
	protected Character sheep;
	protected Character shepherd;
	protected Character wolf;
	protected Player player;
	protected RabbitAdapter rabbit;
	public Runnable rabbitMovement;
	private ArrayList<Character> allCharacters;

	private Stage() {
		grid = Grid.getGrid();
		shepherd = new Shepherd(grid.getRandomCell(), new StandStill());
		sheep = new Sheep(grid.getRandomCell(), new MoveTowards(shepherd));
		wolf = new Wolf(grid.getRandomCell(), new MoveTowards(sheep));
		rabbit = new RabbitAdapter(grid.getRandomCell());
		rabbitMovement = new RabbitMovementThread(rabbit);
		player = new Player(grid.getRandomCell());
		this.register(player);

		allCharacters = new ArrayList<Character>();
		allCharacters.add(sheep);
		allCharacters.add(shepherd);
		allCharacters.add(wolf);
		allCharacters.add(rabbit);

	}
	
	public static Stage getStage() {
		synchronized(Stage.class) {
			if (singleton == null) {
				singleton = new Stage();
			}
		}
		return singleton;
	}

	public void update() {
		if (rabbit.nextMoves.size() < 10)
			new Thread(rabbitMovement).start();
		if (!player.inMove()) {
			if (sheep.location == shepherd.location) {
				System.out.println("Sheep is safe!");
				System.exit(0);
			} else if (sheep.location == wolf.location) {
				System.out.println("Sheep is dead!");
				System.exit(1);
			} else if (sheep.location.x == sheep.location.y) {
				sheep.setBehaviour(new StandStill());
				shepherd.setBehaviour(new MoveTowards(sheep));
			}
			allCharacters.forEach((c) -> c.aiMove().perform());			
			player.startMove();
		}
	}

	public void paint(Graphics g, Point mouseLocation) {
		grid.paint(g, mouseLocation);
		sheep.paint(g);
		shepherd.paint(g);
		wolf.paint(g);
		rabbit.paint(g);
		player.paint(g);
	}
}
