package cz.dusanrychnovsky.chessendgames;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public class Move
{
	private final Piece piece;
	private final Range range;
	private final Position from;
	private final Position to;
	
	/**
	 * 
	 * @param piece
	 * @param from
	 * @param to
	 */
	public Move(Piece piece, Position from, Position to) {
		this.piece = piece;
		this.range = Range.get(from, to);
		this.from = from;
		this.to = to;
	}
	
	/**
	 * 
	 * @return
	 */
	public Piece getPiece() {
		return piece;
	}
		
	/**
	 * 
	 * @return
	 */
	public Position getTo() {
		return to;
	}
	
	/**
	 * Returns true if and only if the represented move is valid with regards
	 * to the given situation.
	 * 
	 * @param situation
	 * @return
	 */
	public boolean isValid(Situation situation)
	{
		// move is not valid unless it starts from the original piece position
		if (!from.equals(situation.getPosition(piece))) {
			return false;
		}
		
		// move is not valid if there is a piece standing at an intermediary
		// move position
		for (Position position : range)
		{
			if (position.equals(from) || position.equals(to)) {
				continue;
			}
			
			if (situation.isOccupied(position)) {
				return false;
			}
		}
		
		// move is not valid if there is that player's piece standing at the
		// final position
		if (situation.isOccupied(to)) 
		{
			Piece targetPiece = situation.getPiece(to);
			if (piece.getPlayer().equals(targetPiece.getPlayer())) {
				return false;
			}
		}
		
		Situation result = Situation.get(situation, this);
		
		// a king cannot move into a position where he would face a check
		if (piece instanceof King && result.isCheck((King) piece)) {
			return false;
		}
		
		// move is not valid if the two kings end up at the same spot or at
		// adjacent locations
		if (piece instanceof King)
		{
			Position kingsLocation = to;
			
			King otherKing = piece.getPlayer().getOpponent().getKing();
			Position otherKingsLocation = situation.getPosition(otherKing);
			
			if (kingsLocation.equals(otherKingsLocation)) {
				return false;
			}
			
			if (kingsLocation.isNeighbour(otherKingsLocation)) {
				return false;
			}
		}
		
		return true;
	}	
}
