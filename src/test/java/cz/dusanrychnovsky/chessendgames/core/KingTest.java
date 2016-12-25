package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Test;

import static com.google.common.collect.Iterables.contains;
import static com.google.common.collect.Iterables.size;
import static org.junit.Assert.*;

public class KingTest {

  private final King king = new King();

  // ==========================================================================
  // LIST MOVES
  // ==========================================================================

  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . x x x . . . .
  // 4 | . x K x . . . .
  // 3 | . x x x . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void inCenterOfBoard_allEightMoves() {

    Iterable<Move> result = king.listAllMovesFromPosition(Position.C4);
    assertEquals(8, size(result));

    assertTrue(contains(result, new Move(Position.C4, Position.B5)));
    assertTrue(contains(result, new Move(Position.C4, Position.C5)));
    assertTrue(contains(result, new Move(Position.C4, Position.D5)));
    assertTrue(contains(result, new Move(Position.C4, Position.B4)));
    assertTrue(contains(result, new Move(Position.C4, Position.D4)));
    assertTrue(contains(result, new Move(Position.C4, Position.B3)));
    assertTrue(contains(result, new Move(Position.C4, Position.C3)));
    assertTrue(contains(result, new Move(Position.C4, Position.D3)));
  }

  // 8 | K x . . . . . .
  // 7 | x x . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . . . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void topLeftCorner_threeMoves() {

    Iterable<Move> result = king.listAllMovesFromPosition(Position.A8);
    assertEquals(3, size(result));

    assertTrue(contains(result, new Move(Position.A8, Position.A7)));
    assertTrue(contains(result, new Move(Position.A8, Position.B7)));
    assertTrue(contains(result, new Move(Position.A8, Position.B8)));
  }

  // 8 | . . . . . . x K
  // 7 | . . . . . . x x
  // 6 | . . . . . . . .
  // 5 | . . . . . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void topRightCorner_threeMoves() {

    Iterable<Move> result = king.listAllMovesFromPosition(Position.H8);
    assertEquals(3, size(result));

    assertTrue(contains(result, new Move(Position.H8, Position.H7)));
    assertTrue(contains(result, new Move(Position.H8, Position.G7)));
    assertTrue(contains(result, new Move(Position.H8, Position.G8)));
  }

  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . . . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . x x x . . . .
  // 1 | . x K x . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void bottomSide_fiveMoves() {

    Iterable<Move> result = king.listAllMovesFromPosition(Position.C1);
    assertEquals(5, size(result));

    assertTrue(contains(result, new Move(Position.C1, Position.B2)));
    assertTrue(contains(result, new Move(Position.C1, Position.C2)));
    assertTrue(contains(result, new Move(Position.C1, Position.D2)));
    assertTrue(contains(result, new Move(Position.C1, Position.B1)));
    assertTrue(contains(result, new Move(Position.C1, Position.D1)));
  }
}