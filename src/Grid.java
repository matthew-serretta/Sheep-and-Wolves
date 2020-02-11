import java.awt.*;

public class Grid {

	private Cell [][] cells = new Cell [20][20];
	
	private static final int MARGIN = 10;
	private static final int WIDTH = 35;
	private static final int HEIGHT = 35;
	
	private int x;
	private int y;
	
	public Grid() {
		for(int i=0; i<cells.length; i++) {
			for (int j=0; j<cells.length; j++) {
				cells[i][j] = new Cell(MARGIN + j * HEIGHT, MARGIN + i * WIDTH);					
			}
		}
	}
	
	public void paint(Graphics g, Point mousePosition) {
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y< cells.length; y++) {
				Cell thisCell = cells[x][y];
				thisCell.paint(g, thisCell.contains(mousePosition));
			}
		}
	}
	
	public Cell getRandomCell() {
		java.util.Random rand = new java.util.Random();
		return cells[rand.nextInt(cells.length)][rand.nextInt(cells.length)];
	}
	
}
