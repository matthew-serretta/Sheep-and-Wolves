import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import bos.GameBoard;
import bos.GamePiece;
import bos.MoveDown;
import bos.MoveLeft;
import bos.MoveRight;
import bos.MoveUp;
import bos.Pair;
import bos.RelativeMove;

// A grid is made up of Cell objects to draw the game's grid/game board
public class Grid implements GameBoard<Cell> {

	private static Grid singleton = null;

	private static final int MARGIN = 10;
	private static final int WIDTH = 35;
	private static final int HEIGHT = WIDTH;
	private static int gridWidth = 20;
	private static int gridHeight = gridWidth;

	private Cell[][] cells = new Cell[gridWidth][gridHeight];

	private Grid() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				cells[i][j] = new Cell(MARGIN + j * HEIGHT, MARGIN + i * WIDTH);
			}
		}
	}

	// Singleton design pattern to ensure there is only one grid
	public static Grid getGrid() {
		synchronized (Grid.class) {
			if (singleton == null) {
				singleton = new Grid();
			}
		}
		return singleton;
	}

	// basic getter
	public Cell cellLocation(int x, int y) {
		return cells[x][y];
	}

	// basic getter
	public int gridWidth() {
		return gridWidth;
	}

	// basic getter
	public int gridHeight() {
		return gridHeight;
	}

	// paint function is used to 'paint' the image of the grid onto the java display
	public void paint(Graphics g, Point mousePosition) {
		doToEachCell((c) -> c.paint(g, c.contains(mousePosition)));
	}

	// returns a random location on the grid
	public Cell getRandomCell() {
		java.util.Random rand = new java.util.Random();
		return cells[rand.nextInt(cells.length)][rand.nextInt(cells.length)];
	}

	// function used to simplify looping through grid's cells
	// e.g. paint all cells
	private void doToEachCell(Consumer<Cell> func) {
		for (int y = 0; y < gridHeight; y++) {
			for (int x = 0; x < gridWidth; x++) {
				func.accept(cells[y][x]);
			}
		}
	}

	// Used for previous methods before findAmongstCell function was created.
	// Not deleted as it may well be useful in the future
	private Pair<Integer, Integer> indexOfCell(Cell c) {
		for (int y = 0; y < gridHeight; y++) {
			for (int x = 0; x < gridWidth; x++) {
				if (cells[y][x] == c) {
					return new Pair(y, x);
				}
			}
		}
		return null;
	}

	// returns the index of the grid cell that matches the predicate
	private Pair<Integer, Integer> findAmongstCells(Predicate<Cell> predicate) {
		for (int y = 0; y < gridHeight; y++) {
			for (int x = 0; x < gridWidth; x++) {
				if (predicate.test(cells[y][x])) {
					return new Pair(y, x);
				}
			}
		}
		return null;
	}

	// safe version of findAmongstCells as it uses optionals in case searching for
	// non-existent Cell
	// e.g. there is no cell above the top row, therefore a character in the top row
	// can't move up
	private Optional<Pair<Integer, Integer>> safeFindAmongstCells(Predicate<Cell> predicate) {
		for (int y = 0; y < gridHeight; y++) {
			for (int x = 0; x < gridWidth; x++) {
				if (predicate.test(cells[y][x])) {
					return Optional.of(new Pair(y, x));
				}
			}
		}
		return null;
	}

	// returns the Cell above a given cell
	@Override
	public Optional<Cell> above(Cell cell) {
		return safeFindAmongstCells((c) -> c == cell).filter((pair) -> pair.first > 0)
				.map((pair) -> cells[pair.first - 1][pair.second]);
	}

	// returns the Cell below a given cell
	@Override
	public Optional<Cell> below(Cell cell) {
		return safeFindAmongstCells((c) -> c == cell).filter((pair) -> pair.first < 19)
				.map((pair) -> cells[pair.first + 1][pair.second]);
	}

	// returns the Cell to the left of a given cell
	@Override
	public Optional<Cell> leftOf(Cell cell) {
		return safeFindAmongstCells((c) -> c == cell).filter((pair) -> pair.second > 0)
				.map((pair) -> cells[pair.first][pair.second - 1]);
	}

	// returns the Cell to the right of a given cell
	@Override
	public Optional<Cell> rightOf(Cell cell) {
		return safeFindAmongstCells((c) -> c == cell).filter((pair) -> pair.second < 19)
				.map((pair) -> cells[pair.first][pair.second + 1]);
	}

	// returns the moves between the 'from' and 'to' Cells for a given character
	// very basic, moves horizontally then vertically
	@Override
	public List<RelativeMove> movesBetween(Cell from, Cell to, GamePiece<Cell> mover) {
		Pair<Integer, Integer> fromIndex = findAmongstCells((c) -> c == from);
		Pair<Integer, Integer> toIndex = findAmongstCells((c) -> c == to);

		List<RelativeMove> result = new ArrayList<RelativeMove>();

		// horizontal movement
		if (fromIndex.second <= toIndex.second) {
			for (int i = fromIndex.second; i < toIndex.second; i++) {
				result.add(new MoveRight(this, mover));
			}
		} else {
			for (int i = toIndex.second; i < fromIndex.second; i++) {
				result.add(new MoveLeft(this, mover));
			}
		}

		// vertical movement
		if (fromIndex.first <= toIndex.first) {
			for (int i = fromIndex.first; i < toIndex.first; i++) {
				result.add(new MoveDown(this, mover));
			}
		} else {
			for (int i = toIndex.first; i < fromIndex.first; i++) {
				result.add(new MoveUp(this, mover));
			}
		}
		return result;
	}

}
