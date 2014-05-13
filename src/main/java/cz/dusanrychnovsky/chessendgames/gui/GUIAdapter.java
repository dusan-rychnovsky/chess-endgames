package cz.dusanrychnovsky.chessendgames.gui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

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
	
	private GameState currentState;
	
	/**
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException
	{
		new GUIAdapter();
	}
	
	/**
	 * 
	 * @param strategy
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public GUIAdapter() throws ClassNotFoundException, IOException 
	{
		SplashScreen splashScreen = new SplashScreen();
		
		InputStream is = PrecomputedValues.class.getResourceAsStream("strategy.dat");
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(is));
		
		strategy = PrecomputedValues.load(in);
		in.close();
		
		splashScreen.close();
		
		window = new Window(this);
		initGame();
	}
	
	/**
	 * 
	 */
	private void initGame()
	{
		Situation emptySituation = new Situation();
		game = new Game(strategy, emptySituation, blackPlayer);
		
		currentState = new PlaceBlackKing(game, blackKing, blackRook, whiteKing);
		
		window.setSituation(currentState.getSituation());
		window.setStatus(currentState.getStatusMessage());
	}
	
	@Override
	public void onMouseClicked(Position position)
	{
		currentState = currentState.handle(position);
		
		Situation currentSituation = currentState.getSituation();
		window.setSituation(currentSituation);
		
		String statusMessage = currentState.getStatusMessage();
		window.setStatus(statusMessage);
		
		window.repaint();
	}
}
