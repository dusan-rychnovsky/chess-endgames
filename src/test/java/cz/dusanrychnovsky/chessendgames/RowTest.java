package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Row.*;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class RowTest {

  // ==========================================================================
  // Navigating
  // ==========================================================================

  @Test
  public void prev() {
    assertEquals(empty(), R1.prev());
    assertEquals(of(R1), R2.prev());
    assertEquals(of(R2), R3.prev());
    assertEquals(of(R3), R4.prev());
    assertEquals(of(R4), R5.prev());
    assertEquals(of(R5), R6.prev());
    assertEquals(of(R6), R7.prev());
    assertEquals(of(R7), R8.prev());
  }

  @Test
  public void next() {
    assertEquals(of(R2), R1.next());
    assertEquals(of(R3), R2.next());
    assertEquals(of(R4), R3.next());
    assertEquals(of(R5), R4.next());
    assertEquals(of(R6), R5.next());
    assertEquals(of(R7), R6.next());
    assertEquals(of(R8), R7.next());
    assertEquals(empty(), R8.next());
  }

  // ==========================================================================
  // Printing
  // ==========================================================================

  @Test
  public void toStringShouldPrintLetter() {
    assertEquals("1", R1.toString());
    assertEquals("3", R3.toString());
    assertEquals("8", R8.toString());
  }

  // ==========================================================================
  // Distance
  // ==========================================================================

  @Test
  public void distanceShouldBeZeroForSameRow() {
    assertEquals(0, R2.distanceTo(R2));
  }

  @Test
  public void distanceShouldBeOneForAdjacentRows() {
    assertEquals(1, R2.distanceTo(R3));
    assertEquals(1, R3.distanceTo(R2));
  }

  @Test
  public void distanceShouldBeSevenForOppositeBorders() {
    assertEquals(7, R1.distanceTo(R8));
    assertEquals(7, R8.distanceTo(R1));
  }
}
