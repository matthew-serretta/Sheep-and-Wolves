import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import bos.RelativeMove;

import java.time.*;

public class Stage {
	protected Grid grid;
	protected Character sheep;
	protected Character shepherd;
	protected Character wolf;
	
	private Instant timeOfLastMove = Instant.now();
	private java.util.List<RelativeMove> moves;

	public Stage() {
		grid = new Grid();
		sheep = new Sheep(grid.getRandomCell());
		shepherd = new Shepherd(grid.getRandomCell());
		wolf = new Wolf(grid.getRandomCell());

		
	}

	public void update() {	
		if(sheep.location == shepherd.location) {
			System.out.println("Sheep is safe!");
			System.exit(0);
		} else if (sheep.location == shepherd.location) {
			System.out.println("Sheep is dead!");
			System.exit(0);
		} else if (timeOfLastMove.plus(Duration.ofSeconds(2)).isBefore(Instant.now())) {
			timeOfLastMove = Instant.now();
			sheep.aiMove(this).perform();
			wolf.aiMove(this).perform();
			shepherd.aiMove(this).perform();
		} 
	}

	public void paint(Graphics g, Point mouseLocation) {
		grid.paint(g, mouseLocation);
		sheep.paint(g);
		shepherd.paint(g);
		wolf.paint(g);
	}
}
