package cz.dusanrychnovsky.chessendgames;

import java.util.List;

public abstract class Piece
{
	/**
	 * Returns a list of all positions to which the represented piece could
	 * move from the given position if the board was otherwise empty.
	 * 
	 * @param position
	 * @return
	 */
	public abstract List<Position> generateMoves(Position position);
}
