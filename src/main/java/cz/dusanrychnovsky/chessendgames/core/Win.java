package cz.dusanrychnovsky.chessendgames.core;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public class Win extends Result
{
	private final Player player;
	
	/**
	 * 
	 * @param player
	 */
	public Win(Player player) {
		this.player = player;
	}
	
	@Override
	public String toString() {
		return player + " won!";
	}
}
