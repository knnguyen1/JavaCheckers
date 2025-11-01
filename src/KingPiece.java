import java.util.ArrayList;

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
		//TODO: Add exception and logic
		
		if (Board[row+1][column+1] == null)
		{
			
		}
		if (Board[row-1][column-1] == null)
		{
			
		}
	}
	
	
}
