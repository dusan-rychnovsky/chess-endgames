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
    assertEquals(White, parse("white"));
    assertEquals(Black, parse("black"));
  }

  @Test
  public void parse_supportsUpperCase() {
    assertEquals(White, parse("WHITE"));
    assertEquals(Black, parse("BLACK"));
  }

  @Test
  public void parse_supportsCamelCase() {
    assertEquals(White, parse("White"));
    assertEquals(Black, parse("Black"));
  }

  // ==========================================================================
  // Opposite
  // ==========================================================================

  @Test
  public void opposite() {
    assertEquals(White, Black.opposite());
    assertEquals(Black, White.opposite());
  }
}
