package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.IterableExtensions.size;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SituationsTest {

  // ==========================================================================
  // All
  // ==========================================================================

  @Test
  public void all_generatesAllValidSituations() {
    assertEquals(411_992, size(Situations.all()));
  }
}
