package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Situation;

public class PlaceBlackRook extends GameState
{
	private final Situation situation;
	
	/**
	 * 
	 * @param other
	 * @param situation
	 */
	public PlaceBlackRook(GameState other, Situation situation) {
		super(other);
		this.situation = situation;
	}

	@Override
	public GameState handle(Position position)
	{
		if (situation.isOccupied(position)) {
			return new MissplacedBlackRook(this, situation);
		}
		
		Situation nextSituation = new Situation(situation);
		nextSituation.addPiece(blackRook, position);
		
		game.setCurrentSituation(nextSituation);
		
		return new PlaceWhiteKing(this, nextSituation);
	}

	@Override
	public Situation getSituation() {
		return situation;
	}

	@Override
	public String getStatusMessage() {
		return "Click on a square to place the opponent's rook.";
	}
}
