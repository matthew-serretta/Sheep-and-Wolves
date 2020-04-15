import java.io.File;
import java.util.Optional;
import javax.imageio.ImageIO;

public class Wolf extends Character {

	public Wolf(Cell location, Behaviour behaviour) {
		super(location, behaviour);
		try {
			display = Optional.of(ImageIO.read(new File("wolf.png")));
		} catch (Exception e) {
			display = Optional.empty();
		}
	}
}
