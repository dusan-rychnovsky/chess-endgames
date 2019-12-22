package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Row.*;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class RowTest {

  // ==========================================================================
  // Parsing
  // ==========================================================================

  @Test
  public void parse() {
    assertEquals(R1, Row.parse("1"));
    assertEquals(R2, Row.parse("2"));
    assertEquals(R3, Row.parse("3"));
    assertEquals(R4, Row.parse("4"));
    assertEquals(R5, Row.parse("5"));
    assertEquals(R6, Row.parse("6"));
    assertEquals(R7, Row.parse("7"));
    assertEquals(R8, Row.parse("8"));
  }

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
}
