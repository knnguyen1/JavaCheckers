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
	}
	
	/**
	 * Gets the board of the game.
	 * 
	 * @return board
	 */
	public Board getBoard()
	{
		return board;
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
	 * Makes a move on the board.
	 *
	 * @param move
	 * @return true if the move was completed successfully, otherwise false
	 */
	public boolean makeMove(Move move)
	{
		// Checks whether the piece being moved is owned by the current player
		if (board.getPieceAt(move.getFromRow(), move.getFromColumn()).getOwner() == currentPlayer)
		{
			// Checks whether the move is valid
			if (board.isMoveValid(move))
			{
				// Makes a capture if necessary
				if (move.madeCapture())
				{
					Piece pieceCaptured = board.getPieceAt((move.getFromRow() + move.getToRow()) / 2, (move.getFromColumn() + move.getToColumn()) / 2);
					pieceCaptured.getOwner().removePiece(pieceCaptured);
					board.removePiece((move.getFromRow() + move.getToRow()) / 2, (move.getFromColumn() + move.getToColumn()) / 2);
				}

				// Move the piece
				Piece pieceMoved = board.getPieceAt(move.getFromRow(), move.getFromColumn());
				board.setPiece(pieceMoved, move.getToRow(), move.getToColumn());
				board.removePiece(move.getFromRow(), move.getFromColumn());
				pieceMoved.moveTo(move.getToRow(), move.getToColumn());

				// Records the move in the history
				moveHistory.addMove(move);

				// Store the result of whether the player has any captured moves
				boolean hasCaptureMove = false;

				// Checks whether the piece moved can be promoted
				if (pieceMoved instanceof RegularPiece == true)
				{
					if (currentPlayer == player1 && pieceMoved.getRow() == Board.DIMENSION - 1)
					{
						board.promoteToKing(pieceMoved);
					}
					else if (currentPlayer == player2 && pieceMoved.getRow() == 0)
					{
						board.promoteToKing(pieceMoved);
					}
				}

				// TODO: Reinforce the rule that kings cannot make a capture directly after being promoted.

				// Gets the valid moves for that piece
				ArrayList<Move> validMoves = pieceMoved.getValidMoves(board);

				// Searches the player's valid moves to see if they have any captures available
				for (Move validMove : validMoves)
				{
					if (validMove.madeCapture())
					{
						hasCaptureMove = true;
						break;
					}
				}

				// Checks whether the current player can make any more jump
				if (hasCaptureMove == false)
				{
					// Switches turns if they cannot make any more jumps
					switchTurns();
				}
			}

			return true;
		}

		return false;
	}

	/**
	 * Checks whether the current player has won.
	 *
	 * @return true if the current player won, false otherwise
	 */
	public boolean checkForWin()
	{
		// Checks whose the current player
		if (currentPlayer == player1)
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
