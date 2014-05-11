package cz.dusanrychnovsky.chessendgames;

import java.util.List;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public abstract class Piece
{
	private final Player player;
	
	/**
	 * 
	 * @param player
	 */
	public Piece(Player player) 
	{
		this.player = player;
		player.addPiece(this);
	}
	
	/**
	 * 
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Returns a list of all moves that the represented piece could do from the
	 * given position if the board was otherwise empty.
	 * 
	 * @param position
	 * @return
	 */
	public abstract List<Move> generateMoves(Position position);
	
	@Override
	public int hashCode() {
		return player.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Piece)) {
			return false;
		}
		
		Piece other = (Piece) obj;
		return player.equals(other.player);
	}
}
