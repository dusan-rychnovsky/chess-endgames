package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Position.*;

public class PositionTest {

  // ==========================================================================
  // Parsing
  // ==========================================================================

  @Test
  public void parse_supportsLowerCase() {
    assertEquals(D3, parse("d3"));
  }

  @Test
  public void parse_supportsUpperCase() {
    assertEquals(D3, parse("D3"));
  }
}
