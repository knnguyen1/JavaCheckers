import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * A SaveButtonListener implements ActionListener. Saves the game in a file named "CheckersGameSave.txt".
 */
public class SaveButtonListener implements ActionListener
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
	public SaveButtonListener(CheckersGame model, CheckersView view)
	{
		this.model = model;
		this.view = view;
	}
		
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Tries to create a PrintWriter and save the game to a file
		try (PrintWriter saveFileWriter = new PrintWriter("CheckersGameSave.txt"))
		{
			// Writes the player's names into the file
			saveFileWriter.println("Player1 Name: " + view.getPlayer1Name());
			saveFileWriter.println("Player2 Name: " + view.getPlayer2Name());
			
			// Write the move history into the file
			saveFileWriter.println("Move History: ");
			saveFileWriter.println(model.getMoveHistory().toString());
			
			view.setInstructionText("The game was saved successfully.");
		}
		catch (FileNotFoundException exception)
		{
			view.setInstructionText("There was an error with saving the game.");
		}
	}
}
