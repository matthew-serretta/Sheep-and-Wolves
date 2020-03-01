import java.awt.*;
import java.util.List;
import java.util.Optional;

import bos.GamePiece;
import bos.RelativeMove;

public class Grid implements bos.GameBoard<Cell>{

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
	
	public Cell cellLocation(int x, int y) {
		return cells[x][y];
	}
	
	private bos.Pair<Integer, Integer> indexOfCell(Cell c) {
		for(int y = 0; y< 20; y++) {
			for(int x = 0; x < 20; x++) {
				if (cells[y][x] == c) {
					return new bos.Pair<Integer, Integer>(y,  x);
				}
			}
		}
		return null;
	}

	@Override
	public Optional<Cell> above(Cell cell) {
		bos.Pair<Integer, Integer> loc = indexOfCell(cell);
		if(loc.first > 0) 
			return Optional.of(cells[loc.first - 1][loc.second]);
		else
			return Optional.empty();
	}

	@Override
	public Optional<Cell> below(Cell cell) {
		bos.Pair<Integer, Integer> loc = indexOfCell(cell);
		if(loc.first < 19) 
			return Optional.of(cells[loc.first + 1][loc.second]);
		else
			return Optional.empty();
	}

	@Override
	public Optional<Cell> leftOf(Cell cell) {
		bos.Pair<Integer, Integer> loc = indexOfCell(cell);
		if(loc.second > 0) 
			return Optional.of(cells[loc.first][loc.second - 1]);
		else
			return Optional.empty();
	}
	
	@Override
	public Optional<Cell> rightOf(Cell cell) {
		bos.Pair<Integer, Integer> loc = indexOfCell(cell);
		if(loc.second < 19) 
			return Optional.of(cells[loc.first][loc.second + 1]);
		else
			return Optional.empty();
	}

	@Override
	public List<RelativeMove> movesBetween(Cell from, Cell to, GamePiece<Cell> mover) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
