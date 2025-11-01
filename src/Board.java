import java.lang.reflect.Array;

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
	 * Initializes the board as a 2d
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
	
	public Piece getPieceAt(int row, int column)
	{
		return board[row][column];
	}
	
	public Piece movePiece(int row, int column)
	{
		return board[row][column];
	}
	
	public void removePiece(int row, int column)
	{
		board[row][column] = null;
	}
	
	public void isMoveValid()
	{
		
	}
	
}

