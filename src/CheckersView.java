import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
 * Version/date: November 14, 2025
 *
 * Responsibilities of class:
 * A board is a 2d array of pieces. It can add and remove pieces. It knows which moves are valid and where players can move their pieces. 
 */

public class CheckersView extends JFrame // A CheckersView is-a JFrame
{
	//Instance Variables
	private CheckersGame model; // A CheckerView has-a checkers game model
	private JTextField player1TextField;
	private JTextField player2TextField;
	private JButton[][] boardButtons;	// A CheckersView has-many board buttons
	private DefaultListModel<String> historyModel;	
	private JList historyList;
	private JButton startButton;
	private JButton restartButton;
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
		
		// Load Board and Piece Images
		blackKingIcon = new ImageIcon("images/black_king.png");
		blackRegularIcon = new ImageIcon("images/black_regular.png");
		redKingIcon = new ImageIcon("images/red_king.png");
		redRegularIcon = new ImageIcon("images/red_regular.png");
		
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
				
				if ((row + col)%2 == 0)
				{
					boardButton.setBackground(new Color(139, 69, 19));
				}
				else
				{
					boardButton.setBackground(new Color(245, 222, 179));
				}
				boardButtons[row][col] = boardButton;				
				boardPanel.add(boardButton);
				
				//TODO: colors and listener
			}
		}
		this.add(boardPanel, BorderLayout.CENTER);
		
		
		// Move History Panel to the EAST
		JPanel moveHistoryPanel = new JPanel();
		moveHistoryPanel.setLayout(new BoxLayout(moveHistoryPanel, BoxLayout.Y_AXIS));
		
		JLabel historyLabel = new JLabel("Move History");
		historyModel = new DefaultListModel<String>();
		historyList = new JList<>(historyModel);
		JScrollPane historyScrollPane = new JScrollPane(historyList);
		
		moveHistoryPanel.add(historyLabel);
		moveHistoryPanel.add(historyScrollPane);
		
		this.add(moveHistoryPanel, BorderLayout.EAST);
		
		
		// Start, Restart Buttons to the SOUTH
		JPanel controlPanel = new JPanel();
		
		startButton = new JButton("StartGame");
		JButton restartButton = new JButton("Restart Game");
		
		startButton.setEnabled(false);
		
		startButton.addActionListener(new StartButtonListener(this));		
		// TODO: add restart button listener
		
		controlPanel.add(startButton);
		controlPanel.add(restartButton);
		
		this.add(controlPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public String getPlayer1Name()
	{
		return player1TextField.getText();
	}
	
	public String getPlayer2Name()
	{
		return player2TextField.getText();
	}
	
	public void enableStartButton()
	{
		startButton.setEnabled(true);
	}
	
	public void disableStartButton()
	{
		startButton.setEnabled(false);
	}
	
	public void startGame()
	{
		player1TextField.setEditable(false);
		player2TextField.setEditable(false);

		model = new CheckersGame(getPlayer1Name(), getPlayer2Name());
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
		// TODO: fix image icons
		for (int row = 0; row < Board.DIMENSION; row++)
		{
			for (int col = 1; col < Board.DIMENSION; col++) 
			{
				JButton boardButton = boardButtons[row][col];
				Piece piece = model.getBoard().getPieceAt(row, col);
				
				if (piece == null)
				{
					boardButton.setIcon(null);
				}
				else if (piece instanceof RegularPiece)
				{
					if (piece.getOwner().getName().equals(getPlayer1Name()))
					{
						boardButton.setIcon(blackRegularIcon);
					}				
					else
					{
						boardButton.setIcon(redRegularIcon);
					}
				}
				else if (piece instanceof KingPiece)
				{
					if (piece.getOwner().getName().equals(getPlayer1Name()))
					{
						boardButton.setIcon(blackKingIcon);
					}				
					else
					{
						boardButton.setIcon(redKingIcon);
					}
				}
			}
		}
	}
}
