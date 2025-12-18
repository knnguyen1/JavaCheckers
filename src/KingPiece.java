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
 * A king piece is a piece that can move forward and backward diagonally.
 */

public class KingPiece extends Piece	// A KingPiece is-a Piece
{
	/**
	 * Parameter Constructor. Sets the row, column and owner of the piece to the given.
	 *
	 * @param row
	 * @param column
	 * @param owner
	 */
	public KingPiece(int row, int column, Player owner)
	{
		super(row, column, owner);
	}
	
	@Override
	ArrayList<Move> getValidMoves(Board board)
	{
		// Creates an empty arraylist to store possible moves.
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		
		// Loops through the possible diagonal moves for any potential moves: top left, top right, bottom left and bottom right
		for (int rowDirection = -1; rowDirection <= 1; rowDirection += 2)
		{
			for (int colDirection = -1; colDirection <= 1; colDirection += 2)
			{
				// Stores the ending coordinates for a regular move and a jump move
				int regularMoveRow = super.getRow() + rowDirection;
				int regularMoveCol = super.getColumn() + colDirection;
				
				int jumpMoveRow = super.getRow() + 2 * rowDirection;
				int jumpMoveCol = super.getColumn() + 2 * colDirection;
				
				
				// Creates a new move with the ending coordinates
				Move newRegularMove = new Move(super.getRow(), super.getColumn(), regularMoveRow, regularMoveCol, this, null);
				Move newJumpMove = new Move(super.getRow(), super.getColumn(), jumpMoveRow, jumpMoveCol, this, null);
								
				// Checks whether a regular move is valid
				if (board.isMoveInBounds(newRegularMove))
				{
					if (board.getPieceAt(regularMoveRow, regularMoveCol) == null)
					{
						possibleMoves.add(newRegularMove);
					}
				}
				
				// Checks whether a jump move is valid
				if (board.isMoveInBounds(newJumpMove))
				{				
					if (board.getPieceAt(jumpMoveRow, jumpMoveCol) == null && board.getPieceAt(regularMoveRow, regularMoveCol) != null && board.getPieceAt(regularMoveRow, regularMoveCol).getOwner() != super.getOwner())
					{
						possibleMoves.add(newJumpMove);
					}
				}
			}
		}
		
		// Return the result
		return possibleMoves;
	}
}

