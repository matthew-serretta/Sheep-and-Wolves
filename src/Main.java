import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;


public class Main extends JFrame implements Runnable{
	
	private class Canvas extends JPanel{
		
		private static final int MARGIN = 10;
		private static final int WIDTH = 35;
		private static final int HEIGHT = 35;
		
		
		private Point[][] grid = new Point[20][20];
		
		public Canvas() {
			setPreferredSize(new Dimension(1280, 720));
			for(int x=0; x<grid.length; x++) {
				for (int y=0; y<grid.length; y++) {
					grid[x][y] = new Point(MARGIN + x * WIDTH, MARGIN + y * HEIGHT);					
				}
			}
		}
		
		@Override
		public void paint(Graphics g) {
			Point p = getMousePosition();
			for(int x=0; x<grid.length; x++) {
				for (int y=0; y<grid.length; y++) {
					if (isWithin(p, grid[x][y], new Dimension(WIDTH, HEIGHT))){
						g.setColor(Color.LIGHT_GRAY);
						g.fillRect(grid[x][y].x, grid[x][y].y, WIDTH, HEIGHT);
					}
					g.setColor(Color.BLACK);
					g.drawRect(grid[x][y].x, grid[x][y].y, WIDTH, HEIGHT);					
				}
			}
		}
		
		public boolean isWithin(Point target, Point topLeft, Dimension area) {
			if (target == null)
				return false;
			else {
				return target.x > topLeft.x 
					&& target.x < topLeft.x + area.width 
					&& target.y > topLeft.y 
					&& target.y < topLeft.y + area.width;
			}
		}
	}
	
    public static void main(String[] args) {
    	Main window = new Main();
    	window.run();
    }
    
    private Main() {
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setContentPane(new Canvas());
    	this.pack();
    	this.setVisible(true);
    }
    
    @Override
    public void run() {
    	while(true)
    		this.repaint();
    }
    
    
}