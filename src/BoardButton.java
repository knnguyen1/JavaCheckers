import javax.swing.JButton;

/**
 * Lead Author(s):
 * @author Kailyn Nguyen
 *
 * Other contributors:
 * None
 *
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving 
 *
 * Version/date: December 12, 2025
 *
 * Responsibilities of class:
 * A BoardButton is-a JButton with a row and column.
 */

public class BoardButton extends JButton
{
	// Instance Variables
	private int row;	//A BoardButton has-a row 
	private int column;	//A BoardButton has-a column 
	
	/**
	 * Parameter constructor. Sets the row and column of the board button.
	 */
	public BoardButton (int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Gets the row of the board button.
	 * 
	 * @return row
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * Gets the column of the board button.
	 * 
	 * @return column
	 */
	public int getColumn()
	{
		return column;
	}
}
