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
 * 
 */

public class Move
{
	//Instance Variables
	private int fromRow;		// A Move has-a from row postion
	private int toRow;			// A Move has-a to row position
	private int fromColumn;		// A Move has-a from column postion
	private int toColumn;		// A Move has-a to column position
	private Piece pieceMoved; 	// A Move has-a piece moved
	private Piece capturedPiece; // A Move has-a captured piece
	
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
	 * Gets a string of information about the move
	 * 
	 * @return moveInfo
	 */
	public String toString()
	{
		String moveInfo = capturedPiece.getOwner().getName() + " moved their piece from (" + fromRow + ", " + fromColumn + ") to (" + toRow + " ," + toColumn + ")";
				
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
