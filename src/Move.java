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
 * A move has a starting and ending position. It knows its start and end coordinates. It has a piece moved and captured piece.
 */

public class Move
{
	//Instance Variables
	private int fromRow;			// A Move has-a from row position
	private int toRow;				// A Move has-a to row position
	private int fromColumn;			// A Move has-a from column position
	private int toColumn;			// A Move has-a to column position
	private Piece pieceMoved; 		// A Move has-a piece moved
	private Piece capturedPiece;	// A Move has-a captured piece
	
	/**
	 * Parameter Constructor. Sets the from and to row and column indexes to the given from and to column indexes.
	 *
	 * @param fromRow
	 * @param fromColumn
	 * @param toRow
	 * @param toColumn
	 */
	public Move(int fromRow, int fromColumn, int toRow, int toColumn, Piece pieceMoved, Piece capturedPiece)
	{
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
		this.pieceMoved = pieceMoved;
		this.capturedPiece = capturedPiece;
	}
	
	/**
	 * Checks whether the move has made a capture or not.
	 *
	 * @return true if the move resulted in a capture, otherwise false
	 */
	public boolean madeCapture()
	{
		if (capturedPiece == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * Gets the row where the move began.
	 *
	 * @return fromRow
	 */
	public int getFromRow()
	{
		return fromRow;
	}
	
	/**
	 * Gets the column where the move began.
	 *
	 * @return fromColumn
	 */
	public int getFromColumn()
	{
		return fromColumn;
	}
	
	/**
	 * Gets the row where the move ended.
	 *
	 * @return toRow
	 */
	public int getToRow()
	{
		return toRow;
	}
	
	/**
	 * Gets the column where the move ended.
	 *
	 * @return toColumn
	 */
	public int getToColumn()
	{
		return toColumn;
	}
	
	/**
	 * Checks whether the given object is the same move
	 *
	 * @param move
	 * @return true if the object is the same move, otherwise false
	 */
	@Override
	public boolean equals(Object other)
	{
		// Checks if they are the same object with the same reference in memory
		if (this == other) {
			return true;
		}
		// Null check
		else if (other == null)
		{
			return false;
		}
		// Checks if the object is not a Move
		else if (other instanceof Move == false)
		{
			return false;
		}
		else
		{
			// Cast other as a Move
			Move move = (Move) other;
		
			//Checks if they have the same beginning and ending coordinates
			if (move.getFromRow() == fromRow && move.getFromColumn() == fromColumn && move.getToRow() == toRow && move.getToColumn() == toColumn)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	/**
	 * Gets a string of information about the move
	 *
	 * @return moveInfo
	 */
	@Override
	public String toString()
	{
		String moveInfo = pieceMoved.getOwner().getName() + " moved their piece from (" + fromRow + ", " + fromColumn + ") to (" + toRow + " ," + toColumn + ")";
				
		if (madeCapture())
		{
			moveInfo = moveInfo + " and made a capture. \n";
		}
		else
		{
			moveInfo = moveInfo + "\n";
		}
		
		return moveInfo;
	}
}	
