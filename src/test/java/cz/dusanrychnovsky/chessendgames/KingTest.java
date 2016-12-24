package cz.dusanrychnovsky.chessendgames;

import com.google.common.collect.Iterables;
import org.junit.Test;

import static com.google.common.collect.Iterables.contains;
import static com.google.common.collect.Iterables.size;
import static cz.dusanrychnovsky.chessendgames.Position.*;
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

    Iterable<Move> result = king.listAllMovesFromPosition(C4);
    assertEquals(8, size(result));

    assertTrue(contains(result, new Move(C4, B5)));
    assertTrue(contains(result, new Move(C4, C5)));
    assertTrue(contains(result, new Move(C4, D5)));
    assertTrue(contains(result, new Move(C4, B4)));
    assertTrue(contains(result, new Move(C4, D4)));
    assertTrue(contains(result, new Move(C4, B3)));
    assertTrue(contains(result, new Move(C4, C3)));
    assertTrue(contains(result, new Move(C4, D3)));
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

    Iterable<Move> result = king.listAllMovesFromPosition(A8);
    assertEquals(3, size(result));

    assertTrue(contains(result, new Move(A8, A7)));
    assertTrue(contains(result, new Move(A8, B7)));
    assertTrue(contains(result, new Move(A8, B8)));
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

    Iterable<Move> result = king.listAllMovesFromPosition(H8);
    assertEquals(3, size(result));

    assertTrue(contains(result, new Move(H8, H7)));
    assertTrue(contains(result, new Move(H8, G7)));
    assertTrue(contains(result, new Move(H8, G8)));
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

    Iterable<Move> result = king.listAllMovesFromPosition(C1);
    assertEquals(5, size(result));

    assertTrue(contains(result, new Move(C1, B2)));
    assertTrue(contains(result, new Move(C1, C2)));
    assertTrue(contains(result, new Move(C1, D2)));
    assertTrue(contains(result, new Move(C1, B1)));
    assertTrue(contains(result, new Move(C1, D1)));
  }
}