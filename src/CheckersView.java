import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CheckersView extends JFrame // A CheckersView is-a JFrame
{
	//Instance Variables
	private CheckersGame model; // A CheckerView has-a checkers game model
	
	private JTextField player1TextField;
	private JTextField player2TextField;
	
	DefaultListModel<String> historyModel;
	private JButton[][] boardButtons;	// A CheckersView has-many board buttons
	
	
	/**
	 * Parameter constructor. Sets the model with the given model. Sets up the main game window.
	 */
	public CheckersView()
	{	
		this.setTitle("Checkers");	//Set the title to the JFrame
		this.setLayout(new BorderLayout()); //Set the layout of the JFrame
		
		// Name Panel to the NORTH
		JPanel playerNamesPanel = new JPanel();
		playerNamesPanel.setLayout(new BoxLayout(playerNamesPanel, BoxLayout.X_AXIS));
		// TODO: decide on colors
		JLabel player1NameLabel = new JLabel("Player1 ():");	
		JLabel player2NameLabel = new JLabel("Player2 ():");	
		JTextField player1TextField = new JTextField();
		JTextField player2TextField = new JTextField();
		playerNamesPanel.add(player1NameLabel);       
		playerNamesPanel.add(player1TextField);
		playerNamesPanel.add(player2NameLabel);       
		playerNamesPanel.add(player2TextField);
		this.add(playerNamesPanel,  BorderLayout.NORTH); 
		
		// Board Panel to the CENTER
		JPanel boardPanel = new JPanel(new GridLayout(Board.DIMENSION, Board.DIMENSION));
		boardButtons = new JButton[Board.DIMENSION][Board.DIMENSION];
		for (int row = 0; row < Board.DIMENSION; row++)
		{
			for (int col = 0; col < Board.DIMENSION; col++)
			{
				JButton boardButton = new JButton();
				boardButton.setPreferredSize(new Dimension(60, 60));
				boardButtons[row][col] = boardButton;
				boardPanel.add(boardButton);
				
				//TODO: colors and listener
			}
		}
		this.add(boardPanel, BorderLayout.CENTER);
		
		
		// Move History to the EAST
		JPanel moveHistoryPanel = new JPanel();
		moveHistoryPanel.setLayout(new BoxLayout(moveHistoryPanel, BoxLayout.Y_AXIS));
		JLabel historyLabel = new JLabel("Move History");	
		historyModel = new DefaultListModel<String>();
		JList historyList = new JList<>(historyModel);
		JScrollPane historyScrollPane = new JScrollPane(historyList);
		moveHistoryPanel.add(historyLabel);
		moveHistoryPanel.add(historyScrollPane);
		this.add(moveHistoryPanel, BorderLayout.EAST);
		
		// Start, Restart Buttons to the SOUTH
		JPanel controlPanel = new JPanel();
		JButton startButton = new JButton("StartGame");
		JButton restartButton = new JButton("Restart Game");
		controlPanel.add(startButton);
		controlPanel.add(restartButton);
		this.add(controlPanel, BorderLayout.SOUTH);
		
		//TODO: add listeners
		
		
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
