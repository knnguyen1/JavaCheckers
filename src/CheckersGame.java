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
 * Checkers Game reinforces the rule of checkers.
 */

public class CheckersGame
{
	// Instance variables
	private Board board;				//A CheckersGame has-a board
	private MoveHistory moveHistory;	//A CheckersGame has-a move history
	private Player player1;				//A CheckersGame has-a player1
	private Player player2;				//A CheckersGame has-a player2
	private Player currentPlayer;		//A CheckersGame has-a current player
	private Piece jumpingPiece;			//A CheckersGame has-a jumping piece 
	private boolean capturedPreviously;			

	/**
	 * Parameter constructor. Given player1 and player2's name, sets up the move history and board.
	 *
	 * @param player1Name
	 * @param player2Name
	 */
	CheckersGame(String player1Name, String player2Name)
	{
		moveHistory = new MoveHistory();
		player1 = new Player(player1Name, 1);
		player2 = new Player(player2Name, -1);
		board = new Board(player1, player2);
		currentPlayer = player1;
		jumpingPiece = null;
	}
	
	/**
	 * Gets the board.
	 * 
	 * @return board
	 */
	public Board getBoard()
	{
		return board;
	}
	
	/**
	 * Gets the move history.
	 * 
	 * @return moveHistory
	 */
	public MoveHistory getMoveHistory()
	{
		return moveHistory;
	}

	/**
	 * Switches the player who needs to make the next move.
	 */
	public void switchTurns()
	{
		if (currentPlayer == player1)
		{
			currentPlayer = player2;
		}
		else
		{
			currentPlayer = player1;
		}
	}

	/**
	 * Gets the current player.
	 *
	 * @return the current player who needs to make the next move.
	 */
	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	/**
	 * Gets player1.
	 *
	 * @return the player1.
	 */
	public Player getPlayer1()
	{
		return player1;
	}
	
	/**
	 * Gets player2.
	 *
	 * @return the player2.
	 */
	public Player getPlayer2()
	{
		return player2;
	}

	/**
	 * Makes a move on the board.
	 *
	 * @param move
	 * @return true if the move was completed successfully, otherwise false
	 */
	public boolean makeMove(Move move)
	{
		// Stores the piece moved
		Piece pieceMoved = board.getPieceAt(move.getFromRow(), move.getFromColumn());
		
		// Ensures that any forced captures are made
		if (jumpingPiece != null && jumpingPiece != pieceMoved)
		{
			return false;
		}
	
		// Checks whether the piece can be moved by the current player
		if (pieceMoved != null && pieceMoved.getOwner() == currentPlayer)
		{
			
			// Stores whether a capture has been made
			boolean madeCapture = false;
			
			// Checks whether the move is valid
			if (board.isMoveValid(move))
			{
				// Makes a capture if necessary
				if (Math.abs(move.getFromRow() - move.getToRow()) == 2 && Math.abs(move.getFromColumn() - move.getToColumn()) == 2)
				{
					Piece pieceCaptured = board.getPieceAt((move.getFromRow() + move.getToRow()) / 2, (move.getFromColumn() + move.getToColumn()) / 2);
					move.setCapturedPiece(pieceCaptured);
					pieceCaptured.getOwner().removePiece(pieceCaptured);
					board.removePiece((move.getFromRow() + move.getToRow()) / 2, (move.getFromColumn() + move.getToColumn()) / 2);
					madeCapture = true;
				}

				// Move the piece
				board.setPiece(pieceMoved, move.getToRow(), move.getToColumn());
				board.removePiece(move.getFromRow(), move.getFromColumn());
				pieceMoved.moveTo(move.getToRow(), move.getToColumn());

				// Records the move in the history
				moveHistory.addMove(move);
				
				// Checks whether the piece moved can be promoted
				if (pieceMoved instanceof RegularPiece == true)
				{
					if ((currentPlayer == player1 && pieceMoved.getRow() == Board.DIMENSION - 1) || (currentPlayer == player2 && pieceMoved.getRow() == 0))
					{
						board.promoteToKing(pieceMoved);
						
						// Ends the player's turn
						jumpingPiece = null;
						switchTurns();
						return true;	
					}
				}

				// Checks whether the current player has any more captures/jumps available
				boolean hasCaptureMove = false;
				ArrayList<Move> validMoves = pieceMoved.getValidMoves(board);

				for (Move validMove : validMoves)
				{
					if (validMove.madeCapture())
					{
						hasCaptureMove = true;
						break;
					}
				}

				// Checks whether the current player can make any more jump
				if (hasCaptureMove == true && madeCapture == true)
				{
					// Continues the player's turn with the same piece
					jumpingPiece = pieceMoved;
				}
				else
				{
					// Switches turns if they cannot make any more jumps
					jumpingPiece = null;
					switchTurns();
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether the previous player has won.
	 *
	 * @return true if the previous player won, false otherwise
	 */
	public boolean checkForWin()
	{
		// Checks whose the current player (If the current player is not player 1, then player 1 was the previous. Check whether player2 has any moves left)
		if (currentPlayer != player1)
		{
			// Checks whether player2 no longer has any more pieces or cannot make any moves
			if (player2.getPieces().isEmpty() == true || board.getValidMoves(player2).isEmpty() == true)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			// Checks whether player1 no longer has any more pieces or cannot make any moves.
			if (player1.getPieces().isEmpty() == true || board.getValidMoves(player1).isEmpty() == true)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
}
