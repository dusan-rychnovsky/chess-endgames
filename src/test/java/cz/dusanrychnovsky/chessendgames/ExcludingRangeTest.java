package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import java.util.Iterator;

import static cz.dusanrychnovsky.chessendgames.Position.*;
import static org.junit.Assert.*;

public class ExcludingRangeTest {

  @Test
  public void unitRange_emptyExcludingRange() {
    ExcludingRange range = new ExcludingRange(A1, A1);
    assertFalse(range.iterator().hasNext());
  }
  
  @Test
  public void rangeWithTwoItems_emptyExcludingRange() {
    ExcludingRange range = new ExcludingRange(A1, A2);
    assertFalse(range.iterator().hasNext());
  }
  
  @Test
  public void rangeWithThreeItems_excludingRangeWithOneItem() {
    
    ExcludingRange range = new ExcludingRange(A1, A3);
    Iterator<Position> it = range.iterator();
    
    assertEquals(A2, it.next());
    assertFalse(it.hasNext());
  }
  
  @Test
  public void rangeWithFourItems_excludingRangeWithTwoItem() {
    
    ExcludingRange range = new ExcludingRange(A1, A4);
    Iterator<Position> it = range.iterator();
    
    assertEquals(A2, it.next());
    assertEquals(A3, it.next());
    assertFalse(it.hasNext());
  }
}