package cz.dusanrychnovsky.chessendgames;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cz.dusanrychnovsky.chessendgames.Player.Color;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public class MoveTest
{
	private King blackKing;
	
	private King whiteKing;
	private Rook whiteRook;
	
	@Before
	public void setUp()
	{
		Player blackPlayer = Player.get(Color.BLACK);
		
		blackKing = new King(blackPlayer);
		blackPlayer.addPiece(blackKing);
		
		Player whitePlayer = Player.get(Color.WHITE);
		
		whiteKing = new King(whitePlayer);
		whitePlayer.addPiece(whiteKing);
		
		whiteRook = new Rook(whitePlayer);
		whitePlayer.addPiece(whiteRook);
		
		blackPlayer.setOpponent(whitePlayer);
		whitePlayer.setOpponent(blackPlayer);
	}
	
	@Test
	public void aRookCannotCrossItsOwnKing()
	{
		Situation situation = new Situation();
		situation.addPiece(blackKing, Position.get(Column.CE, Row.R5));
		situation.addPiece(whiteKing, Position.get(Column.CD, Row.R3));
		situation.addPiece(whiteRook, Position.get(Column.CF, Row.R3));
		
		Move move = new Move(
			whiteRook,
			Position.get(Column.CF, Row.R3),
			Position.get(Column.CB, Row.R3)
		);
		
		assertFalse(move.isValid(situation));
	}
	
	@Test
	public void aKingCannotMoveNearTheOtherKing()
	{
		Situation situation = new Situation();
		situation.addPiece(blackKing, Position.get(Column.CE, Row.R5));
		situation.addPiece(whiteKing, Position.get(Column.CD, Row.R3));
		situation.addPiece(whiteRook, Position.get(Column.CF, Row.R3));
		
		Move move = new Move(
			whiteKing,
			Position.get(Column.CD, Row.R3),
			Position.get(Column.CD, Row.R4)
		);
		
		assertFalse(move.isValid(situation));
	}
	
	@Test
	public void aKingCannotBeExposedToAttack()
	{
		Situation situation = new Situation();
		situation.addPiece(blackKing, Position.get(Column.CE, Row.R5));
		situation.addPiece(whiteKing, Position.get(Column.CD, Row.R3));
		situation.addPiece(whiteRook, Position.get(Column.CB, Row.R4));
		
		Move move = new Move(
			blackKing,
			Position.get(Column.CE, Row.R5),
			Position.get(Column.CE, Row.R4)
		);
		
		assertFalse(move.isValid(situation));
	}
}
