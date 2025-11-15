import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckersView extends JFrame // A CheckersView is-a JFrame
{
	//Instance Variables
	private CheckersGame model; // A CheckerView has-a checkers game model
	
	/**
	 * Parameter constructor. Sets the model with the given model. Sets up the main game window.
	 */
	public CheckersView()
	{	
		this.setTitle("Checkers");	//Set the title to the JFrame
		this.setLayout(new BorderLayout()); //Set the layout of the JFrame
		
		// Name Panel to the North
		
		// TODO: fix spacing
		JPanel playerNamesPanel = new JPanel();
		JPanel player1NamesPanel = new JPanel();
		JPanel player2NamesPanel = new JPanel();
		playerNamesPanel.setLayout(new GridLayout(1, 2, 0, 1));
		player1NamesPanel.setLayout(new GridLayout(1, 2, 0, 1));
		player2NamesPanel.setLayout(new GridLayout(1, 2));
									 
		JLabel player1NameLabel = new JLabel("Player1 (Gray):");	
		JLabel player2NameLabel = new JLabel("Player2 (Red):");	
		JTextField player1TextField = new JTextField();
		JTextField player2TextField = new JTextField();
		player1NamesPanel.add(player1NameLabel);       
		player1NamesPanel.add(player1TextField);
		player2NamesPanel.add(player2NameLabel);       
		player2NamesPanel.add(player2TextField);
		playerNamesPanel.add(player1NamesPanel);
		playerNamesPanel.add(player2NamesPanel);
		this.add(playerNamesPanel,  BorderLayout.NORTH);                         	 
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	/**
	 * Starts the game
	 * @param args not used
	 */
	public static void main(String[] args)
	{
		new CheckersView();
	}
}
