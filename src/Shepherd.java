import java.io.File;
import java.util.Optional;
import javax.imageio.ImageIO;

public class Shepherd extends Character{
	
	public Shepherd(Cell location, Behaviour behaviour) {
		super(location, behaviour);
        try{
            display = Optional.of(ImageIO.read(new File("shepherd.png")));
        } catch (Exception e){
            display = Optional.empty();
        }
	}
}
