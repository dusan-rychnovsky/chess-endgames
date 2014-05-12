package cz.dusanrychnovsky.chessendgames.gui;

import java.io.File;
import java.io.IOException;

import cz.dusanrychnovsky.chessendgames.CLIAdapter;
import cz.dusanrychnovsky.chessendgames.core.Game;
import cz.dusanrychnovsky.chessendgames.core.King;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Player;
import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Result;
import cz.dusanrychnovsky.chessendgames.core.Rook;
import cz.dusanrychnovsky.chessendgames.core.Situation;
import cz.dusanrychnovsky.chessendgames.core.Player.Color;
import cz.dusanrychnovsky.chessendgames.core.strategies.PrecomputedValues;
import cz.dusanrychnovsky.chessendgames.core.strategies.Strategy;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public class GUIAdapter implements MouseEventListener
{
	private final Window window;
	
	private final Player whitePlayer = Player.get(Color.WHITE);
	private final King whiteKing = new King(whitePlayer);
	
	private final Player blackPlayer = Player.get(Color.BLACK);
	private final King blackKing = new King(blackPlayer);
	private final Rook blackRook = new Rook(blackPlayer);
	
	private final Strategy strategy;
	private Game game;
	
	private Situation currentSituation = new Situation();
	
	private boolean initialized = false;
	
	/**
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException
	{
		File dataFile = new File(CLIAdapter.class.getResource("strategy.dat").getFile());
		Strategy strategy = PrecomputedValues.load(dataFile);
		
		new GUIAdapter(strategy);
	}
	
	/**
	 * 
	 * @param strategy
	 */
	public GUIAdapter(Strategy strategy) 
	{
		this.strategy = strategy;
		
		window = new Window(currentSituation, this);
		window.setStatus("Click on a field to place the opponent's king.");
	}
	
	@Override
	public void onMouseClicked(Position position)
	{
		if (!initialized)
		{
			if (!currentSituation.isActive(blackKing)) 
			{
				currentSituation.addPiece(blackKing, position);
				window.setStatus("Click on a square to place the opponent's rook.");
			}
			else if (!currentSituation.isActive(blackRook)) 
			{
				currentSituation.addPiece(blackRook, position);
				window.setStatus("Click on a square to place your king.");
			}
			else if (!currentSituation.isActive(whiteKing))
			{
				currentSituation.addPiece(whiteKing, position);
				game = new Game(strategy, currentSituation, blackPlayer);
				window.setStatus("Click on a square to make your move.");
				initialized = true;

				if (currentSituation.isFinal())
				{
					Result result = currentSituation.getResult();
					window.setStatus(result.toString());
				}
			}
			else {
				throw new AssertionError();
			}
			
			window.setSituation(currentSituation);
			window.repaint();
			
			return;
		}	

		if (currentSituation.isFinal()) {
			return;
		}
		
		window.setStatus("Click on a square to make your move.");
		
		Position from = currentSituation.getPosition(whiteKing);
		Position to = position;
		Move move = new Move(whiteKing, from, to);
		
		try {
			game.doMove(move);
			currentSituation = game.playMove();
		}
		catch (IllegalArgumentException ex) {
			window.setStatus("Invalid move. Click on a square to make your move.");
			return;
		}
		
		if (currentSituation.isFinal())
		{
			Result result = currentSituation.getResult();
			window.setStatus(result.toString());
		}
		
		window.setSituation(currentSituation);
		window.repaint();
	}
}
