import java.awt.*;

public class Grid {
	
	private static final int MARGIN = 10;
	private static final int WIDTH = 35;
	private static final int HEIGHT = 35;

	private Cell [][] grid = new Cell [20][20];
	
	public Grid() {
		for(int x=0; x<grid.length; x++) {
			for (int y=0; y<grid.length; y++) {
				grid[x][y] = new Cell(MARGIN + x * WIDTH, MARGIN + y * HEIGHT);					
			}
		}
	}
	
	public void paint(Graphics g, Point mousePosition) {
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y< grid.length; y++) {
				grid[x][y].paint(g, grid[x][y].contains(mousePosition));
			}
		}
	}
	
}
