package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import java.util.Iterator;

import static cz.dusanrychnovsky.chessendgames.Column.*;
import static cz.dusanrychnovsky.chessendgames.Row.*;
import static org.junit.Assert.*;

public class ExcludingRangeTest {

  @Test
  public void unitRange_emptyExcludingRange() {
    ExcludingRange range = new ExcludingRange(new Position(CA, R1), new Position(CA, R1));
    assertFalse(range.iterator().hasNext());
  }
  
  @Test
  public void rangeWithTwoItems_emptyExcludingRange() {
    ExcludingRange range = new ExcludingRange(new Position(CA, R1), new Position(CA, R2));
    assertFalse(range.iterator().hasNext());
  }
  
  @Test
  public void rangeWithThreeItems_excludingRangeWithOneItem() {
    
    ExcludingRange range = new ExcludingRange(new Position(CA, R1), new Position(CA, R3));
    Iterator<Position> it = range.iterator();
    
    assertEquals(new Position(CA, R2), it.next());
    assertFalse(it.hasNext());
  }
  
  @Test
  public void rangeWithFourItems_excludingRangeWithTwoItem() {
    
    ExcludingRange range = new ExcludingRange(new Position(CA, R1), new Position(CA, R4));
    Iterator<Position> it = range.iterator();
    
    assertEquals(new Position(CA, R2), it.next());
    assertEquals(new Position(CA, R3), it.next());
    assertFalse(it.hasNext());
  }
}