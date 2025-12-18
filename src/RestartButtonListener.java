import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartButtonListener implements ActionListener
{
	//Instance Variable
	private CheckersView view;
	
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

