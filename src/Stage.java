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
	private ArrayList<Character> allCharacters;

	public Stage() {
		grid = new Grid();
		shepherd = new Shepherd(grid.getRandomCell(), new StandStill());
		sheep = new Sheep(grid.getRandomCell(), new MoveTowards(shepherd));
		wolf = new Wolf(grid.getRandomCell(), new MoveTowards(sheep));	
		
		allCharacters = new ArrayList<Character>();
		allCharacters.add(sheep); allCharacters.add(shepherd); allCharacters.add(wolf); 
		
	}

	public void update() {	
		if (timeOfLastMove.plus(Duration.ofSeconds(1)).isBefore(Instant.now())) {
			if (sheep.location == shepherd.location) {
				System.out.println("Sheep is safe!");
				System.exit(0);
			} else if (sheep.location == wolf.location) {
				System.out.println("Sheep is dead!");
				System.exit(0);
			} else if (sheep.location.x == sheep.location.y) {
				sheep.setBehaviour(new StandStill());
				shepherd.setBehaviour(new MoveTowards(sheep));
			}
			allCharacters.forEach((c) -> c.aiMove(this).perform());
			timeOfLastMove = Instant.now();
		} 
	}

	public void paint(Graphics g, Point mouseLocation) {
		grid.paint(g, mouseLocation);
		sheep.paint(g);
		shepherd.paint(g);
		wolf.paint(g);
	}
}
