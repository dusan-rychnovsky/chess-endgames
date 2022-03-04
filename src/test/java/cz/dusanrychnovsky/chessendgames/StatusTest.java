package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.Status.*;
import static cz.dusanrychnovsky.chessendgames.Color.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatusTest {

  // ==========================================================================
  // Is Final
  // ==========================================================================

  @Test
  public void isFinal_win_returnsTrue() {
    assertTrue(win(White).isFinal());
  }

  @Test
  public void isFinal_draw_returnsTrue() {
    assertTrue(Draw.isFinal());
  }

  @Test
  public void isFinal_inProgress_returnsFalse() {
    assertFalse(InProgress.isFinal());
  }

  // ==========================================================================
  // Is Win
  // ==========================================================================

  @Test
  public void isWin_win_returnsTrue() {
    assertTrue(win(White).isWin(White));
  }

  @Test
  public void isWin_winOfOpponent_returnsFalse() {
    assertFalse(win(Black).isWin(White));
  }

  @Test
  public void isWin_draw_returnsFalse() {
    assertFalse(Draw.isWin(White));
  }

  @Test
  public void isWin_inProgress_returnsFalse() {
    assertFalse(InProgress.isWin(White));
  }

}
