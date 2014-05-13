package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.Game;
import cz.dusanrychnovsky.chessendgames.core.King;
import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Rook;
import cz.dusanrychnovsky.chessendgames.core.Situation;

public abstract class GameState
{
	protected final Game game;
	
	protected final King blackKing;
	protected final Rook blackRook;
	protected final King whiteKing;
	
	/**
	 * 
	 * @param game
	 * @param blackKing
	 * @param blackRook
	 * @param whiteKing
	 */
	public GameState(Game game, King blackKing, Rook blackRook, King whiteKing) {
		this.game = game;
		this.blackKing = blackKing;
		this.blackRook = blackRook;
		this.whiteKing = whiteKing;
	}
	
	/**
	 * 
	 * @param other
	 */
	public GameState(GameState other) {
		this(other.game, other.blackKing, other.blackRook, other.whiteKing);
	}
	
	public abstract GameState handle(Position position);
	public abstract Situation getSituation();
	public abstract String getStatusMessage();
}
