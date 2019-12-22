package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Row.*;

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
}
