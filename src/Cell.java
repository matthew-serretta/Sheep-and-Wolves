import java.awt.*;

public class Cell {
	
	private int x;
	private int y;
	private int width = 35;
	private int height = 35;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g, Boolean highlighted) {
		if (highlighted){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x, y, width, height);
		}
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);		
	}
	
	public boolean contains(Point target) {
		if (target == null)
			return false;
		else {
			return target.x > x 
				&& target.x < x + width 
				&& target.y > y 
				&& target.y < y + height;
		}
	}
}
