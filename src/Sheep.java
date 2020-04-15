import java.io.File;
import java.util.Optional;
import javax.imageio.ImageIO;

public class Sheep extends Character {

	public Sheep(Cell location, Behaviour behaviour) {
		super(location, behaviour);
		try {
			display = Optional.of(ImageIO.read(new File("sheep.png")));
		} catch (Exception e) {
			display = Optional.empty();
		}
	}
}
