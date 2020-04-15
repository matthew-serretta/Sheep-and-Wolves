import java.awt.*;
import java.util.ArrayList;

//stage class 'set's the stage' by containing the game board/grid and characters
public class Stage extends KeyObservable {
	private static Stage singleton = null;
	
	protected Grid grid;
	protected Character sheep;
	protected Character wolf;
	protected Player player;
	protected RabbitAdapter rabbit;
	public Runnable rabbitMovement;
	private ArrayList<Character> aiCharacters;

	private Stage() {
		grid = Grid.getGrid();
		player = new Player(grid.getRandomCell());
		this.register(player);
		sheep = new Sheep(grid.getRandomCell(), new MoveTowards(player));
		wolf = new Wolf(grid.getRandomCell(), new MoveTowards(sheep));
		rabbit = new RabbitAdapter(grid.getRandomCell());
		rabbitMovement = new RabbitMovementThread(rabbit);

		aiCharacters = new ArrayList<Character>();
		aiCharacters.add(sheep);
		aiCharacters.add(wolf);
		aiCharacters.add(rabbit);

	}
	
	//Singleton design pattern to ensure there is only one stage
	public static Stage getStage() {
		synchronized(Stage.class) {
			if (singleton == null) {
				singleton = new Stage();
			}
		}
		return singleton;
	}

	//update the stage of any changes after each character makes their move
	public void update() {
		
		//if the rabbit hasn't calculated enough moves, 
		//start making threads to make sure there are moves available
		if (rabbit.nextMoves.size() < 10)
			new Thread(rabbitMovement).start();
		
		if (!player.inMove()) {
			//if the sheep is safe, end the game
			if (sheep.location == player.location) {
				System.out.println("Sheep is safe!");
				System.exit(0);
			} 
			
			//if the sheep is dead, end the game
			else if (sheep.location == wolf.location) {
				System.out.println("Sheep is dead!");
				System.exit(1);
			} 
			
			//if the sheep is on the diagonal from top left to bottom right, stop it from moving
			//this serves no in game purpose, only there to demonstrate behaviour usability
			if (sheep.location.x == sheep.location.y) {
				sheep.setBehaviour(new StandStill());
			}
			
			if (rabbit.isAlive) {
				//if the wolf is within 5 moves from the rabbit, it will chase the rabbit
				if (Grid.getGrid().movesBetween(wolf.location, rabbit.location, wolf).size() < 5) {
					wolf.setBehaviour(new MoveTowards(rabbit));
				}
				
				//if wolf catches the rabbit...
				if (wolf.location == rabbit.location) {
					aiCharacters.remove(rabbit);
					rabbit.die();
					System.out.println("Wolf has eaten the Rabbit!");
					wolf.setBehaviour(new MoveTowards(sheep));
				}	
			}
			
			//perform character moves
			aiCharacters.forEach((c) -> c.aiMove().perform());			
			player.startMove();
		}
	}

	//paint function is used to 'paint' the image of the stage onto the java display
	public void paint(Graphics g, Point mouseLocation) {
		grid.paint(g, mouseLocation);
		aiCharacters.forEach((c) -> c.paint(g));
		player.paint(g);
	}
}
