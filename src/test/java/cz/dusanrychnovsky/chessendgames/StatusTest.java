package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.Status.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatusTest {

  // ==========================================================================
  // Is Final
  // ==========================================================================

  @Test
  public void isFinal_win_returnsTrue() {
    assertTrue(win(Color.White).IsFinal());
  }

  @Test
  public void isFinal_draw_returnsTrue() {
    assertTrue(Draw.IsFinal());
  }

  @Test
  public void isFinal_inProgress_returnsFalse() {
    assertFalse(InProgress.IsFinal());
  }
}
