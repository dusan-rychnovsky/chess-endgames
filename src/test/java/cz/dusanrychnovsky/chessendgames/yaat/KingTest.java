package cz.dusanrychnovsky.chessendgames.yaat;

import com.google.common.collect.Iterables;
import org.junit.Test;

import static com.google.common.collect.Iterables.contains;
import static com.google.common.collect.Iterables.size;
import static cz.dusanrychnovsky.chessendgames.yaat.Column.*;
import static cz.dusanrychnovsky.chessendgames.yaat.Row.*;
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

    Position c4 = new Position(CC, R4);
    Iterable<Move> result = king.listAllMovesFromPosition(c4);
    assertEquals(8, size(result));

    assertTrue(contains(result, new Move(c4, new Position(CB, R5))));
    assertTrue(contains(result, new Move(c4, new Position(CC, R5))));
    assertTrue(contains(result, new Move(c4, new Position(CD, R5))));
    assertTrue(contains(result, new Move(c4, new Position(CB, R4))));
    assertTrue(contains(result, new Move(c4, new Position(CD, R4))));
    assertTrue(contains(result, new Move(c4, new Position(CB, R3))));
    assertTrue(contains(result, new Move(c4, new Position(CC, R3))));
    assertTrue(contains(result, new Move(c4, new Position(CD, R3))));
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

    Position a8 = new Position(CA, R8);
    Iterable<Move> result = king.listAllMovesFromPosition(a8);
    assertEquals(3, size(result));

    assertTrue(contains(result, new Move(a8, new Position(CA, R7))));
    assertTrue(contains(result, new Move(a8, new Position(CB, R7))));
    assertTrue(contains(result, new Move(a8, new Position(CB, R8))));
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

    Position h8 = new Position(CH, R8);
    Iterable<Move> result = king.listAllMovesFromPosition(h8);
    assertEquals(3, size(result));

    assertTrue(contains(result, new Move(h8, new Position(CH, R7))));
    assertTrue(contains(result, new Move(h8, new Position(CG, R7))));
    assertTrue(contains(result, new Move(h8, new Position(CG, R8))));
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

    Position c1 = new Position(CC, R1);
    Iterable<Move> result = king.listAllMovesFromPosition(c1);
    assertEquals(5, size(result));

    assertTrue(contains(result, new Move(c1, new Position(CB, R2))));
    assertTrue(contains(result, new Move(c1, new Position(CC, R2))));
    assertTrue(contains(result, new Move(c1, new Position(CD, R2))));
    assertTrue(contains(result, new Move(c1, new Position(CB, R1))));
    assertTrue(contains(result, new Move(c1, new Position(CD, R1))));
  }
}