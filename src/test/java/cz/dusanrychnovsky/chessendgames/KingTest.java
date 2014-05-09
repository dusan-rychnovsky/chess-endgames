package cz.dusanrychnovsky.chessendgames;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class KingTest
{
	private static final King king = new King();
	
	@Test
	public void generatesCorrectSetOfMovesWhenLocatedInCenterOfTheBoard()
	{
		Position position = Position.get(Column.CC, Row.R3);
		List<Position> result = king.generateMoves(position);
		
		assertEquals(8, result.size());
		assertTrue(result.contains(Position.get(Column.CB, Row.R4)));
		assertTrue(result.contains(Position.get(Column.CC, Row.R4)));
		assertTrue(result.contains(Position.get(Column.CD, Row.R4)));
		assertTrue(result.contains(Position.get(Column.CD, Row.R3)));
		assertTrue(result.contains(Position.get(Column.CD, Row.R2)));
		assertTrue(result.contains(Position.get(Column.CC, Row.R2)));
		assertTrue(result.contains(Position.get(Column.CB, Row.R2)));
		assertTrue(result.contains(Position.get(Column.CB, Row.R3)));
	}
	
	@Test
	public void generatesCorrectSetOfMovesWhenLocatedAtABoundaryColumnOfTheBoard()
	{
		Position position = Position.get(Column.CH, Row.R5);
		List<Position> result = king.generateMoves(position);
		
		assertEquals(5, result.size());
		assertTrue(result.contains(Position.get(Column.CG, Row.R6)));
		assertTrue(result.contains(Position.get(Column.CH, Row.R6)));
		assertTrue(result.contains(Position.get(Column.CH, Row.R4)));
		assertTrue(result.contains(Position.get(Column.CG, Row.R4)));
		assertTrue(result.contains(Position.get(Column.CG, Row.R5)));
	}
	
	@Test
	public void generatesCorrectSetOfMovesWhenLocatedAtABoundaryRowOfTheBoard()
	{
		Position position = Position.get(Column.CB, Row.R1);
		List<Position> result = king.generateMoves(position);
		
		assertEquals(5, result.size());
		assertTrue(result.contains(Position.get(Column.CA, Row.R2)));
		assertTrue(result.contains(Position.get(Column.CB, Row.R2)));
		assertTrue(result.contains(Position.get(Column.CC, Row.R2)));
		assertTrue(result.contains(Position.get(Column.CC, Row.R1)));
		assertTrue(result.contains(Position.get(Column.CA, Row.R1)));
	}

	@Test
	public void generatesCorrectSetOfMovesWhenLocatedInACornerTheBoard()
	{
		Position position = Position.get(Column.CA, Row.R8);
		List<Position> result = king.generateMoves(position);
		
		assertEquals(3, result.size());
		assertTrue(result.contains(Position.get(Column.CB, Row.R8)));
		assertTrue(result.contains(Position.get(Column.CB, Row.R7)));
		assertTrue(result.contains(Position.get(Column.CA, Row.R7)));
	}
}
