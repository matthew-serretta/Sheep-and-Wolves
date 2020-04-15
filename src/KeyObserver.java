import bos.GameBoard;

//observer design pattern to allow a character to listen to keystrokes (commands)
public interface KeyObserver {
	
	public void notify(char c, GameBoard<Cell> gb);
	
}
