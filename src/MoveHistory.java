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
 * A move history has a list of past moves.
 */

public class MoveHistory
{
	//Instance Variables
	private ArrayList<Move> pastMoves;	//A MoveHistory has-many past moves
	
	/**
	 * Default/No args constructor
	 */
	public MoveHistory()
	{
		pastMoves = new ArrayList<Move>();
	}
	
	/**
	 * Adds a move to the past move list
	 * 
	 * @param move
	 */
	public void addMove(Move move)
	{
		pastMoves.add(move);
	}
	
	/**
	 * Gets a string of information about the past moves
	 * 
	 * @return pastMovesInfo
	 */
	@Override
	public String toString()
	{
		String pastMovesInfo = "";
		
		for (int index = 0; index < pastMoves.size(); index++)
		{
			pastMovesInfo = pastMovesInfo + pastMoves.get(index).toString();
		}
		
		return pastMovesInfo;
	}
}
