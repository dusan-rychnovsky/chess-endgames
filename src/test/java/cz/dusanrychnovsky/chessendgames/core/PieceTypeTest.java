package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Test;

import static com.google.common.collect.Iterables.contains;
import static com.google.common.collect.Iterables.size;
import static cz.dusanrychnovsky.chessendgames.core.PieceType.*;
import static org.junit.Assert.*;

public class PieceTypeTest {
  
  private final PieceType rook = ROOK;
  private final PieceType king = KING;
  
  // ==========================================================================
  // KING
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
  
  // ==========================================================================
  // ROOK
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
