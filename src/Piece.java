import java.util.ArrayList;

/**
 * Lead Author(s):
 * @author Kailyn Nguyen
 *
 * Other contributors:
 * None
 *
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Version/date: October 30, 2025
 *
 * Responsibilities of class:
 * A piece has a row, column and an owner. It can move.
 */

public abstract class Piece
{
	//Instance Variables
	private int row;		// A Piece has-a row position
	private int column;		// A Piece has-a column position
	private Player owner;	// A Piece has-a owner
	
	/**
	 * Parameter Constructor.
	 * Sets the row, column and owner of the piece to the given row, column and owner.
	 */
	public Piece(int row, int column, Player owner)
	{
		this.row = row;
		this.column = column;
		this.owner = owner;
	}
	
	/**
	 * Get the row of the piece
	 *
	 * @param row
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * Get the column of the piece
	 *
	 * @param column
	 */
	public int getColumn()
	{
		return column;
	}
	
	/**
	 * Get the owner of the piece
	 *
	 * @param owner
	 */
	public Player getOwner()
	{
		return owner;
	}
	
	/**
	 * Moves the piece's position to the given row and column.
	 *
	 * @param row
	 * @param column
	 */
	public void moveTo(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Gets all the possible moves for the piece.
	 *
	 * @param board
	 * @return arraylist of moves
	 */
	abstract ArrayList<Move> getValidMoves(Board board);
}

