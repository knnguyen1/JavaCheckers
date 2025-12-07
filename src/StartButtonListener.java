import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButtonListener implements ActionListener
{
	//Instance Variable
	private CheckersView view;
	
	public StartButtonListener(CheckersView view)
	{
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		view.startGame();
	}
}
