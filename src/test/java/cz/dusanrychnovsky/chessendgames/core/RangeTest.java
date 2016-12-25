package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Test;

import java.util.Iterator;

import static cz.dusanrychnovsky.chessendgames.core.Column.*;
import static org.junit.Assert.*;

public class RangeTest {

  // ==========================================================================
  // RANGE OF COLUMNS
  // ==========================================================================

  @Test
  public void allowsIteratingThroughRange() {

    Range<Column> range = new Range<>(CD, CH);
    Iterator<Column> it = range.iterator();

    assertEquals(CD, it.next());
    assertEquals(CE, it.next());
    assertEquals(CF, it.next());
    assertEquals(CG, it.next());
    assertEquals(CH, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void canBeIteratedThroughMultipleTimes() {

    Range<Column> range = new Range<>(CD, CE);

    Iterator<Column> it = range.iterator();
    assertEquals(CD, it.next());
    assertEquals(CE, it.next());
    assertFalse(it.hasNext());

    it = range.iterator();
    assertEquals(CD, it.next());
    assertEquals(CE, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void supportsUnitRange() {

    Range<Column> range = new Range<>(CD, CD);
    Iterator<Column> it = range.iterator();

    assertEquals(CD, it.next());
    assertFalse(it.hasNext());
  }
  
  @Test
  public void reversedRange() {
    
    Range<Column> range = new Range<>(CH, CD);
    Iterator<Column> it = range.iterator();
  
    assertEquals(CH, it.next());
    assertEquals(CG, it.next());
    assertEquals(CF, it.next());
    assertEquals(CE, it.next());
    assertEquals(CD, it.next());
    assertFalse(it.hasNext());
  }
}
