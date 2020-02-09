package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static cz.dusanrychnovsky.chessendgames.Row.*;
import static cz.dusanrychnovsky.chessendgames.Column.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;

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
  public void iterator_cols_inverseFullRange() {
    var range = Range.from(CH, CA);
    var it = range.iterator();
    assertEquals(CH, it.next());
    assertEquals(CG, it.next());
    assertEquals(CF, it.next());
    assertEquals(CE, it.next());
    assertEquals(CD, it.next());
    assertEquals(CC, it.next());
    assertEquals(CB, it.next());
    assertEquals(CA, it.next());
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
  public void iterator_rows_inverseFullRange() {
    var range = Range.from(R8, R1);
    var it = range.iterator();
    assertEquals(R8, it.next());
    assertEquals(R7, it.next());
    assertEquals(R6, it.next());
    assertEquals(R5, it.next());
    assertEquals(R4, it.next());
    assertEquals(R3, it.next());
    assertEquals(R2, it.next());
    assertEquals(R1, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_rows_unitRange() {
    var range = Range.from(R3, R3);
    var it = range.iterator();
    assertEquals(R3, it.next());
    assertFalse(it.hasNext());
  }

  // ==========================================================================
  // Iterating Position Horizontal
  // ==========================================================================

  @Test
  public void iterator_pos_leftToRight() {
    var range = Range.from(C3, F3);
    var it = range.iterator();
    assertEquals(C3, it.next());
    assertEquals(D3, it.next());
    assertEquals(E3, it.next());
    assertEquals(F3, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_pos_leftToRight_fullRange() {
    var range = Range.from(A3, H3);
    var it = range.iterator();
    assertEquals(A3, it.next());
    assertEquals(B3, it.next());
    assertEquals(C3, it.next());
    assertEquals(D3, it.next());
    assertEquals(E3, it.next());
    assertEquals(F3, it.next());
    assertEquals(G3, it.next());
    assertEquals(H3, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_pos_rightToLeft() {
    var range = Range.from(F5, D5);
    var it = range.iterator();
    assertEquals(F5, it.next());
    assertEquals(E5, it.next());
    assertEquals(D5, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_pos_rightToLeft_fullRange() {
    var range = Range.from(H5, A5);
    var it = range.iterator();
    assertEquals(H5, it.next());
    assertEquals(G5, it.next());
    assertEquals(F5, it.next());
    assertEquals(E5, it.next());
    assertEquals(D5, it.next());
    assertEquals(C5, it.next());
    assertEquals(B5, it.next());
    assertEquals(A5, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_pos_horizontal_unitRange() {
    var range = Range.from(B6, B6);
    var it = range.iterator();
    assertEquals(B6, it.next());
    assertFalse(it.hasNext());
  }

  // ==========================================================================
  // Iterating Position Vertical
  // ==========================================================================

  @Test
  public void iterator_pos_topToBottom() {
    var range = Range.from(C6, C3);
    var it = range.iterator();
    assertEquals(C6, it.next());
    assertEquals(C5, it.next());
    assertEquals(C4, it.next());
    assertEquals(C3, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_pos_topToBottom_fullRange() {
    var range = Range.from(C8, C1);
    var it = range.iterator();
    assertEquals(C8, it.next());
    assertEquals(C7, it.next());
    assertEquals(C6, it.next());
    assertEquals(C5, it.next());
    assertEquals(C4, it.next());
    assertEquals(C3, it.next());
    assertEquals(C2, it.next());
    assertEquals(C1, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_pos_bottomToTop() {
    var range = Range.from(D2, D4);
    var it = range.iterator();
    assertEquals(D2, it.next());
    assertEquals(D3, it.next());
    assertEquals(D4, it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void iterator_pos_bottomToTop_fullRange() {
    var range = Range.from(E1, E8);
    var it = range.iterator();
    assertEquals(E1, it.next());
    assertEquals(E2, it.next());
    assertEquals(E3, it.next());
    assertEquals(E4, it.next());
    assertEquals(E5, it.next());
    assertEquals(E6, it.next());
    assertEquals(E7, it.next());
    assertEquals(E8, it.next());
    assertFalse(it.hasNext());
  }
}
