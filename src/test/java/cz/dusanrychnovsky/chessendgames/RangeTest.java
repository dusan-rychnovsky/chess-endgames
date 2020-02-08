package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static cz.dusanrychnovsky.chessendgames.Row.*;
import static cz.dusanrychnovsky.chessendgames.Column.*;

public class RangeTest {

  // ==========================================================================
  // Iterating Columns
  // ==========================================================================

  @Test
  public void iterator_cols() {
    var range = Range.from(CB, CF);
    var it = range.iterator();
    assertEquals(CB, it.next());
    assertEquals(CC, it.next());
    assertEquals(CD, it.next());
    assertEquals(CE, it.next());
    assertEquals(CF, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_cols_fullRange() {
    var range = Range.from(CA, CH);
    var it = range.iterator();
    assertEquals(CA, it.next());
    assertEquals(CB, it.next());
    assertEquals(CC, it.next());
    assertEquals(CD, it.next());
    assertEquals(CE, it.next());
    assertEquals(CF, it.next());
    assertEquals(CG, it.next());
    assertEquals(CH, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_cols_inverseRange() {
    var range = Range.from(CG, CC);
    var it = range.iterator();
    assertEquals(CG, it.next());
    assertEquals(CF, it.next());
    assertEquals(CE, it.next());
    assertEquals(CD, it.next());
    assertEquals(CC, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_cols_unitRange() {
    var range = Range.from(CD, CD);
    var it = range.iterator();
    assertEquals(CD, it.next());
    assertFalse(it.hasNext());
  }

  // ==========================================================================
  // Iterating Rows
  // ==========================================================================

  @Test
  public void iterator_rows() {
    var range = Range.from(R2, R5);
    var it = range.iterator();
    assertEquals(R2, it.next());
    assertEquals(R3, it.next());
    assertEquals(R4, it.next());
    assertEquals(R5, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_rows_fullRange() {
    var range = Range.from(R1, R8);
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
  public void iterator_rows_inverseRange() {
    var range = Range.from(R5, R3);
    var it = range.iterator();
    assertEquals(R5, it.next());
    assertEquals(R4, it.next());
    assertEquals(R3, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_rows_unitRange() {
    var range = Range.from(R3, R3);
    var it = range.iterator();
    assertEquals(R3, it.next());
    assertFalse(it.hasNext());
  }
}
