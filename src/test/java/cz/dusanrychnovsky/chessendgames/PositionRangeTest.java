package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import java.util.Iterator;

import static cz.dusanrychnovsky.chessendgames.Position.*;
import static org.junit.Assert.*;

public class PositionRangeTest {
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . T . . . .
  // 4 | . . . . . . . .
  // 3 | . . F . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test(expected = IllegalArgumentException.class)
  public void invalidRange() {
    // neither vertical nor horizontal nor diagonal
    new PositionRange(C3, D5);
  }
  
  // ==========================================================================
  // VERTICAL RANGE
  // ==========================================================================
  
  // 8 | . . T . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . F . . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void verticalRange() {
    
    PositionRange range = new PositionRange(C5, C8);
    Iterator<Position> it = range.iterator();
    
    assertEquals(C5, it.next());
    assertEquals(C6, it.next());
    assertEquals(C7, it.next());
    assertEquals(C8, it.next());
    assertFalse(it.hasNext());
  }
  
  // 8 | . . F . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . T . . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void reversedVerticalRange() {
  
    PositionRange range = new PositionRange(C8, C5);
    Iterator<Position> it = range.iterator();
  
    assertEquals(C8, it.next());
    assertEquals(C7, it.next());
    assertEquals(C6, it.next());
    assertEquals(C5, it.next());
    assertFalse(it.hasNext());
  }
  
  // ==========================================================================
  // HORIZONTAL RANGE
  // ==========================================================================
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . F T . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void horizontalRange() {
    
    PositionRange range = new PositionRange(C5, D5);
    Iterator<Position> it = range.iterator();
    
    assertEquals(C5, it.next());
    assertEquals(D5, it.next());
    assertFalse(it.hasNext());
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . T F . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void reversedHorizontalRange() {
    
    PositionRange range = new PositionRange(D5, C5);
    Iterator<Position> it = range.iterator();
  
    assertEquals(D5, it.next());
    assertEquals(C5, it.next());
    assertFalse(it.hasNext());
  }
  
  // ==========================================================================
  // DIAGONAL RANGE
  // ==========================================================================
  
  // 8 | F . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . T . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test()
  public void topLeftToBottomRightRange() {
    
    PositionRange range = new PositionRange(A8, D5);
    Iterator<Position> it = range.iterator();
    
    assertEquals(A8, it.next());
    assertEquals(B7, it.next());
    assertEquals(C6, it.next());
    assertEquals(D5, it.next());
    assertFalse(it.hasNext());
  }
  
  // 8 | T . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . F . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test()
  public void bottomRightToTopLeftRange() {
    
    PositionRange range = new PositionRange(D5, A8);
    Iterator<Position> it = range.iterator();
    
    assertEquals(D5, it.next());
    assertEquals(C6, it.next());
    assertEquals(B7, it.next());
    assertEquals(A8, it.next());
    assertFalse(it.hasNext());
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . T . . .
  // 5 | . . . F . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test()
  public void bottomLeftToTopRightRange() {
    
    PositionRange range = new PositionRange(D5, E6);
    Iterator<Position> it = range.iterator();
    
    assertEquals(D5, it.next());
    assertEquals(E6, it.next());
    assertFalse(it.hasNext());
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . F . . .
  // 5 | . . . T . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test()
  public void topRightToBottomLeftRange() {
    
    PositionRange range = new PositionRange(E6, D5);
    Iterator<Position> it = range.iterator();
  
    assertEquals(E6, it.next());
    assertEquals(D5, it.next());
    assertFalse(it.hasNext());
  }
}
