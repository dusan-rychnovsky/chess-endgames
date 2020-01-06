package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Axis.left;
import static cz.dusanrychnovsky.chessendgames.Axis.right;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class AxisTest {

  // ==========================================================================
  // Left
  // ==========================================================================

  @Test
  public void leftAxis_pointsToPrev() {
    assertEquals(of(Row.R3), left().next(Row.R4));
    assertEquals(empty(), left().next(Row.R1));
  }

  // ==========================================================================
  // Right
  // ==========================================================================

  @Test
  public void rightAxis_pointsToNext() {
    assertEquals(of(Row.R3), right().next(Row.R2));
    assertEquals(empty(), right().next(Row.R8));
  }
}
