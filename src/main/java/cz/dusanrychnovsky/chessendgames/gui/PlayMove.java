package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Situation;

public class PlayMove extends GameState
{
	private final Situation situation;
	
	/**
	 * 
	 * @param other
	 * @param situation
	 */
	public PlayMove(GameState other, Situation situation) {
		super(other);
		this.situation = situation;
	}

	@Override
	public GameState handle(Position position)
	{
		Position from = situation.getPosition(whiteKing);
		Position to = position;
		
		Move move = new Move(whiteKing, from, to);
		
		if (!move.isValid(situation)) {
			// return new InvalidMove(this, situation);
			return this;
		}
		
		Situation nextSituation = game.doMove(move);
		if (nextSituation.isFinal()) {
			return new GameFinished(this, nextSituation);
		}
		
		nextSituation = game.playMove();
		if (nextSituation.isFinal()) {
			return new GameFinished(this, nextSituation);
		}
		
		return new PlayMove(this, nextSituation);
	}

	@Override
	public Situation getSituation() {
		return situation;
	}

	@Override
	public String getStatusMessage() {
		return "Click on a square to make your move.";
	}
}
