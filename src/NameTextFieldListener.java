import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
 * How to write a document listener. How to Write a Document Listener (The JavaTM Tutorials > Creating a GUI With Swing > Writing Event Listeners). (n.d.). 
 * Retrieved December 6, 2025, from https://docs.oracle.com/javase/tutorial/uiswing/events/documentlistener.html 
 *
 * Version/date: December 5, 2025
 *
 * Responsibilities of class:
 * A board is a 2d array of pieces. It can add and remove pieces. It knows which moves are valid and where players can move their pieces. 
 */
public class NameTextFieldListener implements DocumentListener
{
	//Instance Variable
	private CheckersView view;
		
	public NameTextFieldListener(CheckersView view)
	{
		this.view = view;
	}

	/**
	 * 
	 * @param 
	 */
	public void hasNameInputs()
	{
		if (!view.getPlayer1Name().isBlank() && !view.getPlayer2Name().isBlank())
		{
			view.enableStartButton();
		}
		else
		{
			view.disableStartButton();
		}
	}
	
	@Override
	public void insertUpdate(DocumentEvent e)
	{
		hasNameInputs();
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		hasNameInputs();
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		hasNameInputs();
	}

}
