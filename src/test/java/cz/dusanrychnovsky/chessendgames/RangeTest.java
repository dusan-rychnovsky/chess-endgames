package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static cz.dusanrychnovsky.chessendgames.Row.*;
import static cz.dusanrychnovsky.chessendgames.Column.*;

import java.util.Iterator;

public class RangeTest {

  // ==========================================================================
  // Iterating
  // ==========================================================================

  @Test
  public void iterator_rowsRange() {
    var range = new Range<>(R2, R5);
    var it = range.iterator();
    assertEquals(R2, it.next());
    assertEquals(R3, it.next());
    assertEquals(R4, it.next());
    assertEquals(R5, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_colsRange() {
    var range = new Range<>(CB, CF);
    var it = range.iterator();
    assertEquals(CB, it.next());
    assertEquals(CC, it.next());
    assertEquals(CD, it.next());
    assertEquals(CE, it.next());
    assertEquals(CF, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_fullRange() {
    var range = new Range<>(R1, R8);
    var it = range.iterator();
    assertEquals(R1, it.next());
    assertEquals(R2, it.next());
    assertEquals(R3, it.next());
    assertEquals(R4, it.next());
    assertEquals(R5, it.next());
    assertEquals(R6, it.next());
    assertEquals(R7, it.next());
    assertEquals(R8, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_inverseRange() {
    var range = new Range<>(R5, R3);
    var it = range.iterator();
    assertEquals(R5, it.next());
    assertEquals(R4, it.next());
    assertEquals(R3, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_unitRange() {
    var range = new Range<>(CD, CD);
    var it = range.iterator();
    assertEquals(CD, it.next());
    assertFalse(it.hasNext());
  }
}
