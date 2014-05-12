package cz.dusanrychnovsky.chessendgames.core.strategies;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import cz.dusanrychnovsky.chessendgames.core.King;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Player;
import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Rook;
import cz.dusanrychnovsky.chessendgames.core.Situation;
import cz.dusanrychnovsky.chessendgames.core.Player.Color;

public class PrecomputedValues extends Strategy
{
	private final Map<Pair<Situation, Player>, Integer> cache = new HashMap<Pair<Situation, Player>, Integer>();
	private final Map<Pair<Situation, Player>, Move> moves = new HashMap<Pair<Situation, Player>, Move>();
	
	private final Player whitePlayer;
	private final King whiteKing;
	
	private final Player blackPlayer;
	private final King blackKing;
	private final Rook blackRook;
	
	public static void main(String[] args)
	{
		Player whitePlayer = Player.get(Color.WHITE);
		King whiteKing = new King(whitePlayer);
		
		Player blackPlayer = Player.get(Color.BLACK);
		King blackKing = new King(blackPlayer);
		Rook blackRook = new Rook(blackPlayer);
		
		PrecomputedValues strategy = PrecomputedValues.get(whiteKing, blackKing, blackRook, 3);
	}
	
	private Situation get(String whiteKingPos, String blackKingPos, String blackRookPos)
	{
		Situation result = new Situation();
		
		result.addPiece(whiteKing, Position.get(whiteKingPos));
		result.addPiece(blackKing, Position.get(blackKingPos));
		result.addPiece(blackRook, Position.get(blackRookPos));
		
		return result;
	}

	public static PrecomputedValues get(King whiteKing, King blackKing, Rook blackRook, int numOfIterations)
	{
		PrecomputedValues result = new PrecomputedValues(whiteKing, blackKing, blackRook);
		
		for (int i = 0; i < numOfIterations; i++) 
		{
			System.out.println("Walkthrough no. " + (i + 1));
			
			long startTime = System.currentTimeMillis();
			int count = result.walkthrough();
			long finishTime = System.currentTimeMillis();
			
			long duration = finishTime - startTime; 
			System.out.println(duration + " ms");
			
			System.out.println(count + " new records");
		}
		
		// the cache won't be needed anymore - allow to free the occupied memory
		result.cache.clear();
		
		return result;
	}
	
	/**
	 * 
	 * @param whiteKing
	 * @param blackKing
	 * @param blackRook
	 */
	private PrecomputedValues(King whiteKing, King blackKing, Rook blackRook)
	{
		this.whitePlayer = whiteKing.getPlayer();
		this.whiteKing = whiteKing;
		
		this.blackPlayer = blackKing.getPlayer();
		this.blackKing = blackKing;
		this.blackRook = blackRook;
	}
	
	/**
	 * 
	 * @return
	 */
	private int walkthrough()
	{
		int result = 0;
		
		for (Position whiteKingPos : Position.getAllPositions())
		{
			for (Position blackKingPos : Position.getAllPositions())
			{
				if (whiteKingPos == blackKingPos) {
					continue;
				}
				
				for (Position blackRookPos : Position.getAllPositions())
				{
					if (blackRookPos == blackKingPos || blackRookPos == whiteKingPos) {
						continue;
					}
					
					Situation situation = new Situation();
					situation.addPiece(whiteKing, whiteKingPos);
					situation.addPiece(blackKing, blackKingPos);
					situation.addPiece(blackRook, blackRookPos);
					
					if (evaluate(situation, blackPlayer)) {
						result++;
					}
					
					if (evaluate(situation, whitePlayer)) {
						result++;
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param situation
	 * @param player
	 * @return
	 */
	public boolean evaluate(Situation situation, Player player)
	{
		Pair<Situation, Player> key = Pair.of(situation, player);
		if (cache.containsKey(key)) {
			return false;
		}
		
		if (situation.isCheckmate(whiteKing)) {
			cache.put(key, 0);
			return true;
		}
		
		if (situation.isStalemate(whiteKing)) {
			cache.put(key, Integer.MAX_VALUE);
			return true;
		}
		
		Player opponent = player.getOpponent();
		
		if (player.equals(whitePlayer))
		{
			int max = Integer.MIN_VALUE;
			Move result = null;
			
			for (Move move : situation.generateValidMoves(player))
			{
				Situation successor = Situation.get(situation, move);
				Pair<Situation, Player> tmpKey = Pair.of(successor, opponent);
				if (!cache.containsKey(tmpKey)) {
					return false;
				}
				
				int tmpValue = cache.get(tmpKey);
				if (tmpValue > max) 
				{
					result = move;
					max = tmpValue;
				}
			}
			
			cache.put(key, max);
			moves.put(key, result);
			
			return true;
		}
		else if (player.equals(blackPlayer))
		{
			int min = Integer.MAX_VALUE;
			Move result = null;
			
			for (Move move : situation.generateValidMoves(player))
			{
				Situation successor = Situation.get(situation, move);
				Pair<Situation, Player> tmpKey = Pair.of(successor, opponent);
				
				if (cache.containsKey(tmpKey))
				{
					int tmpValue = cache.get(tmpKey);
					
					// intentionally <, not <= here - we don't want to take
					// into account moves that lead to draws here
					if (tmpValue < min)
					{
						result = move;
						min = tmpValue;
					}
				}	
			}
			
			if (result != null)
			{
				int value = inc(min);
				
				cache.put(key, value);
				moves.put(key, result);
				
				return true;
			}
			else {
				return false;
			}
		}
		else {
			throw new IllegalArgumentException(
				"Unknown player [" + player + "]."
			);
		}
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private int inc(int value)
	{
		if (value == Integer.MAX_VALUE) {
			return value;
		}
		else {
			return value + 1;
		}
	}

	@Override
	public Move chooseMove(Situation situation, Player player)
	{
		Pair<Situation, Player> key = Pair.of(situation, player);
		if (!moves.containsKey(key)) {
			throw new IllegalArgumentException(
				"Unknown move for situation [" + situation + "]."
			);
		}
		
		return moves.get(key);
	}
}
