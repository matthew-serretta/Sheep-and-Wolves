import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Stage {
	private Grid grid;
	private Character sheep;
	private Character shepherd;
	private Character wolf;

	private List<bos.RelativeMove> moves;

	private Instant timeOfLastMove;

	public Stage() {
		grid = new Grid();
		sheep = new Sheep(grid.getRandomCell());
		shepherd = new Shepherd(grid.getRandomCell());
		wolf = new Wolf(grid.getRandomCell());

		moves = new ArrayList<bos.RelativeMove>();
		moves.add(new bos.MoveUp(grid, sheep));
		moves.add(new bos.MoveUp(grid, sheep));
		moves.add(new bos.MoveUp(grid, sheep));

		timeOfLastMove = Instant.now();
	}

	public void update() {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (moves.size() > 0 && timeOfLastMove.plus(Duration.ofSeconds(2)).isBefore(Instant.now())) {
			timeOfLastMove = Instant.now();
			moves.remove(0).perform();

		} else if (moves.size() == 0 && timeOfLastMove.plus(Duration.ofSeconds(20)).isBefore(Instant.now())) {
			System.exit(0);
		}
	}

	public void paint(Graphics g, Point mouseLocation) {
		grid.paint(g, mouseLocation);
		sheep.paint(g);
		shepherd.paint(g);
		wolf.paint(g);
	}
}
