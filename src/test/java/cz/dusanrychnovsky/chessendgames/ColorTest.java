package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static org.junit.Assert.assertEquals;

public class ColorTest {

  // ==========================================================================
  // Parsing
  // ==========================================================================

  @Test
  public void parse_supportsLowerCase() {
    assertEquals(WHITE, parse("white"));
    assertEquals(BLACK, parse("black"));
  }

  @Test
  public void parse_supportsUpperCase() {
    assertEquals(WHITE, parse("WHITE"));
    assertEquals(BLACK, parse("BLACK"));
  }

  @Test
  public void parse_supportsCamelCase() {
    assertEquals(WHITE, parse("White"));
    assertEquals(BLACK, parse("Black"));
  }

  // ==========================================================================
  // Opposite
  // ==========================================================================

  @Test
  public void opposite() {
    assertEquals(WHITE, BLACK.opposite());
    assertEquals(BLACK, WHITE.opposite());
  }
}
