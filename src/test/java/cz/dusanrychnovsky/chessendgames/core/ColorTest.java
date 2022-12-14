package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static org.junit.Assert.assertEquals;

public class ColorTest {

  // ==========================================================================
  // Opposite
  // ==========================================================================

  @Test
  public void opposite() {
    assertEquals(WHITE, BLACK.opposite());
    assertEquals(BLACK, WHITE.opposite());
  }
}
