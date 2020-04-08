import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import bos.RelativeMove;

import java.time.*;

public class Stage extends KeyObservable {
	protected Grid grid;
	protected Character sheep;
	protected Character shepherd;
	protected Character wolf;
	protected Player player;
	private ArrayList<Character> allCharacters;

	private Instant timeOfLastMove = Instant.now();

	public Stage() {
		grid = new Grid();
		shepherd = new Shepherd(grid.getRandomCell(), new StandStill());
		sheep = new Sheep(grid.getRandomCell(), new MoveTowards(shepherd));
		wolf = new Wolf(grid.getRandomCell(), new MoveTowards(sheep));

		player = new Player(grid.getRandomCell());
		this.register(player);

		allCharacters = new ArrayList<Character>();
		allCharacters.add(sheep);
		allCharacters.add(shepherd);
		allCharacters.add(wolf);

	}

	public void update() {
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
			allCharacters.forEach((c) -> c.aiMove(this).perform());
			player.startMove();
			timeOfLastMove = Instant.now();
		}
	}

	public void paint(Graphics g, Point mouseLocation) {
		grid.paint(g, mouseLocation);
		sheep.paint(g);
		shepherd.paint(g);
		wolf.paint(g);
		player.paint(g);
	}
}
