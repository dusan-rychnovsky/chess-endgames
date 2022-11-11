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
    assertTrue(WIN(WHITE).isFinal());
  }

  @Test
  public void isFinal_draw_returnsTrue() {
    assertTrue(DRAW.isFinal());
  }

  @Test
  public void isFinal_inProgress_returnsFalse() {
    assertFalse(IN_PROGRESS.isFinal());
  }

  // ==========================================================================
  // Is Win
  // ==========================================================================

  @Test
  public void isWin_win_returnsTrue() {
    assertTrue(WIN(WHITE).isWin(WHITE));
  }

  @Test
  public void isWin_winOfOpponent_returnsFalse() {
    assertFalse(WIN(BLACK).isWin(WHITE));
  }

  @Test
  public void isWin_draw_returnsFalse() {
    assertFalse(DRAW.isWin(WHITE));
  }

  @Test
  public void isWin_inProgress_returnsFalse() {
    assertFalse(IN_PROGRESS.isWin(WHITE));
  }

}
