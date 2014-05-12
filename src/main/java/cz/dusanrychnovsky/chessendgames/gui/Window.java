package cz.dusanrychnovsky.chessendgames.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import cz.dusanrychnovsky.chessendgames.core.Situation;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private final Board board;
	private final StatusBar statusBar;
	
	/**
	 * 
	 * @param emptySituation
	 * @param listener
	 */
	public Window(Situation emptySituation, MouseEventListener listener)
	{
		setTitle("Chess Endgames");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		setLayout(new BorderLayout());
		
		board = new Board(emptySituation, listener); 
        add(board, BorderLayout.CENTER);
        
        statusBar = new StatusBar();
        add(statusBar, BorderLayout.SOUTH);
        
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * 
	 * @param situation
	 */
	public void setSituation(Situation situation) {
		board.setSituation(situation);
	}
	
	/**
	 * 
	 * @param message
	 */
	public void setStatus(String message) {
		statusBar.setStatusMessage(message);
	}
}
