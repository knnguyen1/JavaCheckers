import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
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
 * A LoadButtonListener implements ActionListener. Reads through the save file and loads the game.
 */

public class LoadButtonListener implements ActionListener
{
	//Instance Variable
	private CheckersGame model; // A SaveButtonListener has-a model
	private CheckersView view; // A SaveButtonListener has-a view
	
	/**
	 * Parameter constructor. Sets the model and view with the given model and view.
	 * 
	 * @param model
	 * @param view
	 */
	public LoadButtonListener(CheckersGame model, CheckersView view)
	{
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		File saveFile = new File("CheckersGameSave.txt");	// Setup the save file as a File object.
		Scanner scan = null;								// Declare a scanner object
		String content = "";								// Create a String to hold the content

		// Tries to open the file and read it
		try
		{
			scan = new Scanner(saveFile); 
			
			// Reads the player's name
			String player1Name = scan.nextLine().substring(14);
			String player2Name = scan.nextLine().substring(14);

			// Creates a new model given the players name
			model = new CheckersGame(player1Name, player2Name);
						
			// Skip the MoveHistory: line
			scan.nextLine();
			
			// Reads the move history
			while (scan.hasNextLine())
			{
				String moveHistoryLine = scan.nextLine();
				
				// Gets the index of the starting coordinates
				int startingCoordIndex1 = moveHistoryLine.indexOf("(");
				int startingCoordIndex2 = moveHistoryLine.indexOf(",");
				int startingCoordIndex3 = moveHistoryLine.indexOf(")");
				int endingCoordIndex1 = moveHistoryLine.indexOf("(", startingCoordIndex1);
				int endingCoordIndex2 = moveHistoryLine.indexOf(",", startingCoordIndex1);
				int endingCoordIndex3 = moveHistoryLine.indexOf(")", startingCoordIndex1);
				
				// Gets the from row, col and to row, col from the line
				int fromRow = Integer.valueOf(moveHistoryLine.substring(startingCoordIndex1+1, startingCoordIndex2));
				int fromCol = Integer.valueOf(moveHistoryLine.substring(startingCoordIndex2+2, startingCoordIndex3));
				int toRow = Integer.valueOf(moveHistoryLine.substring(endingCoordIndex1+1, endingCoordIndex2));
				int toCol = Integer.valueOf(moveHistoryLine.substring(endingCoordIndex2+2, endingCoordIndex3));
				
				// Makes the move
				Move move = new Move(fromRow, fromCol, toRow, toCol, model.getBoard().getPieceAt(fromRow, fromCol), null);
				model.makeMove(move);
			}
		}
		// Catches if the file does not exist
		catch (Exception exception)
		{
			view.setInstructionText("Game could not be loaded.");
		}
		finally
		{
			// Closes the file
			if (scan != null)
			{
				scan.close();
			}
		}
		
		view.updateUI();
	}
}
