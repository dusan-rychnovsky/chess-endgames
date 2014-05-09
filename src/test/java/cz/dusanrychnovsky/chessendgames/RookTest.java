package cz.dusanrychnovsky.chessendgames;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class RookTest
{
	private static final Rook rook = new Rook();
	
	@Test
	public void generatesCorrectSetOfMovesWhenLocatedInCenterOfTheBoard()
	{
		Position position = Position.get(Column.CC, Row.R3);
		List<Position> result = rook.generateMoves(position);
		
		assertEquals(14, result.size());
		
		assertTrue(result.contains(Position.get(Column.CC, Row.R8)));
		assertTrue(result.contains(Position.get(Column.CC, Row.R7)));
		assertTrue(result.contains(Position.get(Column.CC, Row.R6)));
		assertTrue(result.contains(Position.get(Column.CC, Row.R5)));
		assertTrue(result.contains(Position.get(Column.CC, Row.R4)));
		assertTrue(result.contains(Position.get(Column.CC, Row.R2)));
		assertTrue(result.contains(Position.get(Column.CC, Row.R1)));
		
		assertTrue(result.contains(Position.get(Column.CA, Row.R3)));
		assertTrue(result.contains(Position.get(Column.CB, Row.R3)));
		assertTrue(result.contains(Position.get(Column.CD, Row.R3)));
		assertTrue(result.contains(Position.get(Column.CE, Row.R3)));
		assertTrue(result.contains(Position.get(Column.CF, Row.R3)));
		assertTrue(result.contains(Position.get(Column.CG, Row.R3)));
		assertTrue(result.contains(Position.get(Column.CH, Row.R3)));
	}
	

	@Test
	public void generatesCorrectSetOfMovesWhenLocatedInACornerOfTheBoard()
	{
		Position position = Position.get(Column.CA, Row.R1);
		List<Position> result = rook.generateMoves(position);
		
		assertEquals(14, result.size());
		
		assertTrue(result.contains(Position.get(Column.CA, Row.R8)));
		assertTrue(result.contains(Position.get(Column.CA, Row.R7)));
		assertTrue(result.contains(Position.get(Column.CA, Row.R6)));
		assertTrue(result.contains(Position.get(Column.CA, Row.R5)));
		assertTrue(result.contains(Position.get(Column.CA, Row.R4)));
		assertTrue(result.contains(Position.get(Column.CA, Row.R3)));
		assertTrue(result.contains(Position.get(Column.CA, Row.R2)));
		
		assertTrue(result.contains(Position.get(Column.CB, Row.R1)));
		assertTrue(result.contains(Position.get(Column.CC, Row.R1)));
		assertTrue(result.contains(Position.get(Column.CD, Row.R1)));
		assertTrue(result.contains(Position.get(Column.CE, Row.R1)));
		assertTrue(result.contains(Position.get(Column.CF, Row.R1)));
		assertTrue(result.contains(Position.get(Column.CG, Row.R1)));
		assertTrue(result.contains(Position.get(Column.CH, Row.R1)));
	}
}
