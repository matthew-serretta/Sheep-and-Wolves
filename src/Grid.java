import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import bos.GamePiece;
import bos.MoveDown;
import bos.MoveLeft;
import bos.MoveRight;
import bos.MoveUp;
import bos.Pair;
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
		doToEachCell( (c) -> c.paint(g,  c.contains(mousePosition)));
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
	
	private Pair<Integer, Integer> findAmongstCells(Predicate<Cell> predicate) {
		for(int y = 0; y< 20; y++) {
			for(int x = 0; x < 20; x++) {
				if (predicate.test(cells[y][x])) {
					return new Pair(y,  x);
				}
			}
		}
		return null;
	}
	
	private Optional<Pair<Integer, Integer>> safeFindAmongstCells(Predicate<Cell> predicate) {
		for(int y = 0; y< 20; y++) {
			for(int x = 0; x < 20; x++) {
				if (predicate.test(cells[y][x])) {
					return Optional.of(new Pair(y,  x));
				}
			}
		}
		return null;
	}
	
	private void doToEachCell(Consumer<Cell> func){
      for(int y = 0; y < 20; y++) {
    	  for(int x = 0; x < 20; x++) {
    		  func.accept(cells[y][x]);
    	  }
      }
    }

	@Override
	public Optional<Cell> above(Cell cell) {
		return safeFindAmongstCells((c) -> c == cell)
				.filter((pair) -> pair.first > 0)
				.map((pair) -> cells[pair.first - 1][pair.second]);
	}

	@Override
	public Optional<Cell> below(Cell cell) {
		return safeFindAmongstCells((c) -> c == cell)
				.filter((pair) -> pair.first < 19)
				.map((pair) -> cells[pair.first + 1][pair.second]);
	}

	@Override
	public Optional<Cell> leftOf(Cell cell) {
		return safeFindAmongstCells((c) -> c == cell)
				.filter((pair) -> pair.second > 0)
				.map((pair) -> cells[pair.first][pair.second - 1]);
	}
	
	@Override
	public Optional<Cell> rightOf(Cell cell) {
		return safeFindAmongstCells((c) -> c == cell)
				.filter((pair) -> pair.second < 19)
				.map((pair) -> cells[pair.first][pair.second + 1]);
	}

	@Override
	public List<RelativeMove> movesBetween(Cell from, Cell to, GamePiece<Cell> mover) {
		Pair<Integer, Integer> fromIndex = findAmongstCells((c) -> c == from);
		Pair<Integer, Integer> toIndex = findAmongstCells((c) -> c == to);
		
		List<RelativeMove> result = new ArrayList<RelativeMove>();
		
		//horizontal movement
		if(fromIndex.second <= toIndex.second) {
			for(int i = fromIndex.second; i < toIndex.second; i++) {
				result.add(new MoveRight(this, mover));
			}
		} else {
			for(int i = toIndex.second; i < fromIndex.second; i++) {
				result.add(new MoveLeft(this, mover));
			}
		}

		//vertical movement
		if(fromIndex.first <= toIndex.first) {
			for(int i = fromIndex.first; i < toIndex.first; i++) {
				result.add(new MoveDown(this, mover));
			}
		} else {
			for(int i = toIndex.first; i < fromIndex.first; i++) {
				result.add(new MoveUp(this, mover));
			}
		}
		return result;
	}
	
}
