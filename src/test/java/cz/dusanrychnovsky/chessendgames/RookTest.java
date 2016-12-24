package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static com.google.common.collect.Iterables.contains;
import static com.google.common.collect.Iterables.size;
import static cz.dusanrychnovsky.chessendgames.Position.*;
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

    Iterable<Move> result = rook.listAllMovesFromPosition(C4);
    assertEquals(14, size(result));

    // row
    assertTrue(contains(result, new Move(C4, A4)));
    assertTrue(contains(result, new Move(C4, B4)));
    assertTrue(contains(result, new Move(C4, D4)));
    assertTrue(contains(result, new Move(C4, E4)));
    assertTrue(contains(result, new Move(C4, F4)));
    assertTrue(contains(result, new Move(C4, G4)));
    assertTrue(contains(result, new Move(C4, H4)));

    // column
    assertTrue(contains(result, new Move(C4, C1)));
    assertTrue(contains(result, new Move(C4, C2)));
    assertTrue(contains(result, new Move(C4, C3)));
    assertTrue(contains(result, new Move(C4, C5)));
    assertTrue(contains(result, new Move(C4, C6)));
    assertTrue(contains(result, new Move(C4, C7)));
    assertTrue(contains(result, new Move(C4, C8)));
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

    Iterable<Move> result = rook.listAllMovesFromPosition(A8);
    assertEquals(14, size(result));

    // row
    assertTrue(contains(result, new Move(A8, B8)));
    assertTrue(contains(result, new Move(A8, C8)));
    assertTrue(contains(result, new Move(A8, D8)));
    assertTrue(contains(result, new Move(A8, E8)));
    assertTrue(contains(result, new Move(A8, F8)));
    assertTrue(contains(result, new Move(A8, G8)));
    assertTrue(contains(result, new Move(A8, H8)));

    // column
    assertTrue(contains(result, new Move(A8, A1)));
    assertTrue(contains(result, new Move(A8, A2)));
    assertTrue(contains(result, new Move(A8, A3)));
    assertTrue(contains(result, new Move(A8, A4)));
    assertTrue(contains(result, new Move(A8, A5)));
    assertTrue(contains(result, new Move(A8, A6)));
    assertTrue(contains(result, new Move(A8, A7)));
  }
}