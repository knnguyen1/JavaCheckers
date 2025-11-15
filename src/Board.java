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
 * 
 */

public class Board
{
	//Instance Variables
	public static final int DIMENSION = 6;
	private Piece[][] board;	// A Board has-many pieces
	
	/**
	 * Parameter constuctor.
	 * 
	 * Initializes the board as a 2d array of pieces. Fills up the board with empty (null pieces) and the pieces of the players.
	 */
	public Board(Player player1, Player player2)
	{
		board = new Piece[DIMENSION][DIMENSION];
		
		//Initializes the board with empty pieces
		for (int row = 0; row < DIMENSION; row++)
		{
			for (int column = 0; column < DIMENSION; column++)
			{
				board[row][column] = null;
			}
		}
		
		//Add player1's pieces to the board
		for (int row = 0; row < 3; row++)
		{
			for (int column = 0; column < DIMENSION; column++)
			{
				if (row%2 == 0 && column %2 == 1)
				{
					Piece piece = new RegularPiece(row, column, player1);
					board[row][column] = piece;
					player1.addPiece(piece);
				}
				if (row%2 == 1 && column %2 == 0)
				{
					Piece piece = new RegularPiece(row, column, player1);
					board[row][column] = piece;
					player1.addPiece(piece);
				}
			}
		}
		
		//Add player2's pieces to the board
		for (int row = DIMENSION-3; row < DIMENSION; row++)
		{
			for (int column = 0; column < DIMENSION; column++)
			{
				if (row%2 == 0 && column %2 == 0)
				{
					Piece piece = new RegularPiece(row, column, player2);
					board[row][column] = piece;
					player2.addPiece(piece);
				}
				if (row%2 == 1 && column %2 == 1)
				{
					Piece piece = new RegularPiece(row, column, player2);
					board[row][column] = piece;
					player2.addPiece(piece);
				}
			}
		}
	}
	
	/**
	 * Gets the piece at a given row and column on the board
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public Piece getPieceAt(int row, int column)
	{
		// TODO: Add exception
		return board[row][column];
	}
	
	/**
	 * Puts a given piece on the board at a given row and column coordinate
	 * 
	 * @param piece
	 * @param row
	 * @param column
	 */
	public void setPiece(Piece piece, int row, int column)
	{
		// TODO: Add exception
		board[row][column] = piece;
	}
	
	/**
	 * Removes the piece at a given row and column coordinate
	 * 
	 * @param row
	 * @param column
	 */
	public void removePiece(int row, int column)
	{
		// TODO: Add exception
		board[row][column] = null;
	}
	
	/**
	 * Checks whether the given move is within the bounds of the board.
	 * 
	 * @param move
	 * @return true if the move is within bounds, false otherwise
	 */
	public boolean isMoveInBounds(Move move)
	{
		// TODO: Add exception?
		
		// Checks whether the start row is not within bounds
		if (move.getFromRow() < 0 || move.getFromRow() >= DIMENSION)
		{
			return false;
		}
		
		// Checks whether the start column is not within bounds
		if (move.getFromColumn() < 0 || move.getFromColumn() >= DIMENSION)
		{
			return false;
		}
		
		// Checks whether the end row is not within bounds
		if (move.getToRow() < 0 || move.getToRow() >= DIMENSION)
		{
			return false;
		}
		
		// Checks whether the end row is not within bounds
		if (move.getToColumn() < 0 || move.getToColumn() >= DIMENSION)
		{
			return false;
		}
			
		return true;
	}
	
	/**
	 * Checks whether a move is valid on the board
	 * 
	 * @param move
	 * @return
	 */
	public boolean isMoveValid(Move move)
	{
		// TODO:
	}
	
	/**
	 * Given a player, gets all the possible moves a player can make during their turn
	 * 
	 * @param player
	 * @return an arraylist of moves of the possible moves a player can make
	 */
	public ArrayList<Move> getValidMoves(Player player)
	{
		// Creates an arraylist to store all the possible moves (every way a player's pieces CAN move)
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		
		// Creates an arraylist for all the valid moves (every way a player's piece MUST move)
		ArrayList<Move> requiredMoves = new ArrayList<Move>();
		
		
		// Gets all the possible moves for the player's pieces
		ArrayList<Piece> pieces = player.getPieces();
		
		for (Piece piece: pieces)
		{
			possibleMoves.addAll(piece.getValidMoves(this));
		}
		
		// Checks if there are any required moves in the possible moves list
		for (Move move: possibleMoves)
		{
			if (move.madeCapture())
			{
				requiredMoves.add(move);
			}
		}
		
		// Returns the valid moves a player can make
		if (requiredMoves.isEmpty())
		{
			return possibleMoves;
		}
		else
		{
			return requiredMoves;
		}
	}
	
	/**
	 * Promotes a regular piece to a king piece.
	 * 
	 * @param piece
	 */
	public void promoteToKing(Piece piece)
	{
		// Gets information about the piece being promoted
		int row = piece.getRow();
		int column = piece.getColumn();
		Player owner = piece.getOwner();
		
		// Creates a new king piece with the new information
		Piece newKing = new KingPiece(row, column, owner);
		
		// Replaces the regular piece with the king piece on the board
		board[row][column] = newKing;
		
		// Replaces the regular piece with the king piece in the player's list of pieces
		piece.getOwner().removePiece(piece);
		piece.getOwner().addPiece(newKing);
		
	}
}

