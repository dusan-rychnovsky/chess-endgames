package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

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
    new PositionRange(Position.C3, Position.D5);
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
    
    PositionRange range = new PositionRange(Position.C5, Position.C8);
    Iterator<Position> it = range.iterator();
    
    Assert.assertEquals(Position.C5, it.next());
    Assert.assertEquals(Position.C6, it.next());
    Assert.assertEquals(Position.C7, it.next());
    Assert.assertEquals(Position.C8, it.next());
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
  
    PositionRange range = new PositionRange(Position.C8, Position.C5);
    Iterator<Position> it = range.iterator();
  
    Assert.assertEquals(Position.C8, it.next());
    Assert.assertEquals(Position.C7, it.next());
    Assert.assertEquals(Position.C6, it.next());
    Assert.assertEquals(Position.C5, it.next());
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
    
    PositionRange range = new PositionRange(Position.C5, Position.D5);
    Iterator<Position> it = range.iterator();
    
    Assert.assertEquals(Position.C5, it.next());
    Assert.assertEquals(Position.D5, it.next());
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
    
    PositionRange range = new PositionRange(Position.D5, Position.C5);
    Iterator<Position> it = range.iterator();
  
    Assert.assertEquals(Position.D5, it.next());
    Assert.assertEquals(Position.C5, it.next());
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
    
    PositionRange range = new PositionRange(Position.A8, Position.D5);
    Iterator<Position> it = range.iterator();
    
    Assert.assertEquals(Position.A8, it.next());
    Assert.assertEquals(Position.B7, it.next());
    Assert.assertEquals(Position.C6, it.next());
    Assert.assertEquals(Position.D5, it.next());
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
    
    PositionRange range = new PositionRange(Position.D5, Position.A8);
    Iterator<Position> it = range.iterator();
    
    Assert.assertEquals(Position.D5, it.next());
    Assert.assertEquals(Position.C6, it.next());
    Assert.assertEquals(Position.B7, it.next());
    Assert.assertEquals(Position.A8, it.next());
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
    
    PositionRange range = new PositionRange(Position.D5, Position.E6);
    Iterator<Position> it = range.iterator();
    
    Assert.assertEquals(Position.D5, it.next());
    Assert.assertEquals(Position.E6, it.next());
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
    
    PositionRange range = new PositionRange(Position.E6, Position.D5);
    Iterator<Position> it = range.iterator();
  
    Assert.assertEquals(Position.E6, it.next());
    Assert.assertEquals(Position.D5, it.next());
    assertFalse(it.hasNext());
  }
}
