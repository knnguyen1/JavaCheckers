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
				// Stores the ending coordinates
				int row = super.getRow() + rowDirection;
				int col = super.getColumn() + colDirection;
				
				// Creates a new move with the ending coordinates
				Move newMove = new Move(super.getRow(), super.getColumn(), row, col, this, null);
				
				// Checks whether the move is in bounds and there is an empty space for the piece to move
				if (board.isMoveInBounds(newMove) && board.getPieceAt(row, col) == null)
				{
					possibleMoves.add(newMove);
				}
				// Checks whether there is a possible move in the direction but as a jump
				else if (board.getPieceAt(row, col) != null)
				{
					Move newJumpMove = new Move(super.getRow(), super.getColumn(), row + rowDirection, col + colDirection, this, board.getPieceAt(row, col));
					
					// Checks whether the jump move is valid
					if (board.isMoveInBounds(newJumpMove) && board.getPieceAt(row + rowDirection, col + colDirection) == null && board.getPieceAt(row, col).getOwner() != super.getOwner())
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

