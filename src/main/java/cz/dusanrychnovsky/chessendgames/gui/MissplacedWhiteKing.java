package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Situation;

public class MissplacedWhiteKing extends GameState
{
	private final Situation situation;
	
	/**
	 * 
	 * @param other
	 * @param situation
	 */
	public MissplacedWhiteKing(GameState other, Situation situation) {
		super(other);
		this.situation = situation;
	}

	@Override
	public GameState handle(Position position)
	{
		if (!isCorrect(position)) {
			return this;
		}
		
		Situation nextSituation = new Situation(situation);
		nextSituation.addPiece(whiteKing, position);
		
		game.setCurrentSituation(nextSituation);
		
		return new PlayMove(this, nextSituation);
	}

	private boolean isCorrect(Position whiteKingPosition)
	{
		if (situation.isOccupied(whiteKingPosition)) {
			return false;
		}
		
		Position blackKingPosition = situation.getPosition(blackKing);
		if (whiteKingPosition.isNeighbour(blackKingPosition)) {
			return false;
		}
		
		return true;
	}

	@Override
	public Situation getSituation() {
		return situation;
	}

	@Override
	public String getStatusMessage() {
		return "Invalid king position. Click on a square to place your king.";
	}

}
