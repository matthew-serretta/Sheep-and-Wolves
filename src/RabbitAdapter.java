import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.imageio.ImageIO;

import bos.NoMove;
import bos.RelativeMove;

//the rabbit character serves no purpose in game, it is only used to display the adapter design patter and use of threads
//adapter work is done in the RabbitMovementThread class
//the rabbit class is intentionally poorly designed to make movements take longer, resulting in poor response time and therefore the need for threads
public class RabbitAdapter extends Character{
	
	//due to the time it takes to determine the rabbit's next move, we store them here in advance
	public List<RelativeMove> nextMoves = new ArrayList<RelativeMove>();
	
	public RabbitAdapter(Cell location) {
		super(location, new StandStill());
		try{
            display = Optional.of(ImageIO.read(new File("rabbit.png")));
        } catch (Exception e){
            display = Optional.empty();
        }
	}

	//if available, return the next move the rabbit should make
	@Override
	public RelativeMove aiMove() {
		if(nextMoves.size() < 1)
			return new NoMove(Grid.getGrid(), this);

		else {
			RelativeMove move = nextMoves.get(0);
			nextMoves.remove(0);
			return move;
		}
	}
}
