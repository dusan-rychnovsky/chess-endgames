package cz.dusanrychnovsky.chessendgames.yaat;

import org.junit.Test;

import java.util.Iterator;

import static cz.dusanrychnovsky.chessendgames.yaat.Column.CC;
import static cz.dusanrychnovsky.chessendgames.yaat.Column.CD;
import static cz.dusanrychnovsky.chessendgames.yaat.Row.*;
import static cz.dusanrychnovsky.chessendgames.yaat.Row.R4;
import static org.junit.Assert.*;

public class PositionRangeTest {
  
  // ==========================================================================
  // VERTICAL RANGE
  // ==========================================================================
  
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
  
  @Test
  public void horizontalRange() {
    
    PositionRange range = new PositionRange(new Position(CC, R5), new Position(CD, R5));
    Iterator<Position> it = range.iterator();
    
    assertEquals(new Position(CC, R5), it.next());
    assertEquals(new Position(CD, R5), it.next());
    assertFalse(it.hasNext());
  }
  
  @Test
  public void reversedHorizontalRange() {
    
    PositionRange range = new PositionRange(new Position(CD, R5), new Position(CC, R5));
    Iterator<Position> it = range.iterator();
    
    assertEquals(new Position(CC, R5), it.next());
    assertEquals(new Position(CD, R5), it.next());
    assertFalse(it.hasNext());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void invalidRange() {
    // neither vertical nor horizontal
    new PositionRange(new Position(CC, R3), new Position(CD, R4));
  }
  
  // ==========================================================================
  // DIAGONAL RANGE
  // ==========================================================================
  
  // TODO
}