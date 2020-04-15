import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements Runnable{
	
	//Canvas class is used for all of the 'painting' onto the java display
	private class Canvas extends JPanel implements KeyListener{
		
		private Grid grid;
		private Stage stage;
		
		public Canvas() {
			setPreferredSize(new Dimension(1280, 720));
			
			grid = Grid.getGrid();
			stage = Stage.getStage();
		}
		
		//refresh the stage and repaint everything
		@Override
		public void paint(Graphics g) {
			stage.update();
			stage.paint(g, getMousePosition());
		}

		//listen for keystrokes (commands)
		@Override
		public void keyTyped(KeyEvent e) {
			stage.notifyAll(e.getKeyChar(), Grid.getGrid());
			}

		//only here to prevent Canvas class complaining of unimplemented methods
		//may be useful if we want to change command options
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
    
    //keep repainting the canvas for when things change
    //e.g. characters move
    @Override
    public void run() {
    	while(true)
    		this.repaint();
    }    
}