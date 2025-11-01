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

public class Player
{
	//Instance Variables
	private String name;	// A Player has-a name
	private String color;	// A Player has-a piece color
	private ArrayList<Piece> pieces;	// A Player has-many pieces
	
	/**
	 * Parameter Constructor. Sets the name and color of the player to the given name and color.
	 * 
	 * @param name
	 * @param color
	 */
	public Player(String name, String color)
	{
		this.name = name;
		this.color = color;
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
	 * Gets the color of the player's pieces
	 * @return color
	 */
	public String getColor()
	{
		return color;
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
		// TODO: add exception
		int index = pieces.indexOf(piece);
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
