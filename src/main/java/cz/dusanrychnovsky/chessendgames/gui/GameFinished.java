package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Situation;

public class GameFinished extends GameState
{
	private final Situation situation;
	
	/**
	 * 
	 * @param other
	 * @param situation
	 */
	public GameFinished(GameState other, Situation situation) {
		super(other);
		this.situation = situation;
	}

	@Override
	public GameState handle(Position position) {
		return this;
	}

	@Override
	public Situation getSituation() {
		return situation;
	}

	@Override
	public String getStatusMessage() {
		return "Game finished - " + situation.getResult();
	}
}
