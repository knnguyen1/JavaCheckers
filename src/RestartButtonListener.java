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
 * A RestartButtonListener implements ActionListener.
 */
public class RestartButtonListener implements ActionListener
{
	//Instance Variable
	private CheckersView view; // A RestartButtonListener has-a view
	
	/**
	 * Parameter constructor. Sets the view with the given view.
	 * 
	 * @param view
	 */
	public RestartButtonListener(CheckersView view)
	{
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		view.restartGame();
	}
}

