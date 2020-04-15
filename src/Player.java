import java.awt.*;
import java.io.File;
import java.util.Optional;
import javax.imageio.ImageIO;

import bos.GameBoard;

// player class serves no purpose other than to allow a player to 'interact with the game'
// this is to allow demonstration of the observer design pattern
public class Player extends Character implements KeyObserver {
	private Boolean inMove;

	public Player(Cell location) {
		super(location, null);
		inMove = false;
		try {
			display = Optional.of(ImageIO.read(new File("shepherdHook.png")));
		} catch (Exception e) {
			display = Optional.empty();
		}
	}

	// paint function is used to 'paint' the image of the player onto the java
	// display
	public void paint(Graphics g) {
		if (display.isPresent()) {
			g.drawImage(display.get(), location.x + 2, location.y + 2, 31, 31, null, null);
		}
	}

	public void startMove() {
		inMove = true;
	}

	public Boolean inMove() {
		return inMove;
	}

	// when player is notified that a movement key (w,a,s,d) is pressed, move them
	// accordingly
	@Override
	public void notify(char c, GameBoard<Cell> gb) {
		if (inMove) {
			if (c == 'a') {
				location = gb.leftOf(location).orElse(location);
				inMove = false;
			}
			if (c == 'd') {
				location = gb.rightOf(location).orElse(location);
				inMove = false;
			}
			if (c == 'w') {
				location = gb.above(location).orElse(location);
				inMove = false;
			}
			if (c == 's') {
				location = gb.below(location).orElse(location);
				inMove = false;
			}
		}
	}
}
