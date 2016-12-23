package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static com.google.common.collect.Iterables.contains;
import static com.google.common.collect.Iterables.size;
import static cz.dusanrychnovsky.chessendgames.Column.*;
import static cz.dusanrychnovsky.chessendgames.Row.*;
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

    Position c4 = new Position(CC, R4);
    Iterable<Move> result = rook.listAllMovesFromPosition(c4);
    assertEquals(14, size(result));

    // row
    assertTrue(contains(result, new Move(c4, new Position(CA, R4))));
    assertTrue(contains(result, new Move(c4, new Position(CB, R4))));
    assertTrue(contains(result, new Move(c4, new Position(CD, R4))));
    assertTrue(contains(result, new Move(c4, new Position(CE, R4))));
    assertTrue(contains(result, new Move(c4, new Position(CF, R4))));
    assertTrue(contains(result, new Move(c4, new Position(CG, R4))));
    assertTrue(contains(result, new Move(c4, new Position(CH, R4))));

    // column
    assertTrue(contains(result, new Move(c4, new Position(CC, R1))));
    assertTrue(contains(result, new Move(c4, new Position(CC, R2))));
    assertTrue(contains(result, new Move(c4, new Position(CC, R3))));
    assertTrue(contains(result, new Move(c4, new Position(CC, R5))));
    assertTrue(contains(result, new Move(c4, new Position(CC, R6))));
    assertTrue(contains(result, new Move(c4, new Position(CC, R7))));
    assertTrue(contains(result, new Move(c4, new Position(CC, R8))));
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

    Position a8 = new Position(CA, R8);
    Iterable<Move> result = rook.listAllMovesFromPosition(a8);
    assertEquals(14, size(result));

    // row
    assertTrue(contains(result, new Move(a8, new Position(CB, R8))));
    assertTrue(contains(result, new Move(a8, new Position(CC, R8))));
    assertTrue(contains(result, new Move(a8, new Position(CD, R8))));
    assertTrue(contains(result, new Move(a8, new Position(CE, R8))));
    assertTrue(contains(result, new Move(a8, new Position(CF, R8))));
    assertTrue(contains(result, new Move(a8, new Position(CG, R8))));
    assertTrue(contains(result, new Move(a8, new Position(CH, R8))));

    // column
    assertTrue(contains(result, new Move(a8, new Position(CA, R1))));
    assertTrue(contains(result, new Move(a8, new Position(CA, R2))));
    assertTrue(contains(result, new Move(a8, new Position(CA, R3))));
    assertTrue(contains(result, new Move(a8, new Position(CA, R4))));
    assertTrue(contains(result, new Move(a8, new Position(CA, R5))));
    assertTrue(contains(result, new Move(a8, new Position(CA, R6))));
    assertTrue(contains(result, new Move(a8, new Position(CA, R7))));
  }
}