package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ExcludingRangeTest {

  @Test
  public void unitRange_emptyExcludingRange() {
    ExcludingRange range = new ExcludingRange(Position.A1, Position.A1);
    assertFalse(range.iterator().hasNext());
  }
  
  @Test
  public void rangeWithTwoItems_emptyExcludingRange() {
    ExcludingRange range = new ExcludingRange(Position.A1, Position.A2);
    assertFalse(range.iterator().hasNext());
  }
  
  @Test
  public void rangeWithThreeItems_excludingRangeWithOneItem() {
    
    ExcludingRange range = new ExcludingRange(Position.A1, Position.A3);
    Iterator<Position> it = range.iterator();
    
    Assert.assertEquals(Position.A2, it.next());
    assertFalse(it.hasNext());
  }
  
  @Test
  public void rangeWithFourItems_excludingRangeWithTwoItem() {
    
    ExcludingRange range = new ExcludingRange(Position.A1, Position.A4);
    Iterator<Position> it = range.iterator();
    
    Assert.assertEquals(Position.A2, it.next());
    Assert.assertEquals(Position.A3, it.next());
    assertFalse(it.hasNext());
  }
}