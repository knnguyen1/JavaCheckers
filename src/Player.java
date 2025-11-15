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
 * A player has a name, a list of pieces on the board and a direction they can move.
 */

public class Player
{
	//Instance Variables
	private String name;	// A Player has-a name
	private ArrayList<Piece> pieces;	// A Player has-many pieces
	private int direction;	// A Player has-a direction that they move on the Board
	
	/**
	 * Parameter Constructor. Sets the name and color of the player to the given name and color.
	 * 
	 * @param name
	 * @param color
	 */
	public Player(String name, int direction)
	{
		this.name = name;
		this.direction = direction;
		pieces = new ArrayList<Piece>();
	}
	
	/**
	 * Gets the name of the player
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets the direction that the player's regular pieces moves in
	 * @return +1 if the player moves down on the board, -1 if they move forward
	 */
	public int getDirection()
	{
		return direction;
	}
	
	/**
	 * Gets a list of the pieces that the player has
	 * @return arraylist of pieces
	 */
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	
	/**
	 * Removes a piece from the player's list of pieces
	 * @param piece that should be removed
	 */
	public void removePiece(Piece piece)
	{
		int index = pieces.indexOf(piece);
		
		// Checks whether that piece is not in the player's list
		if (index == -1)
		{
			throw (new IllegalArgumentException("Cannot remove a piece that the player does not own."));
		}
		
		pieces.remove(index);
	}
	
	/**
	 * Adds a given piece to the player's list of pieces
	 * @param piece
	 */
	public void addPiece(Piece piece)
	{
		pieces.add(piece);
	}
}
