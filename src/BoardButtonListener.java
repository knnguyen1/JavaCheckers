import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

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
 * 
 */

public class BoardButtonListener implements ActionListener
{
	//Instance Variable
	private CheckersGame model;	// A BoardButtonListener has-a model
	private CheckersView view;	// A BoardButtonListener has-a view
	private BoardButton button;	// A BoardButtonListener has-a button

	public BoardButtonListener (CheckersGame model, CheckersView view, BoardButton button)
	{
		this.model = model;
		this.view = view;
		this.button = button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Checks if a button has already been selected
		if (view.getSelectedButton() == null)
		{
			Piece piece = model.getBoard().getPieceAt(button.getRow(), button.getColumn());
			
			if (piece != null && piece.getOwner() == model.getCurrentPlayer())
			{ 
				// Makes this current button the selected button if one hasn't been
				view.setSelectedButton(button);
				button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
			}
		}
		// error somewhere help cryssss
		else
		{
			// Attempts to make the move
			int fromRow = view.getSelectedButton().getRow();
			int toRow = button.getRow();
			int fromCol = view.getSelectedButton().getColumn();
			int toCol = button.getColumn();
			
			Move move = new Move(fromRow, fromCol, toRow, toCol, model.getBoard().getPieceAt(fromRow, fromCol), null);
			
			//TODO: Set text
			if (model.makeMove(move))
			{
				// check for win etc
//				if ()
//				setInstructionText(model.getCurrentPlayer())
				System.out.println("A move has been completed.");
			}
			else 
			{
//				setInstructionText()
			}
			
			view.getSelectedButton().setBorder(null);
			view.setSelectedButton(null);
			view.updateUI();
		}
	}

}
