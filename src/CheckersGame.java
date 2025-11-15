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

public class CheckersGame
{
	//Instance variables
	Board board;
	MoveHistory moveHistory;
	Player player1;
	Player player2;
	Player currentPlayer;
	
	CheckersGame(Player player1, Player player2)
	{
		board = new Board(player1, player2);
		moveHistory = new MoveHistory();
		this.player1 = player1;
		this.player2 = player2;
		currentPlayer = player1;
	}
	
	public void switchTurns()
	{
		if (currentPlayer == player1)
		{
			currentPlayer = player2;
		}
		else {
			currentPlayer = player1;
		}
	}
	
	
}
