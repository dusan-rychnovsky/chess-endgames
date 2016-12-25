package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Test;

import static com.google.common.collect.Iterables.contains;
import static com.google.common.collect.Iterables.size;
import static org.junit.Assert.*;

public class RookTest {

  private final Rook rook = new Rook();

  // ==========================================================================
  // LIST MOVES
  // ==========================================================================

  // 8 | . . x . . . . .
  // 7 | . . x . . . . .
  // 6 | . . x . . . . .
  // 5 | . . x . . . . .
  // 4 | x x R x x x x x
  // 3 | . . x . . . . .
  // 2 | . . x . . . . .
  // 1 | . . x . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void inCenterOfBoard_wholeColumnAndRow() {

    Iterable<Move> result = rook.listAllMovesFromPosition(Position.C4);
    assertEquals(14, size(result));

    // row
    assertTrue(contains(result, new Move(Position.C4, Position.A4)));
    assertTrue(contains(result, new Move(Position.C4, Position.B4)));
    assertTrue(contains(result, new Move(Position.C4, Position.D4)));
    assertTrue(contains(result, new Move(Position.C4, Position.E4)));
    assertTrue(contains(result, new Move(Position.C4, Position.F4)));
    assertTrue(contains(result, new Move(Position.C4, Position.G4)));
    assertTrue(contains(result, new Move(Position.C4, Position.H4)));

    // column
    assertTrue(contains(result, new Move(Position.C4, Position.C1)));
    assertTrue(contains(result, new Move(Position.C4, Position.C2)));
    assertTrue(contains(result, new Move(Position.C4, Position.C3)));
    assertTrue(contains(result, new Move(Position.C4, Position.C5)));
    assertTrue(contains(result, new Move(Position.C4, Position.C6)));
    assertTrue(contains(result, new Move(Position.C4, Position.C7)));
    assertTrue(contains(result, new Move(Position.C4, Position.C8)));
  }

  // 8 | R x x x x x x x
  // 7 | x . . . . . . .
  // 6 | x . . . . . . .
  // 5 | x . . . . . . .
  // 4 | x . . . . . . .
  // 3 | x . . . . . . .
  // 2 | x . . . . . . .
  // 1 | x . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void inLeftCorner_wholeColumnAndRow() {

    Iterable<Move> result = rook.listAllMovesFromPosition(Position.A8);
    assertEquals(14, size(result));

    // row
    assertTrue(contains(result, new Move(Position.A8, Position.B8)));
    assertTrue(contains(result, new Move(Position.A8, Position.C8)));
    assertTrue(contains(result, new Move(Position.A8, Position.D8)));
    assertTrue(contains(result, new Move(Position.A8, Position.E8)));
    assertTrue(contains(result, new Move(Position.A8, Position.F8)));
    assertTrue(contains(result, new Move(Position.A8, Position.G8)));
    assertTrue(contains(result, new Move(Position.A8, Position.H8)));

    // column
    assertTrue(contains(result, new Move(Position.A8, Position.A1)));
    assertTrue(contains(result, new Move(Position.A8, Position.A2)));
    assertTrue(contains(result, new Move(Position.A8, Position.A3)));
    assertTrue(contains(result, new Move(Position.A8, Position.A4)));
    assertTrue(contains(result, new Move(Position.A8, Position.A5)));
    assertTrue(contains(result, new Move(Position.A8, Position.A6)));
    assertTrue(contains(result, new Move(Position.A8, Position.A7)));
  }
}