package Game;

import java.util.Random;

public class Board {

	private int[][] board;
	private int colors;

	/**
	 * Creates a new Board object
	 * 
	 * @param nRows
	 *            The number of rows on the board.
	 * @param nCols
	 *            The number of columns on the board.
	 * @param nColors
	 *            The number of possible columns on the board.
	 */
	public Board(int nRows, int nCols, int nColors) {
		board = new int[nRows][nCols];
		this.colors = nColors;
		Random r = new Random();
		for (int[] row : board) {
			for (int col = 0; col < row.length; col++) {
				row[col] = r.nextInt(nColors);
			}
		}
	}

	/**
	 * 
	 * @return The number of legal colors. (If this number is 4, the legal
	 *         colors are 0, 1, 2 and 3.)
	 */
	public int getColors() {
		return colors;
	}

	/**
	 * Finds the color stored at a given point
	 * 
	 * @param row
	 *            The row of the cell in question
	 * @param col
	 *            The column of the cell in question
	 * @return The color of the cell
	 */
	public int getColor(int row, int col) {
		return board[row][col];
	}

	/**
	 * 
	 * @return The number of rows in the board.
	 */
	public int getRows() {
		return board.length;
	}

	/**
	 * 
	 * @return The number of columns in the board.
	 */
	public int getCols() {
		return board[0].length;
	}

	/**
	 * 
	 * @param row
	 *            The row of a cell in question.
	 * @param col
	 *            The column of a cell in question.
	 * @return true if this cell exists on the board.
	 */
	public boolean isValid(int row, int col) {
		return (row >= 0 && row < board.length && col >= 0 && col < board[0].length);
	}

	/**
	 * Flood fills an area of the grid with a given color. (Called every time
	 * someone makes a move.)
	 * 
	 * @param row
	 *            The row from which to start the flood fill.
	 * @param col
	 *            The column from which to start the flood fill.
	 * @param color
	 *            The color to add in.
	 */
	public void floodFill(int row, int col, int color) {
		floodFill(row, col, color, board[row][col]);
	}

	private void floodFill(int row, int col, int endColor, int startColor) {
		if (!isValid(row, col) || board[row][col] != startColor
				|| startColor == endColor)
			return;
		board[row][col] = endColor;
		floodFill(row + 1, col, endColor, startColor);
		floodFill(row - 1, col, endColor, startColor);
		floodFill(row, col - 1, endColor, startColor);
		floodFill(row, col + 1, endColor, startColor);
	}

	public String toString() {
		String out = "";
		for (int[] row : board) {
			for (int color : row) {
				out += " " + color;
			}
			out += "\n";
		}
		return out;
	}

}
