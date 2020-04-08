import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;


public class Main extends JFrame implements Runnable{
	
	private class Canvas extends JPanel implements KeyListener{
		
		private Grid grid;
		private Stage stage;
		
		public Canvas() {
			setPreferredSize(new Dimension(1280, 720));
			
			grid = new Grid();
			stage = new Stage();
		}
		
		@Override
		public void paint(Graphics g) {
			stage.update();
			stage.paint(g, getMousePosition());
		}

		@Override
		public void keyTyped(KeyEvent e) {
			stage.notifyAll(e.getKeyChar(), stage.grid);
			}

		@Override
		public void keyPressed(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {}	
	}
	
    public static void main(String[] args) {
    	Main window = new Main();
    	window.run();
    }
    
    private Main() {
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	Canvas canvas = new Canvas();
    	this.setContentPane(canvas);
    	this.addKeyListener(canvas);
    	this.pack();
    	this.setVisible(true);
    }
    
    @Override
    public void run() {
    	while(true)
    		this.repaint();
    }    
}