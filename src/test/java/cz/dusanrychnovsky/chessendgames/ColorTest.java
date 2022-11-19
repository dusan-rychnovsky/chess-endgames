package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static org.junit.Assert.assertEquals;

public class ColorTest {

  // ==========================================================================
  // Parsing
  // ==========================================================================

  @Test
  public void parseShouldSupportLowerCase() {
    assertEquals(WHITE, parse("white"));
    assertEquals(BLACK, parse("black"));
  }

  @Test
  public void parseShouldSupportUpperCase() {
    assertEquals(WHITE, parse("WHITE"));
    assertEquals(BLACK, parse("BLACK"));
  }

  @Test
  public void parseShouldSupportCamelCase() {
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
