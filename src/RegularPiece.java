import java.util.ArrayList;

public class RegularPiece extends Piece // A RegularPiece is-a Piece
{
	/**
	 * Parameter Constructor. Sets the row, column and owner of the piece to the given.
	 * 
	 * @param row
	 * @param column
	 * @param owner
	 */
	public RegularPiece(int row, int column, Player owner)
	{
		super(row, column, owner);
	}

	@Override
	public ArrayList<Move> getValidMoves(Board board)
	{
		//TODO: Add exception and logic
		
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		
		
		
		return possibleMoves;
	}

}
