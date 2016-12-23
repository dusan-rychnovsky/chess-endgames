package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import java.util.Iterator;

import static cz.dusanrychnovsky.chessendgames.Column.*;
import static cz.dusanrychnovsky.chessendgames.Row.*;
import static org.junit.Assert.*;

public class PositionRangeTest {
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . X . . . .
  // 4 | . . . . . . . .
  // 3 | . . X . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test(expected = IllegalArgumentException.class)
  public void invalidRange() {
    // neither vertical nor horizontal nor diagonal
    new PositionRange(new Position(CC, R3), new Position(CD, R5));
  }
  
  // ==========================================================================
  // VERTICAL RANGE
  // ==========================================================================
  
  // 8 | . . X . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . X . . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void verticalRange() {
    
    PositionRange range = new PositionRange(new Position(CC, R5), new Position(CC, R8));
    Iterator<Position> it = range.iterator();
    
    assertEquals(new Position(CC, R5), it.next());
    assertEquals(new Position(CC, R6), it.next());
    assertEquals(new Position(CC, R7), it.next());
    assertEquals(new Position(CC, R8), it.next());
    assertFalse(it.hasNext());
  }
  
  // 8 | . . X . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . X . . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void reversedVerticalRange() {
  
    PositionRange range = new PositionRange(new Position(CC, R8), new Position(CC, R5));
    Iterator<Position> it = range.iterator();
  
    assertEquals(new Position(CC, R5), it.next());
    assertEquals(new Position(CC, R6), it.next());
    assertEquals(new Position(CC, R7), it.next());
    assertEquals(new Position(CC, R8), it.next());
    assertFalse(it.hasNext());
  }
  
  // ==========================================================================
  // HORIZONTAL RANGE
  // ==========================================================================
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . X X . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void horizontalRange() {
    
    PositionRange range = new PositionRange(new Position(CC, R5), new Position(CD, R5));
    Iterator<Position> it = range.iterator();
    
    assertEquals(new Position(CC, R5), it.next());
    assertEquals(new Position(CD, R5), it.next());
    assertFalse(it.hasNext());
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . X X . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void reversedHorizontalRange() {
    
    PositionRange range = new PositionRange(new Position(CD, R5), new Position(CC, R5));
    Iterator<Position> it = range.iterator();
    
    assertEquals(new Position(CC, R5), it.next());
    assertEquals(new Position(CD, R5), it.next());
    assertFalse(it.hasNext());
  }
  
  // ==========================================================================
  // DIAGONAL RANGE
  // ==========================================================================
  
  // 8 | X . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . X . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test()
  public void topLeftToBottomRightRange() {
    
    PositionRange range = new PositionRange(new Position(CA, R8), new Position(CD, R5));
    Iterator<Position> it = range.iterator();
    
    assertEquals(new Position(CA, R8), it.next());
    assertEquals(new Position(CB, R7), it.next());
    assertEquals(new Position(CC, R6), it.next());
    assertEquals(new Position(CD, R5), it.next());
    assertFalse(it.hasNext());
  }
  
  // 8 | X . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . X . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test()
  public void bottomRightToTopLeftRange() {
    
    PositionRange range = new PositionRange(new Position(CD, R5), new Position(CA, R8));
    Iterator<Position> it = range.iterator();
    
    assertEquals(new Position(CA, R8), it.next());
    assertEquals(new Position(CB, R7), it.next());
    assertEquals(new Position(CC, R6), it.next());
    assertEquals(new Position(CD, R5), it.next());
    assertFalse(it.hasNext());
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . X . . .
  // 5 | . . . X . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test()
  public void bottomLeftToTopRightRange() {
    
    PositionRange range = new PositionRange(new Position(CD, R5), new Position(CE, R6));
    Iterator<Position> it = range.iterator();
    
    assertEquals(new Position(CD, R5), it.next());
    assertEquals(new Position(CE, R6), it.next());
    assertFalse(it.hasNext());
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . X . . .
  // 5 | . . . X . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test()
  public void topRightToBottomLeftRange() {
    
    PositionRange range = new PositionRange(new Position(CE, R6), new Position(CD, R5));
    Iterator<Position> it = range.iterator();
    
    assertEquals(new Position(CD, R5), it.next());
    assertEquals(new Position(CE, R6), it.next());
    assertFalse(it.hasNext());
  }
}