package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.Game;
import cz.dusanrychnovsky.chessendgames.core.King;
import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Rook;
import cz.dusanrychnovsky.chessendgames.core.Situation;

public class PlaceBlackKing extends GameState
{
	private final Situation situation;
	
	/**
	 * 
	 * @param blackKing
	 * @param blackRook
	 * @param whiteKing
	 */
	public PlaceBlackKing(Game game, King blackKing, Rook blackRook, King whiteKing) {
		super(game, blackKing, blackRook, whiteKing);
		situation = game.getCurrentSituation();
	}
	
	@Override
	public GameState handle(Position position)
	{
		Situation nextSituation = new Situation(situation);
		nextSituation.addPiece(blackKing, position);
		
		game.setCurrentSituation(nextSituation);
		
		return new PlaceBlackRook(this, nextSituation);
	}

	@Override
	public Situation getSituation() {
		return situation;
	}

	@Override
	public String getStatusMessage() {
		return "Click on a square to place the opponent's king.";
	}	
}
