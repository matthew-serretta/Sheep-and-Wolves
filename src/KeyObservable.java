import java.util.ArrayList;

import bos.GameBoard;

// observer design pattern to allow a character to listen to keystrokes (commands)
public class KeyObservable extends ArrayList<KeyObserver> {

	public void register(KeyObserver ko) {
		add(ko);
	}

	public void notifyAll(char c, GameBoard<Cell> gb) {
		this.forEach((KeyObserver ko) -> ko.notify(c, gb));
	}
}
