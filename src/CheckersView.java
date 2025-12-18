import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

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
 * GeeksforGeeks. (2025, July 11). Java swing - look and feel. 
 * Retrieved December 5, 2025, from https://www.geeksforgeeks.org/java/java-swing-look-feel/ 
 * 
 * Orozco-Fletcher, M. (2022, January 22). Java lesson 22: Inserting images onto the JFRAME. Medium. 
 * Retrieved December 12, 2025, https://medium.com/@michael71314/java-lesson-22-inserting-images-onto-the-jframe-a0a0b6540cca 
 * 
 * Version/date: November 14, 2025
 *
 * Responsibilities of class:
 * 
 */

public class CheckersView extends JFrame // A CheckersView is-a JFrame
{
	//Instance Variables
	private CheckersGame model; // A CheckerView has-a checkers game model
	
	private JTextField player1TextField;
	private JTextField player2TextField;
	private BoardButton[][] boardButtons;
	private JLabel instructionLabel;
	private JTextArea moveHistoryTextArea;
	private JButton startButton;
	private JButton restartButton;
	private BoardButton selectedButton;
	private ImageIcon blackKingIcon;
	private ImageIcon blackRegularIcon;
	private ImageIcon redKingIcon;
	private ImageIcon redRegularIcon;
	
	
	/**
	 * Parameter constructor. Sets the model with the given model. Sets up the main game window.
	 */
	public CheckersView()
	{	
		this.setTitle("Checkers");			//Set the title to the JFrame
		this.setLayout(new BorderLayout()); //Set the layout of the JFrame
		
		// Load and Scale Piece Images
		blackKingIcon = new ImageIcon(new ImageIcon("images/black_king.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		blackRegularIcon = new ImageIcon(new ImageIcon("images/black_regular.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		redKingIcon = new ImageIcon(new ImageIcon("images/red_king.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		redRegularIcon = new ImageIcon(new ImageIcon("images/red_regular.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
				
		// Name Panel to the NORTH
		JPanel playerNamesPanel = new JPanel(new FlowLayout());	
		
		JLabel player1NameLabel = new JLabel("Player1 (Black):");	
		JLabel player2NameLabel = new JLabel("Player2 (Red):");
		
		player1TextField = new JTextField(10);
		player2TextField = new JTextField(10);
		
		player1TextField.getDocument().addDocumentListener(new NameTextFieldListener(this));
		player2TextField.getDocument().addDocumentListener(new NameTextFieldListener(this));
		
		playerNamesPanel.add(player1NameLabel);       
		playerNamesPanel.add(player1TextField);
		playerNamesPanel.add(player2NameLabel);       
		playerNamesPanel.add(player2TextField);
		
		instructionLabel = new JLabel("Please input your names and click the Start Button.");
		instructionLabel.setAlignmentX(CENTER_ALIGNMENT);
		playerNamesPanel.add(instructionLabel);

		
		this.add(playerNamesPanel, BorderLayout.NORTH); 
		
		
		// Board Panel to the CENTER
		JPanel boardPanel = new JPanel(new GridLayout(Board.DIMENSION+1, Board.DIMENSION+1));
		boardButtons = new BoardButton[Board.DIMENSION][Board.DIMENSION];
		
		// Add column labels for the first row of the board panel
		JLabel emptyLabel = new JLabel("");	// Empty JLabel for the beginning of the column number row and row numbers column
		boardPanel.add(emptyLabel);
		for (int col = 0; col < Board.DIMENSION; col++)
		{
			JLabel colNumber = new JLabel(Integer.toString(col), SwingConstants.CENTER);
			boardPanel.add(colNumber);
		}
		
		// Add the board buttons and row labels
		for (int row = 0; row < Board.DIMENSION; row++)
		{
			//Add a row label at the beginning of each row 
			JLabel rowNum = new JLabel(Integer.toString(row), SwingConstants.CENTER);
			boardPanel.add(rowNum);
			
			// Add the board buttons
			for (int col = 0; col < Board.DIMENSION; col++)
			{
				BoardButton boardButton = new BoardButton(row, col);
				boardButton.setPreferredSize(new Dimension(60, 60));
				boardButton.setEnabled(false);
				
				if ((row + col)%2 == 1)
				{
					boardButton.setBackground(new Color(139, 69, 19));
				}
				else
				{
					boardButton.setBackground(new Color(245, 222, 179));
				}
				
				boardButtons[row][col] = boardButton;
				boardButtons[row][col].setEnabled(false);

				boardPanel.add(boardButton);
			}
		}
		this.add(boardPanel, BorderLayout.CENTER);
		
		
		// Move History Panel to the EAST
		JPanel moveHistoryPanel = new JPanel();
		moveHistoryPanel.setLayout(new BoxLayout(moveHistoryPanel, BoxLayout.Y_AXIS));
		
		JLabel historyLabel = new JLabel("Move History");
		historyLabel.setAlignmentX(CENTER_ALIGNMENT);
		moveHistoryTextArea = new JTextArea();
		moveHistoryTextArea.setEditable(false);
		moveHistoryTextArea.setLineWrap(true);
		moveHistoryTextArea.setWrapStyleWord(true);
		JScrollPane historyScrollPane = new JScrollPane(moveHistoryTextArea);
		historyScrollPane.setPreferredSize(new Dimension(250, 540));
		
		moveHistoryPanel.add(historyLabel);
		moveHistoryPanel.add(historyScrollPane);
		
		this.add(moveHistoryPanel, BorderLayout.EAST);
		
		
		// Start, Restart, Load and Save Game Buttons to the SOUTH
		JPanel controlPanel = new JPanel();
		
		startButton = new JButton("StartGame");
		restartButton = new JButton("Restart Game");
		JButton loadButton = new JButton("Load Game");
		JButton saveButton = new JButton("Save Game");
		
		disableStartButton();
		
		startButton.addActionListener(new StartButtonListener(this));		
		restartButton.addActionListener(new RestartButtonListener(this));
		
		controlPanel.add(startButton);
		controlPanel.add(restartButton);
		controlPanel.add(loadButton);
		controlPanel.add(saveButton);

		
		this.add(controlPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	/**
	 * Get player1's name
	 * 
	 * @return player 1's name
	 */
	public String getPlayer1Name()
	{
		return player1TextField.getText();
	}
	
	/**
	 * Get player2's name
	 * 
	 * @return player 2's name
	 */
	public String getPlayer2Name()
	{
		return player2TextField.getText();
	}
	
	/**
	 * Set the instruction text with the given string
	 * 
	 * @param string
	 */
	public void setInstructionText(String string)
	{
		instructionLabel.setText(string);
	}
	
	/**
	 * Returns the current selectedButton
	 * 
	 * @return selectedButton
	 */
	public BoardButton getSelectedButton()
	{
		return selectedButton;
	}
	
	/**
	 * Sets the given selected button with the given
	 * 
	 * @param button
	 */
	public void setSelectedButton(BoardButton button)
	{
		selectedButton = button;
	}
	
	/**
	 * Allows the start button to be pressed
	 */
	public void enableStartButton()
	{
		startButton.setEnabled(true);
	}
	
	/**
	 * Disables the start button to be pressed
	 */
	public void disableStartButton()
	{
		startButton.setEnabled(false);
	}
	
	/**
	 * Start the game
	 */
	public void startGame()
	{
		player1TextField.setEditable(false);
		player2TextField.setEditable(false);
		disableStartButton();
		selectedButton = null;
		
		model = new CheckersGame(getPlayer1Name(), getPlayer2Name());
		
		// Adds listeners to the darker squares
		for (int row = 0; row < Board.DIMENSION; row++)
		{
			for (int col = 0; col < Board.DIMENSION; col++)
			{
				if ((row + col)%2 == 1)
				{
					//Adds a new action listener to the board buttons once a new model is created
					boardButtons[row][col].addActionListener(new BoardButtonListener(model, this, boardButtons[row][col]));
					boardButtons[row][col].setEnabled(true);
				}
			}
		}
			
		updateUI();
	}
	
	/**
	 * Restarts the game
	 */
	public void restartGame()
	{
		player1TextField.setText("");
		player2TextField.setText("");
		player1TextField.setEditable(true);
		player2TextField.setEditable(true);
		disableStartButton();
		selectedButton = null;
		
		model = null;		
		
		// Removes listeners from the darker squares
		for (int row = 0; row < Board.DIMENSION; row++)
		{
			for (int col = 0; col < Board.DIMENSION; col++)
			{
				if ((row + col)%2 == 1)
				{
					ActionListener[] listeners = boardButtons[row][col].getActionListeners();
					
					// Remove the previous action listener from the button
					for (ActionListener listener : listeners)	
					{
						boardButtons[row][col].removeActionListener(listener);
					}
				}
			}
		}
		
		updateUI();
	}

	/**
	 * Starts the game
	 * @param args not used
	 */
	public static void main(String[] args)
	{
		// Sets the Look and Feel 
		try {
			// The following 1 line is from https://www.geeksforgeeks.org/java/java-swing-look-feel/ 
	        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }
		
		new CheckersView();
	}
	
	
	/**
	 * Updates the UI whenever the model is updated.
	 */
	public void updateUI()
	{
		// Updates the Board
		// No game has started
		if (model == null)
		{
			for (int row = 0; row < Board.DIMENSION; row++)
			{
				// Add the empty board buttons
				for (int col = 0; col < Board.DIMENSION; col++)
				{
					if ((row + col)%2 == 1)
					{
						boardButtons[row][col].setBackground(new Color(139, 69, 19));
						boardButtons[row][col].setIcon(null);
						boardButtons[row][col].setEnabled(false);
					}
				}
			}
			instructionLabel.setText("Please input your names and click the Start Button.");
			moveHistoryTextArea.setText("");
		}
//		// Check if the current player has won and turns off all the board buttons if they do
//		else if (model.checkForWin())
//		{
//			for (int row = 0; row < Board.DIMENSION; row++)
//			{
//				for (int col = 0; col < Board.DIMENSION; col++)
//				{
//					boardButtons[row][col].setEnabled(false);
//				}
//			}
//		}
		// A game is occurring. Updates the board to reflect the model.
		else 
		{
			for (int row = 0; row < Board.DIMENSION; row++)
			{
				for (int col = 0; col < Board.DIMENSION; col++) 
				{
					if ((row + col)%2 == 1)
					{
						Piece piece = model.getBoard().getPieceAt(row, col);
				
						if (piece == null)
						{
							boardButtons[row][col].setIcon(null);
						}
						else if (piece instanceof RegularPiece)
						{
							if (piece.getOwner() == model.getPlayer1())
							{
								boardButtons[row][col].setIcon(blackRegularIcon);
							}				
							else
							{
								boardButtons[row][col].setIcon(redRegularIcon);
							}
						}
						else if (piece instanceof KingPiece)
						{
							if (piece.getOwner() == model.getPlayer1())
							{
								boardButtons[row][col].setIcon(blackKingIcon);
							}				
							else
							{
								boardButtons[row][col].setIcon(redKingIcon);
							}
						}
					}
				}
			}
			// Updates the Move History
			moveHistoryTextArea.setText(model.getMoveHistory().toString());
		}
	}
}
