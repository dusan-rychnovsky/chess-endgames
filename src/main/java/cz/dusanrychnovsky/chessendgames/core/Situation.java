package cz.dusanrychnovsky.chessendgames.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public class Situation
{
	private final Map<Piece, Position> pieces = new HashMap<Piece, Position>();
	
	/**
	 * Returns the situation that is the result of performing the given move on
	 * the given situation.
	 * 
	 * @param originalSituation
	 * @param move
	 * @return
	 */
	public static Situation get(Situation originalSituation, Move move)
	{
		Piece piece = move.getPiece();
		Position to = move.getTo();
		
		Situation result = new Situation();
		
		for (Entry<Piece, Position> entry : originalSituation.pieces.entrySet())
		{
			if (piece.equals(entry.getKey())) {
				continue;
			}
			
			if (to.equals(entry.getValue())) {
				continue;
			}
			
			result.addPiece(entry.getKey(), entry.getValue());
		}
		
		result.addPiece(piece, to);
		
		return result;
	}
	
	/**
	 * 
	 * @param piece
	 * @param position
	 */
	public void addPiece(Piece piece, Position position)
	{
		if (pieces.containsKey(piece)) {
			throw new IllegalArgumentException(
				"Piece [" + piece + "] already registered."
			);
		}
		
		if (pieces.containsValue(position)) {
			throw new IllegalArgumentException(
				"Another piece already registered at position [" + position + "]."
			);
		}
		
		pieces.put(piece, position);
	}
	
	/**
	 * 
	 * @param piece
	 * @return
	 */
	public Position getPosition(Piece piece)
	{
		if (!pieces.containsKey(piece)) {
			throw new IllegalArgumentException("Unknown piece [" + piece + "].");
		}
		
		return pieces.get(piece);
	}

	/**
	 * 
	 * @param position
	 * @return
	 */
	public boolean isOccupied(Position position)
	{
		for (Entry<Piece, Position> entry : pieces.entrySet()) 
		{
			if (position.equals(entry.getValue())) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public Piece getPiece(Position position)
	{
		for (Entry<Piece, Position> entry : pieces.entrySet()) 
		{
			if (position.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		
		throw new IllegalArgumentException(
			"No piece at position [" + position + "]."
		);
	}
	
	/**
	 * Returns a list of all (mutually different) situations which can be
	 * reached using a single valid move of the given player.
	 * 
	 * @param player
	 * @return
	 */
	public List<Situation> generateSuccessors(Player player)
	{
		List<Situation> result = new ArrayList<Situation>();
		
		for (Piece piece : player.getPieces())
		{
			Position currPiecePosition = getPosition(piece);
			for (Move move : piece.generateMoves(currPiecePosition))
			{
				if (move.isValid(this)) {
					result.add(Situation.get(this, move));
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Returns true if and only the given king piece is in check in the
	 * represented situation.
	 * 
	 * @param king
	 * @return
	 */
	public boolean isCheck(King king)
	{
		// situation is a check to a king if and only if there exists a valid
		// move for any of the oponent's figures other than his king that would
		// end at the kings location
		
		Position kingsPosition = getPosition(king);
		
		Player otherPlayer = king.getPlayer().getOpponent();
		King otherKing = otherPlayer.getKing();
		
		for (Piece piece : otherPlayer.getPieces())
		{
			if (piece.equals(otherKing)) {
				continue;
			}
			
			Position currPosition = getPosition(piece);
			List<Move> moves = piece.generateMoves(currPosition);
			
			for (Move move : moves)
			{
				if (move.getTo().equals(kingsPosition) && move.isValid(this)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return pieces.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Situation)) {
			return false;
		}
		
		Situation other = (Situation) obj;
		return pieces.equals(other.pieces);
	}
}
