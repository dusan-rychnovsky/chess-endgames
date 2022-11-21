package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Color.*;
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
