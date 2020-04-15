import java.awt.*;

//Cell class is used as the building blocks for a gameboard's grid
public class Cell extends Rectangle{
	
	public Cell(int x, int y) {
		super(x, y, 35, 35);
	}
	
	//paint function is used to 'paint' the image of the cell onto the java display
	public void paint(Graphics g, Boolean highlighted) {
		//paint the cell with inside color
		if (highlighted){
			g.setColor(new Color(0,153,51));
			g.fillRect(x, y, width, height);
		}
		else {
			g.setColor(new Color(130,212,89));
			g.fillRect(x, y, 35, 35);
		}
		//paint the cell border
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);		
	}
	
	//contains function is used to determine if a given point is contained within the Cell
	@Override
	public boolean contains(Point target) {
		if (target == null)
			return false;
		else {
			return super.contains(target);
		}
	}
}
