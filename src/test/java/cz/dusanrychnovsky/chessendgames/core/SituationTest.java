package cz.dusanrychnovsky.chessendgames.core;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cz.dusanrychnovsky.chessendgames.core.Player.Color;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public class SituationTest
{
	private King blackKing;

	private Player whitePlayer;	
	private King whiteKing;
	private Rook whiteRook;
	
	@Before
	public void setUp()
	{
		Player blackPlayer = Player.get(Color.BLACK);
		blackPlayer.removeAllPieces();
		
		blackKing = new King(blackPlayer);
		
		whitePlayer = Player.get(Color.WHITE);
		whitePlayer.removeAllPieces();
		
		whiteKing = new King(whitePlayer);
		whiteRook = new Rook(whitePlayer);
	}
	
	// ========================================================================
	// MOVE EXECUTION
	// ========================================================================
	
	@Test
	public void createsANewSituationResultingFromTheOriginalOneByApplyingTheMove()
	{
		Situation origSituation = new Situation();
		origSituation.addPiece(blackKing, Position.get(Column.CD, Row.R5));
		origSituation.addPiece(whiteKing, Position.get(Column.CF, Row.R3));
		origSituation.addPiece(whiteRook, Position.get(Column.CH, Row.R1));
		
		Move move = new Move(
			whiteRook,
			Position.get(Column.CH, Row.R1),
			Position.get(Column.CH, Row.R8)
		);
		
		Situation newSituation = Situation.get(origSituation, move);
		
		assertEquals(Position.get(Column.CD, Row.R5), newSituation.getPosition(blackKing));
		assertEquals(Position.get(Column.CF, Row.R3), newSituation.getPosition(whiteKing));
		assertEquals(Position.get(Column.CH, Row.R8), newSituation.getPosition(whiteRook));
	}
	
	// ========================================================================
	// IS CHECK?
	// ========================================================================
	
	@Test
	public void aSituationIsACheckIfTheKingIsAttackedByAnOpponentsFiggure()
	{
		Situation situation = new Situation();
		situation.addPiece(blackKing, Position.get(Column.CD, Row.R5));
		situation.addPiece(whiteKing, Position.get(Column.CF, Row.R3));
		situation.addPiece(whiteRook, Position.get(Column.CG, Row.R5));
		
		assertTrue(situation.isCheck(blackKing));
	}

	@Test
	public void aSituationIsNotACheckIfTheKingIsNotAttacked()
	{
		Situation situation = new Situation();
		situation.addPiece(blackKing, Position.get(Column.CD, Row.R7));
		situation.addPiece(whiteKing, Position.get(Column.CA, Row.R5));
		situation.addPiece(whiteRook, Position.get(Column.CE, Row.R3));
		
		assertFalse(situation.isCheck(blackKing));
	}
	
	@Test
	public void aSituationIsNotACheckIfAnotherFigureStandsBetweenTheKingAndTheAttackingFigure()
	{
		Situation situation = new Situation();
		situation.addPiece(blackKing, Position.get(Column.CD, Row.R7));
		situation.addPiece(whiteKing, Position.get(Column.CD, Row.R5));
		situation.addPiece(whiteRook, Position.get(Column.CD, Row.R3));
		
		assertFalse(situation.isCheck(blackKing));
	}
	
	// ========================================================================
	// SUCCESSORS
	// ========================================================================
	
	@Test
	public void generatesSuccessorSituations()
	{
		Situation situation = new Situation();
		situation.addPiece(blackKing, Position.get(Column.CC, Row.R7));
		situation.addPiece(whiteKing, Position.get(Column.CB, Row.R5));
		situation.addPiece(whiteRook, Position.get(Column.CB, Row.R2));
		
		List<Situation> successors = situation.generateSuccessors(whitePlayer);
		assertEquals(16, successors.size());
	}
}
